package com.note.plannerweb.note.service;

import com.note.plannerweb.config.model.response.ListResult;
import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.except.CAuthenticationEntryPointException;
import com.note.plannerweb.except.MemberNotFoundCException;
import com.note.plannerweb.except.NoteNotFoundException;
import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.member.repository.MemberRepository;
import com.note.plannerweb.note.domain.Note;
import com.note.plannerweb.note.domain.NoteReview;
import com.note.plannerweb.note.dto.NoteCreateRequest;
import com.note.plannerweb.note.dto.NoteResponse;
import com.note.plannerweb.note.dto.NoteReviewCreateRequest;
import com.note.plannerweb.note.dto.NoteReviewResponse;
import com.note.plannerweb.note.repository.NoteRepository;
import com.note.plannerweb.note.repository.NoteReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final ModelMapper modelMapper;
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final NoteReviewRepository noteReviewRepository;

    public List<NoteResponse> getNoteMyList(String token){
        return getMemberByToken(token).getNotes().stream()
                .map(m->modelMapper.map(m,NoteResponse.class))
                .collect(Collectors.toList());
    }

    public NoteResponse createNote(String token,NoteCreateRequest noteCreateRequest){

        if(!jwtProvider.validateToken(token))
            throw new CAuthenticationEntryPointException();

        Member memberByToken = getMemberByToken(token);

        List<NoteReview> noteReviews=noteCreateRequest.getNoteReviews().stream()
                .map(o->o.toEntity())
                .collect(Collectors.toList());

        noteReviewRepository.saveAll(noteReviews);

        Note note=Note.builder()
                .noteReviews(noteReviews)
                .number(noteCreateRequest.getNumber())
                .memo(noteCreateRequest.getMemo())
                .code(noteCreateRequest.getCode())
                .category(noteCreateRequest.getCategory())
                .description(noteCreateRequest.getDescription())
                .subject(noteCreateRequest.getSubject())
                .targetDate(noteCreateRequest.getTargetDate())
                .member(memberByToken)
                .build();
        // Note 도메인에 setter가 없어서 modelMapper 사용 불가 modelMapper는 setter를 이용한다

        for(NoteReview n : noteReviews){
            n.setNote(note);
        }

        memberByToken.getNotes().add(note);

        noteRepository.save(note);

        return modelMapper.map(noteRepository.save(note),NoteResponse.class);
    }
    public NoteResponse getNoteById(String token,Long noteId){
        return getMemberByToken(token).getNotes().stream()
                .filter(o->o.getId()==noteId)
                .map(o->modelMapper.map(o,NoteResponse.class))
                .collect(Collectors.toList())
                .get(0);

    }

    public NoteResponse updateNote(String token,Long noteId,NoteCreateRequest noteCreateRequest){
        Note note=this.noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException());

        List<NoteReview> noteReviews=noteCreateRequest.getNoteReviews().stream()
                .map(o->o.toEntity())
                .collect(Collectors.toList());

        noteReviewRepository.saveAll(noteReviews);

        if(jwtProvider.validateToken(token)){
           note.update(noteCreateRequest.getNumber(), noteCreateRequest.getSubject(), noteCreateRequest.getDescription(), noteCreateRequest.getCategory()
                    ,noteCreateRequest.getCode(), noteCreateRequest.getMemo(), noteReviews);
        }
        return this.modelMapper.map(this.noteRepository.save(note),NoteResponse.class);
    }

    public NoteResponse deleteNote(String token,Long noteId){
        if(!jwtProvider.validateToken(token)){
            throw new CAuthenticationEntryPointException();
        }

        Member memberByToken = getMemberByToken(token);
        Note note=noteRepository.findById(noteId).orElseThrow(NoteNotFoundException::new);

        if(!memberByToken.getNotes().contains(note)){
            throw new CAuthenticationEntryPointException();
        }
        NoteResponse noteResponse = modelMapper.map(note, NoteResponse.class);
        noteRepository.delete(note);

        return noteResponse;
    }

    public NoteResponse getNoteResponse(Note note){
        return this.modelMapper.map(note,NoteResponse.class);
    }




    public Member getMemberByToken(String token){
        String userPk = this.jwtProvider.getUserPk(token);
        Long userLongPk=Long.parseLong(userPk);
        return this.memberRepository.findById(userLongPk).orElseThrow(MemberNotFoundCException::new);
    }
}
