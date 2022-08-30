package com.note.plannerweb.note.service;

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

    public NoteResponse createNote(NoteCreateRequest noteCreateRequest){
        // Note 도메인에 setter가 없어서 modelMapper 사용 불가 modelMapper는 setter를 이용한다
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
        Note save = this.noteRepository.save(note);
        return getNoteResponse(save);
    }

    public NoteResponse updateNote(Long id,NoteCreateRequest noteCreateRequest){
        Note note=this.noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 오답노트가 없습니다. id="+id));
        note.update(noteCreateRequest.getNumber(), noteCreateRequest.getSubject(), noteCreateRequest.getDescription(), noteCreateRequest.getCategory()
                ,noteCreateRequest.getCode(), noteCreateRequest.getMemo(), noteCreateRequest.getRepeat_complete(), noteCreateRequest.getRepeat_time());

        return this.modelMapper.map(this.noteRepository.save(note),NoteResponse.class);
    }

    public void deleteNote(Long id){
        Note note=this.noteRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 오답노트가 없습니다. id="+id));

        this.noteRepository.delete(note);
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
    public List<NoteResponse> getNoteList(){
        return this.noteRepository.findAll().stream()
                .map(m->this.modelMapper.map(m,NoteResponse.class))
                .collect(Collectors.toList());
    }

    public NoteResponse getNoteById(Long noteId){
        Note note= this.noteRepository.findById(noteId).get();
        return this.modelMapper.map(note,NoteResponse.class);
    }
}
