package com.animal.animalhood.service;

import com.animal.animalhood.domain.*;
import com.animal.animalhood.dto.updateSittingOrder;
import com.animal.animalhood.repository.MemberRepository;
import com.animal.animalhood.repository.PetRepository;
import com.animal.animalhood.repository.SittingOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SittingOrderService {

    private final MemberRepository memberRepository;
    private final PetRepository patRepository;
    private final SittingOrderRepository sittingOrderRepository;

    public List<SittingOrder> findList() {
        return sittingOrderRepository.findAll();
    }

    /**
     * 돌봄 요청
     */
    @Transactional
    public Long order(Long memberId, String strDate, String endDate, String detail){
        Member member = memberRepository.findOne(memberId);
        SittingOrder order = SittingOrder.createOrder(member, strDate, endDate);
        order.setDetail(detail);

        sittingOrderRepository.save(order);
        return order.getId();
    }

    /**
     * 상세조회
     */
    public SittingOrder orderDetail(Long orderId){
        SittingOrder order = sittingOrderRepository.findOne(orderId);
        return order;
    }

    /**
     * 수정
     */
    @Transactional
    public SittingOrder orderUpdate(Long orderId, updateSittingOrder request){
        SittingOrder order = sittingOrderRepository.findOne(orderId);

        order.setStartDate(request.getStartDate());
        order.setEndDate(request.getEndDate());
        order.setDetail(request.getDetail());
        return order;
    }

    /**
     * 돌봄 요청 취소
     */
    @Transactional
    public void canselOrder(Long orderId){
        SittingOrder order = sittingOrderRepository.findOne(orderId);
        order.cancel();
    }

    /**
     * 돌봄 요청 삭제
     */
    @Transactional
    public void deleteOrder(Long orderId){
        SittingOrder order = sittingOrderRepository.findOne(orderId);
        sittingOrderRepository.delete(order);
    }

    /**
     * 요청 신청
     */
    @Transactional
    public SitterPet requestSitting(SitterPet sitter){
        sitter.setStatus(OrderStatus.PROGRESS);
        SitterPet savedSitter = sittingOrderRepository.requestSitting(sitter);
    //    return sitter.getId();
        return savedSitter;
    }

    /**
     * 신청목록
     */
    @Transactional
    public List<Member> sitterList(Long id){
        List<Member> sitters = sittingOrderRepository.sitterPetList(id);
        return sitters;
    }

    /**
     * 신청 응답
     */
    @Transactional
    public void responseSitting(SitterPet sitter, OrderStatus status){
        SitterPet getSitter = sittingOrderRepository.findSitter(sitter.getId());
        getSitter.setStatus(status);

    }

}
