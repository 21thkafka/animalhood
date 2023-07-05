package com.animal.animalhood.repository;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.domain.OrderStatus;
import com.animal.animalhood.domain.SitterPet;
import com.animal.animalhood.domain.SittingOrder;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
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

    public SitterPet requestSitting(SitterPet sitter){
        em.persist(sitter);
        return sitter;
    }

    public SitterPet findSitter(Long id){ return em.find(SitterPet.class, id); }

    public void delete(SittingOrder order) {
        em.remove(order);
    }

    public List<Member> sitterPetList (Long id) {
        return em.createQuery("select m from Member m join fetch m.sitterPets p" +
                " join fetch p.sittingOrder o " +
                " where o.id = :id", Member.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void rejectUpdate(Long id){
        OrderStatus status = OrderStatus.REJECT;
        em.createQuery("update SitterPet p set p.status = :status where p.id != :id")
                .setParameter("id", id)
                .setParameter("status", status)
                .executeUpdate();
        em.clear(); //벌크 연산 수행 후 영속성 컨텍스트 초기화
    }
}
