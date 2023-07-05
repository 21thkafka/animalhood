package com.animal.animalhood.dto;

import com.animal.animalhood.domain.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class sitterApplyResponse {

    private Long id;
    private String status;
}
