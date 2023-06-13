package com.animal.animalhood.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public class addMemberRequest {

    private Long id;
    private String loginId;
    private String name;
    private String password;
    private String eMail;
    private String mobile;
    private String address;

    private int sittingPoint;

    private LocalDateTime regDate;

    public Long getId() {
        return this.id;
    }
}
