package com.animal.animalhood.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SittingOrder {

    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    private Member member;

    private String detail;

    private Date startDate;

    private Date endDate;

    private Date regDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
