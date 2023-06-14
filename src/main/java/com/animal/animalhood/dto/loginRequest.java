package com.animal.animalhood.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class loginRequest {
    private String loginId;
    private String password;
}
