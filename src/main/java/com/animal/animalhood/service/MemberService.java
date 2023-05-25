package com.animal.animalhood.service;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.LongSummaryStatistics;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
 //       validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    @Transactional(readOnly = true)
    private void validateDuplicateMember(Member member) {
        List<Member> findMember = memberRepository.findByLoginId(member.getLoginId());
        if(!findMember.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    
    //회원 전체 조회
    public List<Member> findMembers(){ return memberRepository.findAll(); }

    public Member findOne(Long memberId) { return memberRepository.findOne(memberId);}

}
