package com.animal.animalhood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class pagingDto {
    private int totalCnt;
    private int endPage;

}
