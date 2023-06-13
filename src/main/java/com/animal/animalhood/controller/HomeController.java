package com.animal.animalhood.controller;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.domain.Pat;
import com.animal.animalhood.domain.SittingOrder;
import com.animal.animalhood.service.MemberService;
import com.animal.animalhood.service.PatService;
import com.animal.animalhood.service.SittingOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final PatService patService;
    private final SittingOrderService sittingOrderService;

    @GetMapping("/")
    public String home(Model model){

        //test id 유저 디폴트로 입력
    /*    Member testMember = new Member();
        testMember.setLoginId("testId");
        testMember.setName("testName");
        testMember.setSittingPoint(0);
        memberService.join(testMember);*/

        //test id 유저의 펫 입력
   /*     Pat testPat = new Pat();
        testPat.setMember(testMember);
        testPat.setPetName("구름이");
        testPat.setPetAge(12);
        testPat.setGubun("강아지");
        Long savePat = patService.savePat(testPat);*/

        //test order 입력
    //    sittingOrderService.order(1L, "2023-06-10T19:27", "2023-06-12T19:27", "test text");

        return  "home";
    }
}
