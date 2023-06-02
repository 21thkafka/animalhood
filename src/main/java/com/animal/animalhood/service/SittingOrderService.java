package com.animal.animalhood.service;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.domain.OrderStatus;
import com.animal.animalhood.domain.SitterPat;
import com.animal.animalhood.domain.SittingOrder;
import com.animal.animalhood.repository.MemberRepository;
import com.animal.animalhood.repository.PatRepository;
import com.animal.animalhood.repository.SittingOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SittingOrderService {

    private final MemberRepository memberRepository;
    private final PatRepository patRepository;
    private final SittingOrderRepository sittingOrderRepository;

    /**
     * 돌봄 요청
     */
    @Transactional
    public Long order(Long memberId){
        Member member = memberRepository.findOne(memberId);
        SittingOrder order = SittingOrder.createOrder(member);
        sittingOrderRepository.save(order);
        return order.getId();
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
     * 요청 신청
     */
    public Long requestSitting(SitterPat sitter){
        sitter.setStatus(OrderStatus.PROGRESS);
        sittingOrderRepository.requestSitting(sitter);
        return sitter.getId();
    }

    /**
     * 신청 응답
     */
    public void responseSitting(SitterPat sitter, OrderStatus status){
        SitterPat getSitter = sittingOrderRepository.findSitter(sitter.getId());
        getSitter.setStatus(status);

    }

}
