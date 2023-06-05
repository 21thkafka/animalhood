package com.animal.animalhood.controller;


import com.animal.animalhood.domain.SittingOrder;

import com.animal.animalhood.service.MemberService;

import com.animal.animalhood.service.SittingOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final SittingOrderService sittingOrderService;
    private final MemberService memberService;
//    private final PatService patService;

    //목록
    @GetMapping("/sittingOrders")
    public String sittingOrderList (Model model){
        List<SittingOrder> orders = sittingOrderService.findList();
        model.addAttribute("orders", orders);
        return "sittingOrder/sittingOrderList";
    }

    @GetMapping("/sittingOrder")
    public String applyForm ( Model model){

//        List<Pat> pats = patService.findPat();
//        Member member = memberService.findOne();

        return "sittingOrder/sittingOrderForm";
    }

    @PostMapping("/sittingOrder")
    public String sittingOrder(@RequestParam("memberId") Long memberId,
                               @RequestParam("detail") String detail,
                               @RequestParam("strDate") String strDate,
                               @RequestParam("endDate") String endDate){

     //   strDate = strDate + " 00:00:00.000";
     //   endDate= endDate + " 00:00:00.000";

        sittingOrderService.order(memberId, strDate, endDate, detail);
        return "redirect:/sittingOrders";
    }

    //수정하기
    @GetMapping("/sittingOrder/detail/{id}")
    public String updateSittingOrder (@PathVariable Long id, Model model){
        SittingOrder order = sittingOrderService.orderDetail(id);
        model.addAttribute("order", order);

        return "sittingOrder/sittingOrderDetail";
    }
}
