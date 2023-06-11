package com.animal.animalhood.repository;

import com.animal.animalhood.domain.SitterPat;
import com.animal.animalhood.domain.SittingOrder;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SittingOrderRepository {

    private final EntityManager em;

    public void save(SittingOrder order) { em.persist(order); }

    public SittingOrder findOne(Long id){
        return em.find(SittingOrder.class, id);
    }

    public List<SittingOrder> findAll() {
        return em.createQuery("select s from SittingOrder s inner join s.member", SittingOrder.class)
                .getResultList();
    }

    public void requestSitting(SitterPat sitter){ em.persist(sitter); }

    public SitterPat findSitter(Long id){
        return em.find(SitterPat.class, id);
    }

    public void delete(SittingOrder order) {
        em.remove(order);
    }
}
