package com.animal.animalhood.service;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.repository.MemberRepository;
import com.animal.animalhood.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PetRepository patRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        loadUserByUsername(member.getEmail());
        String pw = member.getPassword();
        member.setPassword(bCryptPasswordEncoder.encode(pw));
        member.setRegDate(LocalDateTime.now());
        memberRepository.save(member);
        return member.getId();
    }

    //회원정보 수정
    @Transactional
    public void updateMember(Member member){

    }

    //회원 전체 조회
    public List<Member> findMembers(){ return memberRepository.findAll(); }

    //회원 조회
    public Member findOne(Long memberId) { return memberRepository.findOne(memberId);}
    @Transactional
    public Member findOne(String email) {
        List<Member> members = memberRepository.findByEmail(email);
        Member member = members.get(0);
        return member;
    }

    public void loadUserByUsername(String email){
        List<Member> checkEmail = memberRepository.findByEmail(email);
        if(!checkEmail.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
