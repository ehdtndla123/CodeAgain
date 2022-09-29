package com.note.plannerweb.study.controller;

import com.note.plannerweb.config.model.response.ListResult;
import com.note.plannerweb.config.model.response.SingleResult;
import com.note.plannerweb.config.model.service.ResponseService;
import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.plan.dto.PlannerResponse;
import com.note.plannerweb.study.dto.StudyCreate;
import com.note.plannerweb.study.dto.StudyMemberResponse;
import com.note.plannerweb.study.dto.StudyResponse;
import com.note.plannerweb.study.service.StudyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Api(tags = {"4. Study"})
@RequestMapping(value = "/api/study", produces = "application/json; charset=UTF8")
public class StudyController {

    private final StudyService studyService;
    private final JwtProvider jwtProvider;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(value = "자신의 스터디 조회", notes = "자신의 스터디를 조회합니다.")
    @GetMapping
    public SingleResult<StudyResponse> getStudyInfo(HttpServletRequest request) {
        return responseService.getSingleResult(studyService.getStudyInfoByToken(jwtProvider.resolveToken(request)));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(value = "스터디 생성", notes = "스터디를 생성합니다.")
    @PostMapping
    public SingleResult<StudyResponse> createStudy(HttpServletRequest request, StudyCreate studyCreate) {
        return responseService.getSingleResult(studyService.createStudy(jwtProvider.resolveToken(request), studyCreate));
    }

    @ApiOperation(value = "스터디 조회 By 아이디", notes = "스터디를 조회합니다.")
    @GetMapping(value = "/{studyId}")
    public SingleResult<StudyResponse> getStudyInfoById(@PathVariable Long studyId) {
        return responseService.getSingleResult(studyService.getStudyInfoById(studyId));
    }

    @ApiOperation(value = "스터디 조회 By SNO", notes = "스터디를 조회합니다.")
    @GetMapping(value = "/sno/{studyId}")
    public SingleResult<StudyResponse> getStudyInfoBySno(@PathVariable Long studyId) {
        return responseService.getSingleResult(studyService.getStudyInfoBySNO(studyId));
    }

    @ApiOperation(value = "해당 스터디의 멤버들 조회", notes = "해당 스터디의 멤버들을 조회합니다.")
    @GetMapping(value = "/members/{studyId}")
    public ListResult<StudyMemberResponse> getStudyMemberList(@PathVariable Long studyId){
        return responseService.getListResult(studyService.getStudyMemberList(studyId));
    }

    @ApiOperation(value = "스터디 삭제", notes = "스터디를 삭제합니다.")
    @DeleteMapping(value = "/{studyId}")
    public SingleResult<StudyResponse> deleteStudy(HttpServletRequest request, @PathVariable Long studyId) {
        return responseService.getSingleResult(studyService.deleteStudy(jwtProvider.resolveToken(request), studyId));
    }

}
