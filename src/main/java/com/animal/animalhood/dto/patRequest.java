package com.animal.animalhood.dto;

import com.animal.animalhood.domain.Image;
import com.animal.animalhood.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class patRequest {

    private Long patId;
    private String name;
    private int age;

    private Member member;
    //    private Image image;
    private List<Image> imageList;

}
