package com.animal.animalhood.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SitterPet {

    @Id @GeneratedValue
    @Column(name="sitting_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private SittingOrder sittingOrder;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime regDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
