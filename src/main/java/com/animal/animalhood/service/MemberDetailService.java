package com.animal.animalhood.service;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public Member loadUserByUsername(String loginId) {
        Member findMember = memberRepository.findByOneLoginId(loginId);
        if(findMember == null){
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
        return findMember;
    }
}
