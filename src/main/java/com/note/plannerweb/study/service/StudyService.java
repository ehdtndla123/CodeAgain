package com.note.plannerweb.study.service;

import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.except.*;
import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.member.repository.MemberRepository;
import com.note.plannerweb.study.domain.Study;
import com.note.plannerweb.study.domain.StudyMember;
import com.note.plannerweb.study.domain.StudyPlan;
import com.note.plannerweb.study.domain.StudyProblem;
import com.note.plannerweb.study.dto.*;
import com.note.plannerweb.study.repository.StudyMemberRepository;
import com.note.plannerweb.study.repository.StudyPlanRepository;
import com.note.plannerweb.study.repository.StudyProblemRepository;
import com.note.plannerweb.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final StudyProblemRepository studyProblemRepository;
    private final StudyPlanRepository studyPlanRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;


    private final JwtProvider jwtProvider;

    public StudyResponse createStudy(String token, StudyCreate studyCreate) {
        tokenValidate(token);

        Member memberByToken = getMemberByToken(token);
        StudyMember studyMember = StudyMember.builder()
                .member(memberByToken)
                .name(memberByToken.getName())
                .build();
        memberByToken.setStudyMember(studyMember);

        Long authNo = (long)(Math.random() * (999999 - 100000 + 1)) + 100000;
        List<StudyMember> studyMembers = new ArrayList<>();
        studyMembers.add(studyMemberRepository.save(studyMember));


        Study study = Study.builder()
                .name(studyCreate.getName())
                .sno(authNo)
                .studyMembers(studyMembers)
                .build();



        studyMember.setStudy(study);

        studyRepository.save(study);

        return modelMapper.map(study, StudyResponse.class);
    }

    public StudyPlanResponse createStudyPlan(String token,Long studyId ,StudyPlanCreate studyPlanCreate) {
        tokenValidate(token);

        Study study = studyRepository.findById(studyId).orElseThrow(StudyNotFoundException::new);

        List<StudyProblem> studyProblems = studyPlanCreate.getStudyProblems().stream()
                .map(o -> o.toEntity())
                .collect(Collectors.toList());

        studyProblemRepository.saveAll(studyProblems);


        StudyPlan studyPlan= StudyPlan.builder()
                .location(studyPlanCreate.getLocation())
                .penaltyContent(studyPlanCreate.getPenaltyContent())
                .penaltyTF(studyPlanCreate.getPenaltyTF())
                .targetDate(studyPlanCreate.getTargetDate())
                .study(study)
                .studyProblems(studyProblems)
                .build();

        for (StudyProblem s : studyProblems) {
            s.setStudyPlan(studyPlan);
        }

        studyPlanRepository.save(studyPlan);

        study.getStudyPlans().add(studyPlan);

        //studyRepository.save(study);
        return modelMapper.map(studyPlan, StudyPlanResponse.class);
    }

    public StudyProblemResponse createStudyProblem(String token, Long studyPlanId, StudyProblemCreate studyProblemCreate) {
        tokenValidate(token);
        StudyPlan plan = studyPlanRepository.findById(studyPlanId).orElseThrow(StudyPlanNotFoundException::new);

        StudyProblem studyProblem= StudyProblem.builder()
                .subject(studyProblemCreate.getSubject())
                .code(studyProblemCreate.getCode())
                .studyPlan(plan)
                .build();
        studyProblemRepository.save(studyProblem);

        plan.getStudyProblems().add(studyProblem);

        return modelMapper.map(studyProblem, StudyProblemResponse.class);
    }


