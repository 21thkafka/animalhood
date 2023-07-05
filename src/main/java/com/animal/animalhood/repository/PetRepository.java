package com.animal.animalhood.repository;

import com.animal.animalhood.domain.Image;
import com.animal.animalhood.domain.Pet;
import com.animal.animalhood.domain.SitterPet;
import com.animal.animalhood.domain.SittingOrder;
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

    public List<Pet> findPetMember(Long id){
        return em.createQuery("select p from Pet p inner join p.member m where m.id = :id", Pet.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Pet> findAll(){
        return em.createQuery("select p from Pet p", Pet.class)
                .getResultList();
    }

    public void saveImg(Image image){
        em.persist(image);
    }

    public List<SittingOrder> findSitterPet(String email){
        return em.createQuery("select o from SittingOrder o" +
                " join fetch o.sitterPets p" +
                " join fetch p.member m where m.email = :email", SittingOrder.class)
                .setParameter("email", email)
                .getResultList();


    }
}
