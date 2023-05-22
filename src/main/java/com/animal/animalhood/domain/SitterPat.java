package com.animal.animalhood.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SitterPat {

    @Id @GeneratedValue
    @Column(name="sitting_id")
    private Long id;

    @OneToOne(mappedBy = "sitterPat", fetch = LAZY)
    private SittingOrder sittingOrder;

    private Date regDate;

}
