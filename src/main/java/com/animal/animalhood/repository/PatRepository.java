package com.animal.animalhood.repository;

import com.animal.animalhood.domain.Pat;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PatRepository {

    private final EntityManager em;

    public void save(Pat pat){
        em.persist(pat);
    }


    public Pat findOne(Long id){
        return em.find(Pat.class, id);
    }

    public List<Pat> findAll(){
        return em.createQuery("select p from Pat p", Pat.class)
                .getResultList();
    }
}
