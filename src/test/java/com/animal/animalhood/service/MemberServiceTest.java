package com.animal.animalhood.service;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.domain.Pat;
import com.animal.animalhood.repository.MemberRepository;
import com.animal.animalhood.repository.PatRepository;
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
    PatService patService;
    @Autowired
    PatRepository patRepository;

    @Autowired MemberRepository memberRepository;

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

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    @Transactional
    public void addPatTest() throws Exception {
        //given
        Pat pat = new Pat();
        pat.setPetName("보리");
        pat.setPetAge(2);

        //when
        joinTest();
        pat.setMember(memberRepository.findOne(0L));
        Long savedId = patService.savePat(pat);

        //then
        assertEquals(pat, patRepository.findOne(savedId));
    }
}