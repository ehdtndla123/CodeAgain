package com.note.plannerweb.note.service;

import com.note.plannerweb.config.model.response.ListResult;
import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.except.MemberNotFoundCException;
import com.note.plannerweb.except.NoteNotFoundException;
import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.member.repository.MemberRepository;
import com.note.plannerweb.note.domain.Note;
import com.note.plannerweb.note.dto.NoteCreateRequest;
import com.note.plannerweb.note.dto.NoteResponse;
import com.note.plannerweb.note.dto.NoteUpdateRequest;
import com.note.plannerweb.note.repository.NoteRepository;
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

    public List<NoteResponse> getNoteMyList(String token){
        return getMemberByToken(token).getNotes().stream()
                .map(m->modelMapper.map(m,NoteResponse.class))
                .collect(Collectors.toList());
    }

    public NoteResponse createNote(NoteCreateRequest noteCreateRequest, String token){
        // Note 도메인에 setter가 없어서 modelMapper 사용 불가 modelMapper는 setter를 이용한다
        Member member=this.getMemberByToken(token);

        Note note= Note.builder()
                .number(noteCreateRequest.getNumber())
                .subject(noteCreateRequest.getSubject())
                .description(noteCreateRequest.getDescription())
                .category(noteCreateRequest.getCategory())
                .code(noteCreateRequest.getCode())
                .memo(noteCreateRequest.getMemo())
                .repeat_complete(noteCreateRequest.getRepeat_complete())
                .repeat_time(noteCreateRequest.getRepeat_time())
                .build();

        note.setMember(member);

        member.getNotes().add(note);

        Note save = this.noteRepository.save(note);
        return getNoteResponse(save);
    }
    public NoteResponse getNoteById(Long noteId,String token){
        return getMemberByToken(token).getNotes().stream()
                .filter(o->o.getId()==noteId)
                .map(o->modelMapper.map(o,NoteResponse.class))
                .collect(Collectors.toList())
                .get(0);
    }

    public NoteResponse updateNote(Long id,NoteCreateRequest noteCreateRequest,String token){
        Note note=this.noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException());
        if(jwtProvider.validateToken(token)){
           note.update(noteCreateRequest.getNumber(), noteCreateRequest.getSubject(), noteCreateRequest.getDescription(), noteCreateRequest.getCategory()
                    ,noteCreateRequest.getCode(), noteCreateRequest.getMemo(), noteCreateRequest.getRepeat_complete(), noteCreateRequest.getRepeat_time());
        }
        return this.modelMapper.map(this.noteRepository.save(note),NoteResponse.class);
    }

    public Long deleteNote(Long id,String token){
        Note note=noteRepository.findById(id).orElseThrow(NoteNotFoundException::new);
        Long deleteId=note.getId();
        if(jwtProvider.validateToken(token)){
            noteRepository.delete(note);
        }
        return deleteId;
    }

    public NoteResponse getNoteResponse(Note note){
        return this.modelMapper.map(note,NoteResponse.class);
    }

    //page 12개 note list
//    public Page<Note> getNoteList(int page){
//        List<Sort.Order> list=new ArrayList<>();
//        list.add(Sort.Order.by("id"));
//        Pageable pageable= PageRequest.of(page,12,Sort.by(list));
//        return this.noteRepository.findAll(pageable);
//    }
//    public List<NoteResponse> getNoteList(){
//        return this.noteRepository.findAll().stream()
//                .map(m->this.modelMapper.map(m,NoteResponse.class))
//                .collect(Collectors.toList());
//    }





    public Member getMemberByToken(String token){
        String userPk = this.jwtProvider.getUserPk(token);
        Long userLongPk=Long.parseLong(userPk);
        return this.memberRepository.findById(userLongPk).orElseThrow(MemberNotFoundCException::new);
    }
}
