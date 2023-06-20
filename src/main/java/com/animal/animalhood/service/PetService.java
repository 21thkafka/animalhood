package com.animal.animalhood.service;

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

    public List<Pet> findPat(Long id){
        List<Pet> pats = petRepository.findPat(id);
        return pats;
    }

    public List<Pet> findPatMember(Long memberId) {
        List<Pet> pats = petRepository.findPatMember(memberId);
        return pats;
    }
}
