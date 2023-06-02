package com.animal.animalhood.repository;

import com.animal.animalhood.domain.SitterPat;
import com.animal.animalhood.domain.SittingOrder;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;

@Repository
@RequiredArgsConstructor
public class SittingOrderRepository {

    private final EntityManager em;

    public void save(SittingOrder order) { em.persist(order); }

    public SittingOrder findOne(Long id){
        return em.find(SittingOrder.class, id);
    }

    private SittingOrder findAll(Long id) { return em.find(SittingOrder.class, id); };

    public void requestSitting(SitterPat sitter){ em.persist(sitter); }

    public SitterPat findSitter(Long id){
        return em.find(SitterPat.class, id);
    }
}
