package com.animal.animalhood.service;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.dto.addMemberRequest;
import com.animal.animalhood.repository.MemberRepository;
import com.animal.animalhood.repository.PatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.LongSummaryStatistics;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PatRepository patRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        loadUserByUsername(member);
        String pw = member.getPassword();
        member.setPassword(bCryptPasswordEncoder.encode(pw));
        member.setRegDate(LocalDateTime.now());
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional(readOnly = true)
    private void loadUserByUsername(Member member) {
        List<Member> findMember = memberRepository.findByLoginId(member.getLoginId());
        if(!findMember.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원정보 수정
    @Transactional
    public void updateMember(Member member){

    }

    //회원 전체 조회
    public List<Member> findMembers(){ return memberRepository.findAll(); }

    @Transactional
    public Member findOne(Long memberId) { return memberRepository.findOne(memberId);}

}
