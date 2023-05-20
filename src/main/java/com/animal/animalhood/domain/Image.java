package com.animal.animalhood.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
public class Image {

    @Id @GeneratedValue
    @Column(name="petImageNo")
    private Long imgNo;

    private String originalName;

    private String originalPath;

    private String serverName;

    private String serverPath;

    private Date regDate;
}
