package com.animal.animalhood.service;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.repository.MemberRepository;
import com.animal.animalhood.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //OAuth2로 로그인시 사용
    public Member findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
