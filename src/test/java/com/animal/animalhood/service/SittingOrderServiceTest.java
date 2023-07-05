package com.animal.animalhood.service;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.domain.OrderStatus;
import com.animal.animalhood.domain.SitterPet;
import com.animal.animalhood.domain.SittingOrder;
import com.animal.animalhood.repository.SittingOrderRepository;
import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Long orderId = sittingOrderService.order(member.getId(), "20230603", "20230606", "testDtail");

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
        Long orderId = sittingOrderService.order(member.getId(), "20230603", "20230606", "testDtail");

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
        Long orderId = sittingOrderService.order(member.getId(), "20230603", "20230606", "testDtail");
        SittingOrder order = sittingOrderRepository.findOne(orderId);
        SitterPet sitter = new SitterPet();
        sitter.setSittingOrder(order);

        Member member2 = createMember("testSitter", "testSitterName");
        sitter.setMember(member2);

        //when
        SitterPet getSitter = sittingOrderService.requestSitting(sitter);

        //then
        assertEquals(orderId, getSitter.getSittingOrder().getId());
        assertEquals(member2.getId(), getSitter.getMember().getId());
        assertEquals(OrderStatus.PROGRESS, getSitter.getStatus());
    }

    @Test
    public void answerTest() throws Exception {
        //given
        Member member = createMember("testId1", "testName1");
        Long orderId = sittingOrderService.order(member.getId(), "20230603", "20230606", "testDtail");
        SittingOrder order = sittingOrderRepository.findOne(orderId);
        SitterPet sitter = new SitterPet();
        sitter.setSittingOrder(order);

        Member member2 = createMember("testSitter", "testSitterName");
        sitter.setMember(member2);

        //when
        SitterPet getSitterId = sittingOrderService.requestSitting(sitter);
        //SitterPet getSitter = sittingOrderRepository.findSitter(getSitterId);
        sittingOrderService.responseSitting(getSitterId, OrderStatus.REJECT);

        //then
        assertEquals(OrderStatus.REJECT, getSitterId.getStatus());
    }

    @Test
    public void sitterListTest() throws Exception {
        //given
        Long id = 1L;

        //when
        List<Member> sitters = sittingOrderService.sitterList(id);

        //then
        assertEquals("신동석", sitters.get(0).getName());
    }

    @Test
    public void updateRejectTest(){
        //given
        Long id = 1L;
        SitterPet sitter = sittingOrderService.findSitter(id);
        OrderStatus status = OrderStatus.ACCEPT;

        //when
        sittingOrderService.responseSitting(sitter, status);
        SitterPet afterSitter = sittingOrderService.findSitter(id);
        SitterPet afterRejectSitter = sittingOrderService.findSitter(50052L);
        SitterPet afterRejectSitter2 = sittingOrderService.findSitter(50053L);

        //then
        assertEquals(OrderStatus.ACCEPT, afterSitter.getStatus());
        assertEquals(OrderStatus.REJECT, afterRejectSitter.getStatus());
        assertEquals(OrderStatus.REJECT, afterRejectSitter2.getStatus());
    }

    private Member createMember(String email, String name) {
        Member member = new Member();
        member.setEmail(email);
        member.setName(name);
        member.setSittingPoint(0);
        em.persist(member);
        return member;
    }


}