package com.animal.animalhood.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter @Getter
@RequiredArgsConstructor
public class addSitterRequest {

    private Long orderId;
    private String email;
}
