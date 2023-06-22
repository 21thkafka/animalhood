package com.animal.animalhood.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet {

    @Id
    @GeneratedValue
    @Column(name="pet_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private int petAge;

    private String petName;

    private String gubun;

//    @OneToMany(mappedBy = "pet")
//    private List<Image> image = new ArrayList<>();

    @OneToOne(mappedBy = "pet", fetch = LAZY)
    private Image image;

    public static Pet createPet(Member member, String name, int age){
        Pet pet = new Pet();
        pet.setPetName(name);
        pet.setPetAge(age);
        pet.setMember(member);
        return pet;
    }
}
