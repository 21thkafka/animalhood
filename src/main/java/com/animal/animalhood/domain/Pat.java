package com.animal.animalhood.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pat {

    @Id
    @GeneratedValue
    @Column(name="pat_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private int petAge;

    private String petName;

    private String gubun;

    @OneToMany(mappedBy = "pat")
    private List<Image> image = new ArrayList<>();
}
