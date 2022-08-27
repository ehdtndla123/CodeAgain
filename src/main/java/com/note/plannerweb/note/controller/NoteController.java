package com.note.plannerweb.note.controller;

import com.note.plannerweb.note.dto.NoteCreateRequest;
import com.note.plannerweb.note.dto.NoteResponse;
import com.note.plannerweb.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/notes",produces = "application/json; charset=UTF8")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public NoteResponse createNote(@RequestBody NoteCreateRequest noteCreateRequest){
        return this.noteService.createNote(noteCreateRequest);
    }

//    @GetMapping(value ="/{page}")
//    public Object getNoteList(@PathVariable("page") Integer page){
//        return this.noteService.getNoteList(page);
//    }

    @GetMapping(value="/{noteId}")
    public NoteResponse getNoteById(@PathVariable Long noteId){
        return noteService.getNoteById(noteId);
    }

}
