package com.animal.animalhood.service;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.domain.OrderStatus;
import com.animal.animalhood.domain.SitterPat;
import com.animal.animalhood.domain.SittingOrder;
import com.animal.animalhood.repository.SittingOrderRepository;
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
public class SittingOrderServiceTest {

    @Autowired EntityManager em;
    @Autowired SittingOrderService sittingOrderService;
    @Autowired SittingOrderRepository sittingOrderRepository;

    @Test
    public void sittingOrderTest() throws Exception {
        //given
        Member member = createMember("testId1", "testName1");

        //when
        Long orderId = sittingOrderService.order(member.getId(), "20230603", "20230606");

        SittingOrder getOrder = sittingOrderRepository.findOne(orderId);
        //then
        assertEquals(OrderStatus.PROGRESS, getOrder.getStatus());
        assertEquals(member.getId(), getOrder.getMember().getId());
        assertEquals(member.getName(), getOrder.getMember().getName());

    }

    @Test
    public void sittingCancelTest() throws Exception {
        //given
        Member member = createMember("testId1", "testName1");
        Long orderId = sittingOrderService.order(member.getId(), "20230603", "20230606");

        //when
        sittingOrderService.canselOrder(orderId);

        //then
        SittingOrder getOrder = sittingOrderRepository.findOne(orderId);
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
    }

    @Test
    public void sittingPatTest() throws Exception {
        //given
        Member member = createMember("testId1", "testName1");
        Long orderId = sittingOrderService.order(member.getId(), "20230603", "20230606");
        SittingOrder order = sittingOrderRepository.findOne(orderId);
        SitterPat sitter = new SitterPat();
        sitter.setSittingOrder(order);

        Member member2 = createMember("testSitter", "testSitterName");
        sitter.setMember(member2);

        //when
        Long getSitterId = sittingOrderService.requestSitting(sitter);

        //then
        assertEquals(orderId, sittingOrderRepository.findSitter(getSitterId).getSittingOrder().getId());
        assertEquals(member2.getId(), sittingOrderRepository.findSitter(getSitterId).getMember().getId());
        assertEquals(OrderStatus.PROGRESS, sittingOrderRepository.findSitter(getSitterId).getStatus());
    }

    @Test
    public void answerTest() throws Exception {
        //given
        Member member = createMember("testId1", "testName1");
        Long orderId = sittingOrderService.order(member.getId(), "20230603", "20230606");
        SittingOrder order = sittingOrderRepository.findOne(orderId);
        SitterPat sitter = new SitterPat();
        sitter.setSittingOrder(order);

        Member member2 = createMember("testSitter", "testSitterName");
        sitter.setMember(member2);

        //when
        Long getSitterId = sittingOrderService.requestSitting(sitter);
        SitterPat getSitter = sittingOrderRepository.findSitter(getSitterId);
        sittingOrderService.responseSitting(getSitter, OrderStatus.REJECT);

        //then
        assertEquals(OrderStatus.REJECT, getSitter.getStatus());
    }

    private Member createMember(String id, String name) {
        Member member = new Member();
        member.setLoginId(id);
        member.setName(name);
        member.setSittingPoint(0);
        em.persist(member);
        return member;
    }
}