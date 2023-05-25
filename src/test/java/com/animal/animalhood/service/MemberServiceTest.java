package com.animal.animalhood.service;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void joinTest() throws Exception {
        //given
        Member member = new Member();
        member.setSittingPoint(0);
        member.setLoginId("loginTest");

        //when
        Long savedId = memberService.join(member);

        System.out.println("savedId " + savedId);
        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }
}