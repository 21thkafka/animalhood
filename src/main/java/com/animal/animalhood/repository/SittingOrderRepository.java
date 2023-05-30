package com.animal.animalhood.repository;

import com.animal.animalhood.domain.SittingOrder;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;

@Repository
@RequiredArgsConstructor
public class SittingOrderRepository {

    private final EntityManager em;

    private void save(SittingOrder order) { em.persist(order); }

    private SittingOrder findAll(Long id) { return em.find(SittingOrder.class, id); };
}
