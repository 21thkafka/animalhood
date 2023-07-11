package com.animal.animalhood.service;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.domain.Pet;
import com.animal.animalhood.repository.MemberRepository;
import com.animal.animalhood.repository.PetRepository;
import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    PetService patService;
    @Autowired
    PetRepository patRepository;

    @Autowired MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void joinTest() throws Exception {
        //given
   //    addMemberRequest req = new addMemberRequest();
        Member member = new Member();
        member.setSittingPoint(0);
        member.setEmail("loginTest@email.com");
        member.setPassword("test123");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    @Transactional
    public void addPatTest() throws Exception {
        //given
        Pet pat = new Pet();
        pat.setPetName("보리");
        pat.setPetAge(2);

        //when
        joinTest();
        pat.setMember(memberRepository.findOne(0L));
        Long savedId = patService.savePat(0L, "보리", "고양이", 2);

        //then
        assertEquals(pat, patRepository.findOne(savedId));
    }
}