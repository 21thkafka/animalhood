package com.animal.animalhood.dto;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.domain.SittingOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
public class updateSittingOrder {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String detail;

}
