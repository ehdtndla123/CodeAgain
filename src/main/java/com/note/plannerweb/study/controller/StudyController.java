package com.note.plannerweb.study.controller;

import com.note.plannerweb.config.model.service.ResponseService;
import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.study.service.StudyService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = {"4. Study"})
@RequestMapping(value = "/api/study", produces = "application/json; charset=UTF8")
public class StudyController {

    private final StudyService studyService;
    private final JwtProvider jwtProvider;
    private final ResponseService responseService;


}
