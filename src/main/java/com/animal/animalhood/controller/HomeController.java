package com.animal.animalhood.controller;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.domain.SittingOrder;
import com.animal.animalhood.service.MemberService;
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

    private final SittingOrderService sittingOrderService;

    @GetMapping("/")
    public String home(Model model){

        //test id 유저 디폴트로 입력
        Member testMember = new Member();
        testMember.setLoginId("testId");
        testMember.setName("testName");
        testMember.setSittingPoint(0);
        memberService.join(testMember);

        List<SittingOrder> orders = sittingOrderService.findList();
        model.addAttribute("orders", orders);

        return  "sittingOrder/sittingOrderList";
    }
}
