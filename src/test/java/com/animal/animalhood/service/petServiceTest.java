package com.animal.animalhood.service;

import com.animal.animalhood.domain.OrderStatus;
import com.animal.animalhood.domain.SitterPet;
import com.animal.animalhood.domain.SittingOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class petServiceTest {

    @Autowired PetService petService;

    @Test
    @Transactional
    public void findSitterPet(){
        //given
        SitterPet sitterPet = new SitterPet();
        String email = "caudeutsch@gmail.com";
       // Long memberId = 2L;

        //when
        List<SittingOrder> findSitterPet = petService.findSitterPet(email);

        //then
        assertEquals(OrderStatus.PROGRESS, findSitterPet.get(0).getStatus());

    }
}
