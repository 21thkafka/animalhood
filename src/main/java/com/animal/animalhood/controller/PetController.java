package com.animal.animalhood.controller;


import com.animal.animalhood.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PetController {

    final private PetService patService;

    @PostMapping("/pet")
    public String savePat (@RequestParam("memberId") Long memberId,
                           @RequestParam("petName") String name,
                           @RequestParam("petAge") int age){
        patService.savePat(memberId, name, age);
        return "redirect:/myPage";
    }
}
