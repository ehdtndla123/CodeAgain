package com.note.plannerweb.note.service;

import com.note.plannerweb.note.domain.Note;
import com.note.plannerweb.note.dto.NoteCreateRequest;
import com.note.plannerweb.note.dto.NoteResponse;
import com.note.plannerweb.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final ModelMapper modelMapper;

    public NoteResponse createNote(NoteCreateRequest noteCreateRequest){
        // Note 도메인에 setter가 없어서 modelMapper 사용 불가
        Note note= Note.builder()
                .number(noteCreateRequest.getNumber())
                .subject(noteCreateRequest.getSubject())
                .description(noteCreateRequest.getDescription())
                .category(noteCreateRequest.getCategory())
                .code(noteCreateRequest.getCode())
                .memo(noteCreateRequest.getMemo())
                .repeat(noteCreateRequest.getMemo())
                .repeat_time(noteCreateRequest.getRepeat_time())
                .build();
        Note save = this.noteRepository.save(note);
        return getNoteResponse(save);
    }

    public NoteResponse getNoteResponse(Note note){
        return this.modelMapper.map(note,NoteResponse.class);
    }

    //page 12개 note list
    public Page<Note> getNoteList(int page){
        List<Sort.Order> list=new ArrayList<>();
        list.add(Sort.Order.by("id"));
        Pageable pageable= PageRequest.of(page,12,Sort.by(list));
        return this.noteRepository.findAll(pageable);
    }

    public NoteResponse getNoteById(Long noteId){
        Note note= this.noteRepository.findById(noteId).get();
        return this.modelMapper.map(note,NoteResponse.class);
    }
}
