package com.animal.animalhood.controller;

import com.animal.animalhood.service.MemberDetailService;
import com.animal.animalhood.service.MemberService;
import com.animal.animalhood.service.PetService;
import com.animal.animalhood.service.SittingOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberDetailService memberDetailService;
    private final SittingOrderService sittingOrderService;

    @GetMapping("/")
    public String home(Model model){

        //test id 유저의 펫 입력
   /*     Pat testPat = new Pat();
        testPat.setMember(testMember);
        testPat.setPetName("구름이");
        testPat.setPetAge(12);
        testPat.setGubun("강아지");
        Long savePat = patService.savePat(testPat);*/

        //test order 입력
    //    sittingOrderService.order(1L, "2023-06-10T19:27", "2023-06-12T19:27", "test text");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String email = userDetails.getUsername();
        UserDetails member = memberDetailService.loadUserByUsername(email);
        model.addAttribute("member", member);

        return  "home";
    }
}
