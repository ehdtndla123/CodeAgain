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


        studyPlanRepository.save(studyPlan);

        study.getStudyPlans().add(studyPlan);
        for (StudyProblem s : studyProblems) {
            s.setStudyPlan(studyPlan);
            for (StudyMember sm : study.getStudyMembers()) {
                StudyProblem studyPr= StudyProblem.builder()
                        .studyPlan(studyPlan)
                        .studyMember(sm)
                        .subject(s.getSubject())
                        .code(s.getCode())
                        .build();
                studyProblemRepository.save(studyPr);
                sm.getStudyProblems().add(studyPr);
            }
        }

        //studyRepository.save(study);



        return modelMapper.map(studyPlan, StudyPlanResponse.class);
    }

    public StudyProblemResponse createStudyProblem(String token ,StudyProblemAdd studyProblemAdd) {
        tokenValidate(token);
        Study study = studyRepository.findById(studyProblemAdd.getStudyId()).orElseThrow(StudyNotFoundException::new);
        StudyPlan studyPlan = studyPlanRepository.findById(studyProblemAdd.getStudyPlanId()).orElseThrow(StudyPlanNotFoundException::new);
        List<StudyMember> studyMembers = study.getStudyMembers();


        StudyProblem studyProblem = StudyProblem.builder()
                .code(studyProblemAdd.getCode())
                .studyPlan(studyPlan)
                .subject(studyProblemAdd.getSubject())
                .build();
        studyProblemRepository.save(studyProblem);
        studyPlan.getStudyProblems().add(studyProblem);

        for (StudyMember sm : studyMembers) {
            StudyProblem studyPr= StudyProblem.builder()
                    .studyPlan(studyPlan)
                    .studyMember(sm)
                    .subject(studyProblemAdd.getSubject())
                    .code(studyProblemAdd.getCode())
                    .build();
            studyProblemRepository.save(studyPr);
            sm.getStudyProblems().add(studyPr);
        }

        return modelMapper.map(studyProblem, StudyProblemResponse.class);
    }

    protected void addStudyProblem(Study study, StudyPlan plan,String subject,String code) {
        List<StudyMember> studyMembers = study.getStudyMembers();
        for (StudyMember sm : studyMembers) {
            StudyProblem studyProblem= StudyProblem.builder()
                    .subject(subject)
                    .code(code)
                    .studyPlan(plan)
                    .studyMember(sm)
                    .build();
            studyProblemRepository.save(studyProblem);
            sm.getStudyProblems().add(studyProblem);
        }
    }


//    public StudyProblemResponse createStudyProblem(String token, StudyProblemCreate studyProblemCreate) {
//        tokenValidate(token);
//        Study study = studyRepository.findById(studyProblemCreate.getStudyId()).orElseThrow(StudyNotFoundException::new);
//        List<StudyMember> studyMembers = study.getStudyMembers();
//        StudyPlan plan = studyPlanRepository.findById(studyProblemCreate.getStudyPlanId()).orElseThrow(StudyPlanNotFoundException::new);
//
//        for (StudyMember sm : studyMembers) {
//            StudyProblem studyProblem= StudyProblem.builder()
//                    .subject(studyProblemCreate.getSubject())
//                    .code(studyProblemCreate.getCode())
//                    .studyPlan(plan)
//                    .studyMember(sm)
//                    .build();
//            sm.getStudyProblems().add(studyProblem);
//            studyProblemRepository.save(studyProblem);
//        }
//
//        StudyProblem studyProblem= StudyProblem.builder()
//                .subject(studyProblemCreate.getSubject())
//                .code(studyProblemCreate.getCode())
//                .studyPlan(plan)
//                .build();
//
//        studyProblemRepository.save(studyProblem);
//
//        plan.getStudyProblems().add(studyProblem);
//
//        return modelMapper.map(studyProblem, StudyProblemResponse.class);
//    }


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
    public Long getStudyId(String token) {

        Member memberByToken = getMemberByToken(token);
        StudyMember studyMember = memberByToken.getStudyMember();

        if (studyMember == null) {
            throw new StudyMemberNotFoundException();
        }
        if (studyMember.getStudy() == null) {
            throw new StudyMemberNotFoundException();
        }

        return studyMember.getStudy().getId();
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

    public StudyMemberResponse joinStudy(String token,Long sno) {
        tokenValidate(token);
        Study study = studyRepository.findBySno(sno).orElseThrow(StudyNotFoundException::new);
        Member memberByToken = getMemberByToken(token);
        StudyMember studyMember=StudyMember.builder()
                .name(memberByToken.getName())
                .member(memberByToken)
                .study(study)
                .build();


        if(checkGroup(token)){
           throw new RuntimeException("이미 가입 되어 있는 유저입니다.");
        }
        study.getStudyMembers().add(studyMemberRepository.save(studyMember));

        return modelMapper.map(studyMember, StudyMemberResponse.class);
    }


    public StudyProblemResponse updateStudyProblem(String token, Long studyProblemId, StudyProblemUpdate studyProblemUpdate) {
        tokenValidate(token);
        StudyProblem studyProblem = studyProblemRepository.findById(studyProblemId).orElseThrow(StudyProblemNotFoundException::new);
        studyProblem.updateProblem(studyProblemUpdate.getCode(), studyProblemUpdate.getSubject());
        studyProblemRepository.save(studyProblem);
        return modelMapper.map(studyProblem, StudyProblemResponse.class);
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
