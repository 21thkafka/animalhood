package com.animal.animalhood.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
public class Member implements UserDetails {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String email;

    private String password;

    private String name;

    private String address;

    private String mobile;

    private int sittingPoint;

    private LocalDateTime regDate;

    @OneToMany(mappedBy = "member")
    private List<SittingOrder> sittingOrders = new ArrayList<>();

    public void addSittingOrder(SittingOrder sittingOrder) {
        sittingOrders.add(sittingOrder);
        sittingOrder.setMember(this);
    }

    public void removeSittingOrder(SittingOrder sittingOrder) {
        sittingOrders.remove(sittingOrder);
        sittingOrder.setMember(null);
    }

    @OneToMany(mappedBy = "member")
    private List<Pet> pet = new ArrayList<>();

    @OneToMany (mappedBy = "member")
    private List<SitterPet> sitterPets = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("member"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
