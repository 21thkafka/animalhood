package com.animal.animalhood.controller;


import com.animal.animalhood.domain.SittingOrder;

import com.animal.animalhood.dto.updateSittingOrder;
import com.animal.animalhood.service.MemberService;

import com.animal.animalhood.service.SittingOrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

        sittingOrderService.order(memberId, strDate, endDate, detail);
        return "redirect:/sittingOrders";
    }

    //상세조회
    @GetMapping("/sittingOrder/detail/{id}")
    public String detailSittingOrder (@PathVariable Long id, Model model){
        SittingOrder order = sittingOrderService.orderDetail(id);
        model.addAttribute("order", order);

        return "sittingOrder/sittingOrderDetail";
    }

    @PutMapping("/sittingOrder/detail/{id}")
    public ResponseEntity<UpdateOrderResponse> updateSittingOrder (@PathVariable Long id,
                                                            @RequestBody updateSittingOrder request,
                                                            Model model){


        SittingOrder updatedOrder = sittingOrderService.orderUpdate(id, request);

    //    model.addAttribute("order", updatedOrder);
        UpdateOrderResponse result = new UpdateOrderResponse(updatedOrder.getId(), updatedOrder.getStartDate(), updatedOrder.getEndDate(),updatedOrder.getDetail());

        return ResponseEntity.ok()
                .body(result);
    }

    @Data
    @AllArgsConstructor
    static class UpdateOrderResponse {
        private Long id;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String Detail;
    }

}
