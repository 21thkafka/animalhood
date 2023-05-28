package com.animal.animalhood.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
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

    private Date regDate;

    @OneToMany(mappedBy = "member")
    private List<SittingOrder> sittingOrders = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Pat> pat = new ArrayList<>();
}
