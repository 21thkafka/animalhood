package com.animal.animalhood.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Image {

    @Id @GeneratedValue
    @Column(name="petImageNo")
    private Long imgNo;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "pat_id")
    private Pat pat;

    private String originalName;

    private String originalPath;

    private String serverName;

    private String serverPath;

    private LocalDateTime regDate;
}
