package com.animal.animalhood.repository;

import com.animal.animalhood.domain.Pet;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PetRepository {

    private final EntityManager em;

    public void save(Pet pat){
        em.persist(pat);
    }


    public Pet findOne(Long id){
        return em.find(Pet.class, id);
    }

    public List<Pet> findPat(Long patId){
        return em.createQuery("select p from Pet p where patId = :patId", Pet.class)
                .setParameter("patId", patId)
                .getResultList();
    }

    public List<Pet> findPatMember(Long memberId){
        return em.createQuery("select p from Pet p where memberId = :memberId", Pet.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public List<Pet> findAll(){
        return em.createQuery("select p from Pet p", Pet.class)
                .getResultList();
    }
}
