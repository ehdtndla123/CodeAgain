package com.note.plannerweb.study.service;

import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.except.CAuthenticationEntryPointException;
import com.note.plannerweb.except.MemberNotFoundCException;
import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.member.repository.MemberRepository;
import com.note.plannerweb.study.domain.Study;
import com.note.plannerweb.study.domain.StudyMember;
import com.note.plannerweb.study.domain.StudyProblem;
import com.note.plannerweb.study.dto.StudyCreate;
import com.note.plannerweb.study.dto.StudyMemberResponse;
import com.note.plannerweb.study.dto.StudyProblemCreate;
import com.note.plannerweb.study.dto.StudyResponse;
import com.note.plannerweb.study.repository.StudyMemberRepository;
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
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    private final JwtProvider jwtProvider;



    public StudyResponse createStudy(String token,StudyCreate studyCreate) {
        tokenValidate(token);

        Member memberByToken = getMemberByToken(token);
        StudyMember studyMember = createStudyMember(memberByToken);

        List<StudyMember> studyMembers = new ArrayList<>();
        studyMembers.add(studyMemberRepository.save(studyMember));

        List<StudyProblem> studyProblems = studyCreate.getStudyProblems().stream()
                .map(o -> o.toEntity())
                .collect(Collectors.toList());

        studyProblemRepository.saveAll(studyProblems);

        Study study=Study.builder()
                .location(studyCreate.getLocation())
                .penaltyContent(studyCreate.getPenaltyContent())
                .penaltyTF(studyCreate.getPenaltyTF())
                .SNO(studyCreate.getSNO())
                .targetDate(studyCreate.getTargetDate())
                .studyMembers(studyMembers)
                .studyProblems(studyProblems)
                .build();

        studyMember.setStudy(study);

        for (StudyProblem s : studyProblems) {
            s.setStudy(study);
        }

        studyRepository.save(study);

        return modelMapper.map(study, StudyResponse.class);
    }

    

    StudyMember createStudyMember(Member member) {
        return StudyMember.builder()
                .name(member.getName())
                .member(member)
                .build();
    }








    void tokenValidate(String token){
        if(!jwtProvider.validateToken(token))
            throw new CAuthenticationEntryPointException();
    }
    Member getMemberByToken(String token){
        String userPk = this.jwtProvider.getUserPk(token);
        Long userLongPk=Long.parseLong(userPk);
        return this.memberRepository.findById(userLongPk).orElseThrow(MemberNotFoundCException::new);
    }



}
