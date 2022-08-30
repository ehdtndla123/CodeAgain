package com.note.plannerweb.note.controller;

import com.note.plannerweb.note.domain.Note;
import com.note.plannerweb.note.dto.NoteCreateRequest;
import com.note.plannerweb.note.dto.NoteResponse;
import com.note.plannerweb.note.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"2. Note"})
@RestController
@RequestMapping(value="/api/notes",produces = "application/json; charset=UTF8")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @ApiOperation(value = "오답노트 리스트 조회",notes = "오답노트 목록을 조회합니다.")
    @GetMapping
    public List<NoteResponse> getNoteList(){
        return this.noteService.getNoteList();
    }

    @ApiOperation(value="오답노트 생성",notes="오답노트를 생성합니다.")
    @PostMapping
    public NoteResponse createNote(@RequestBody NoteCreateRequest noteCreateRequest){
        return this.noteService.createNote(noteCreateRequest);
    }


//    @GetMapping(value ="/{page}")
//    public Object getNoteList(@PathVariable("page") Integer page){
//        return this.noteService.getNoteList(page);
//    }

    @ApiOperation(value = "오답노트 조회 (Id)",notes = "Id를 이용해 오답노트를 조회합니다.")
    @GetMapping(value="/{noteId}")
    public NoteResponse getNoteById(@PathVariable Long noteId){
        return noteService.getNoteById(noteId);
    }

    @ApiOperation(value = "오답노트 수정",notes = "Id를 이용해 오답노트를 수정합니다.")
    @PutMapping(value = "/{noteId}")
    public NoteResponse updateNoteById(@PathVariable Long noteId,@RequestBody NoteCreateRequest noteCreateRequest){
        return this.noteService.updateNote(noteId,noteCreateRequest);
    }

    @ApiOperation(value = "오답노트 삭제",notes="Id를 이용해 오답노트를 삭제합니다.")
    @DeleteMapping(value="/{noteId}")
    public Long deleteNoteById(@PathVariable Long noteId){
        this.noteService.deleteNote(noteId);
        return noteId;
    }
}
