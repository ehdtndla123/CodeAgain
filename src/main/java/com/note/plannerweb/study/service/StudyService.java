package com.note.plannerweb.study.service;

import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.except.CAuthenticationEntryPointException;
import com.note.plannerweb.except.MemberNotFoundCException;
import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.member.repository.MemberRepository;
import com.note.plannerweb.study.domain.Study;
import com.note.plannerweb.study.dto.StudyCreate;
import com.note.plannerweb.study.dto.StudyMemberResponse;
import com.note.plannerweb.study.dto.StudyResponse;
import com.note.plannerweb.study.repository.StudyMemberRepository;
import com.note.plannerweb.study.repository.StudyProblemRepository;
import com.note.plannerweb.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final StudyProblemRepository studyProblemRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    private final JwtProvider jwtProvider;



}
