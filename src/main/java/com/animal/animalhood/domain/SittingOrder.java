package com.animal.animalhood.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


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

    @OneToMany(mappedBy = "sittingOrder")
    private List<SitterPet> sitterPets = new ArrayList<>();

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "pet_id")
//    private Pet pet;

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
    public static SittingOrder createOrder(Member member, String strDate, String endDate){
        SittingOrder order = new SittingOrder();
        order.setMember(member);

        LocalDateTime strDateTime = parseDate(strDate);
        LocalDateTime endDateTime = parseDate(endDate);

        order.setStatus(OrderStatus.PROGRESS);
        order.setStartDate(strDateTime);
        order.setEndDate(endDateTime);
        order.setRegDate(LocalDateTime.now());
        return order;
    }

    //String->LocalDateTime 변환
    public static LocalDateTime parseDate(String date){

        String dateTimeString = date.replace("T", " ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);


        return dateTime;
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
