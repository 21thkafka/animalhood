package com.animal.animalhood.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pat {

    @Id
    @GeneratedValue
    @Column(name="pat_id")
    private Long id;

    private Member member;

    private int petAge;

    private String petName;

    private Image image;
}
