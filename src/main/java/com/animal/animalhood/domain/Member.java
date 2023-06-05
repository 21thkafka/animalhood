package com.animal.animalhood.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String loginId;

    private String name;

    private String address;

    private String mobile;

    private String eMail;

    private int sittingPoint;

    private LocalDateTime regDate;

    @OneToMany(mappedBy = "member")
    private List<SittingOrder> sittingOrders = new ArrayList<>();

    public void addSittingOrder(SittingOrder sittingOrder) {
        sittingOrders.add(sittingOrder);
        sittingOrder.setMember(this);
    }

    public void removeSittingOrder(SittingOrder sittingOrder) {
        sittingOrders.remove(sittingOrder);
        sittingOrder.setMember(null);
    }

    @OneToMany(mappedBy = "member")
    private List<Pat> pat = new ArrayList<>();

    @OneToMany (mappedBy = "member")
    private List<SitterPat> sitterPats = new ArrayList<>();
}
