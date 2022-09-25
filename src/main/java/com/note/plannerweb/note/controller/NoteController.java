package com.note.plannerweb.note.controller;

import com.note.plannerweb.config.model.response.ListResult;
import com.note.plannerweb.config.model.response.SingleResult;
import com.note.plannerweb.config.model.service.ResponseService;
import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.note.dto.NoteCreateRequest;
import com.note.plannerweb.note.dto.NoteResponse;
import com.note.plannerweb.note.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = {"2. Note"})
@RestController
@RequestMapping(value="/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    private final ResponseService responseService;
    private final JwtProvider jwtProvider;

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String",paramType = "header"
            )
    })
    @ApiOperation(value="자신의 오답노트 조회",notes = "자신의 오답노트를 조회합니다.")
    @GetMapping
    public ListResult<NoteResponse> getNoteListByToken(HttpServletRequest request){
        return responseService.getListResult(noteService.getNoteMyList(jwtProvider.resolveToken(request)));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String",paramType = "header"
            )
    })
    @ApiOperation(value="오답노트 생성",notes="오답노트를 생성합니다.")
    @PostMapping
    public SingleResult<NoteResponse> createNote(HttpServletRequest request,@RequestBody NoteCreateRequest noteCreateRequest){
        return this.responseService.getSingleResult(noteService.createNote(jwtProvider.resolveToken(request),noteCreateRequest));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String",paramType = "header"
            )
    })
    @ApiOperation(value = "오답노트 조회 (Id)",notes = "Id를 이용해 오답노트를 조회합니다.")
    @GetMapping(value="/{noteId}")
    public SingleResult<NoteResponse> getNoteById(HttpServletRequest request,@PathVariable Long noteId) {
        return responseService.getSingleResult(noteService.getNoteById(jwtProvider.resolveToken(request),noteId));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String",paramType = "header"
            )
    })
    @ApiOperation(value = "오답노트 수정",notes = "Id를 이용해 오답노트를 수정합니다.")
    @PutMapping(value = "/{noteId}")
    public SingleResult<NoteResponse> updateNoteById(HttpServletRequest request,@PathVariable Long noteId,@RequestBody NoteCreateRequest noteCreateRequest){
        return responseService.getSingleResult(noteService.updateNote(jwtProvider.resolveToken(request),noteId,noteCreateRequest));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String",paramType = "header"
            )
    })
    @ApiOperation(value = "오답노트 삭제",notes="Id를 이용해 오답노트를 삭제합니다.")
    @DeleteMapping(value="/{noteId}")
    public SingleResult<NoteResponse> deleteNoteById(HttpServletRequest request,@PathVariable Long noteId) {
        return responseService.getSingleResult(noteService.deleteNote(jwtProvider.resolveToken(request),noteId));
    }




}
