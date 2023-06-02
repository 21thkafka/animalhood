package com.animal.animalhood.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SittingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sitting_id")
    private SitterPat sitterPat;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "pat_id")
    private Pat pat;

    private String detail;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime regDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setMember(Member member){
        this.member = member;
        member.getSittingOrders().add(this);
    }

    //==생성 메소드==//
    public static SittingOrder createOrder(Member member){
        SittingOrder order = new SittingOrder();
        order.setMember(member);

        order.setStatus(OrderStatus.PROGRESS);
        order.setRegDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//
    /**
     * 돌봄 요청 취소
     */
    public void cancel(){
        if(status == OrderStatus.ACCEPT){
            throw new IllegalStateException("이미 돌봄 요청 과정을 수락한 상태입니다");
        }
        this.setStatus(OrderStatus.CANCEL);
    }

}
