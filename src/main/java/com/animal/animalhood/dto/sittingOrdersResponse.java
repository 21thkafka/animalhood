package com.animal.animalhood.dto;

import com.animal.animalhood.domain.SittingOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class sittingOrdersResponse {

    private final Long id;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public sittingOrdersResponse(SittingOrder orders) {
        this.id = orders.getId();
        this.startDate = orders.getStartDate();
        this.endDate = orders.getEndDate();
    }
}