//    public StudyResponse createStudy(String token, StudyCreate studyCreate) {
//        tokenValidate(token);
//
//        Member memberByToken = getMemberByToken(token);
//        StudyMember studyMember = StudyMember.builder()
//                .member(memberByToken)
//                .name(memberByToken.getName())
//                .build();
//
//        memberByToken.setStudyMember(studyMember);
//
//        List<StudyMember> studyMembers = new ArrayList<>();
//        studyMembers.add(studyMemberRepository.save(studyMember));
//
//        List<StudyProblem> studyProblems = studyCreate.getStudyProblems().stream()
//                .map(o -> o.toEntity())
//                .collect(Collectors.toList());
//
//        studyProblemRepository.saveAll(studyProblems);
//
//        Study study = Study.builder()
//                .location(studyCreate.getLocation())
//                .penaltyContent(studyCreate.getPenaltyContent())
//                .penaltyTF(studyCreate.getPenaltyTF())
//                .sno(studyCreate.getSno())
//                .targetDate(studyCreate.getTargetDate())
//                .studyMembers(studyMembers)
//                .name(studyCreate.getName())
//                .studyProblems(studyProblems)
//                .build();
//
//        studyMember.setStudy(study);
//
//        for (StudyProblem s : studyProblems) {
//            s.setStudy(study);
//        }
//
//        studyRepository.save(study);
//
//        return modelMapper.map(study, StudyResponse.class);
//    }

//    public StudyResponse deleteStudy(String token,Long studyId){
//        tokenValidate(token);
//        Study study = studyRepository.findById(studyId).orElseThrow(StudyNotFoundException::new);
//        StudyResponse studyResponse = modelMapper.map(study, StudyResponse.class);
//        studyRepository.delete(study);
//        return studyResponse;
//    }

    public StudyMemberResponse deleteStudyMember(String token, Long studyId) {
        tokenValidate(token);
        studyRepository.findById(studyId).orElseThrow(StudyNotFoundException::new);
        StudyMember studyMember = getMemberByToken(token).getStudyMember();
        StudyMemberResponse studyMemberResponse = modelMapper.map(studyMember, StudyMemberResponse.class);
        studyMemberRepository.delete(studyMember);
        return studyMemberResponse;
    }


    public StudyResponse getStudyInfoByToken(String token) {
        tokenValidate(token);
        Member memberByToken = getMemberByToken(token);
        StudyMember studyMember = memberByToken.getStudyMember();

        if (studyMember == null) {
            throw new StudyMemberNotFoundException();
        }
        if (studyMember.getStudy() == null) {
            throw new StudyMemberNotFoundException();
        }
        Study study = studyRepository.findById(studyMember.getStudy().getId()).orElseThrow(StudyNotFoundException::new);
        return modelMapper.map(study, StudyResponse.class);
    }

    public Boolean checkGroup(String token){
        tokenValidate(token);
        Member memberByToken = getMemberByToken(token);
        StudyMember studyMember = memberByToken.getStudyMember();

        if (studyMember == null) {
            return false;
        }

        if (studyMember.getStudy() == null) {
            return false;
        }

        return true;
    }


    public StudyResponse getStudyInfoById(Long studyId) {
        return modelMapper.map(studyRepository.findById(studyId).orElseThrow(StudyNotFoundException::new), StudyResponse.class);
    }

    public StudyResponse getStudyInfoBySNO(Long SNO) {
        return modelMapper.map(studyRepository.findBySno(SNO).orElseThrow(StudyNotFoundException::new), StudyResponse.class);
    }

    public List<StudyMemberResponse> getStudyMemberList(Long studyId) {
        Study study = studyRepository.findById(studyId).orElseThrow(StudyNotFoundException::new);

        return study.getStudyMembers().stream()
                .map(o -> modelMapper.map(o, StudyMemberResponse.class))
                .collect(Collectors.toList());
    }

    public List<StudyPlanResponse> getStudyPlanList(Long studyId) {
        Study study = studyRepository.findById(studyId).orElseThrow(StudyNotFoundException::new);

        return study.getStudyPlans().stream()
                .map(o -> modelMapper.map(o, StudyPlanResponse.class))
                .collect(Collectors.toList());
    }

    public List<StudyResponse> getStudyList() {
        return studyRepository.findAll().stream()
                .map(o -> modelMapper.map(o, StudyResponse.class))
                .collect(Collectors.toList());
    }


    void tokenValidate(String token) {
        if (!jwtProvider.validateToken(token))
            throw new CAuthenticationEntryPointException();
    }

    Member getMemberByToken(String token) {
        String userPk = this.jwtProvider.getUserPk(token);
        Long userLongPk = Long.parseLong(userPk);
        return this.memberRepository.findById(userLongPk).orElseThrow(MemberNotFoundCException::new);
    }



}
