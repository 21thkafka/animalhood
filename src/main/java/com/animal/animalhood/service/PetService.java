package com.animal.animalhood.service;

import com.animal.animalhood.domain.Image;
import com.animal.animalhood.domain.Member;
import com.animal.animalhood.domain.Pet;
import com.animal.animalhood.repository.MemberRepository;
import com.animal.animalhood.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long savePat(Long memberId, String name, int age){
        Member member = memberRepository.findOne(memberId);
        Pet pet = Pet.createPet(member, name, age);
        petRepository.save(pet);
        return pet.getId();
    }

    @Transactional
    public Pet updatePat(Long id, String gubun, String name, int age){
        Pet findPat = petRepository.findOne(id);
        findPat.setPetName(name);
        findPat.setPetAge(age);
        return findPat;
    }

    public Pet findPat(Long id){
        Pet pets = petRepository.findOne(id);
        return pets;
    }

    public List<Pet> findPatMember(Long memberId) {
        List<Pet> pets = petRepository.findPatMember(memberId);
        return pets;
    }

    @Transactional
    public Long saveImg(Image image){
        petRepository.saveImg(image);
        return image.getImgNo();
    }
}
