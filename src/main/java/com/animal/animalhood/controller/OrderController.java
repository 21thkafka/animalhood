package com.animal.animalhood.controller;


import com.animal.animalhood.domain.*;

import com.animal.animalhood.dto.addSitterRequest;
import com.animal.animalhood.dto.sitterApplyResponse;
import com.animal.animalhood.dto.updateSittingOrder;
import com.animal.animalhood.service.MemberDetailService;
import com.animal.animalhood.service.MemberService;

import com.animal.animalhood.service.PetService;
import com.animal.animalhood.service.SittingOrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final SittingOrderService sittingOrderService;
    private final MemberDetailService memberDetailService;
    private final MemberService memberService;

    private final PetService petService;

    //목록
    @GetMapping("/sitting-orders")
    public String sittingOrderList (Model model){
        List<SittingOrder> orders = sittingOrderService.findList();
        model.addAttribute("orders", orders);
        return "sittingOrder/sittingOrderList";
    }

    @GetMapping("/sitting-order")
    public String applyForm ( Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String email = userDetails.getUsername();
        UserDetails member = memberDetailService.loadUserByUsername(email);
        model.addAttribute("member", member);

        return "sittingOrder/sittingOrderForm";
    }

    @PostMapping("/sitting-order")
    public String sittingOrder(@RequestParam("memberId") Long memberId,
                               @RequestParam("detail") String detail,
                               @RequestParam("strDate") String strDate,
                               @RequestParam("endDate") String endDate){

        sittingOrderService.order(memberId, strDate, endDate, detail);
        return "redirect:/sitting-orders";
    }

    //상세조회
    @GetMapping("/sitting-order/detail/{id}")
    public String detailSittingOrder (@PathVariable Long id, Model model){
        SittingOrder order = sittingOrderService.orderDetail(id);
        model.addAttribute("order", order);

        //로그인한 회원 정보
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String email = userDetails.getUsername();
        UserDetails user = memberDetailService.loadUserByUsername(email);
        model.addAttribute("user", user);

        return "sittingOrder/sittingOrderDetail";
    }

    @PutMapping("/sitting-order/detail/{id}")
    public ResponseEntity<UpdateOrderResponse> updateSittingOrder (@PathVariable Long id,
                                                            @RequestBody updateSittingOrder request,
                                                            Model model){
        SittingOrder updatedOrder = sittingOrderService.orderUpdate(id, request);

    //    model.addAttribute("order", updatedOrder);
        UpdateOrderResponse result = new UpdateOrderResponse(updatedOrder.getId(), updatedOrder.getStartDate(), updatedOrder.getEndDate(),updatedOrder.getDetail());

        return ResponseEntity.ok()
                .body(result);
    }

    @DeleteMapping("/sitting-order/detail/{id}")
    public ResponseEntity<Void> deleteSittingOrder(@PathVariable Long id){
        sittingOrderService.deleteOrder(id);

        return ResponseEntity.ok()
                .build();
    }

    //돌봄 요청 신청
    @PostMapping("/sitting-order/sitter")
    public ResponseEntity<Void> applySitter(@RequestBody addSitterRequest req){
        Member member = memberService.findOne(req.getEmail());
        SittingOrder order = sittingOrderService.orderDetail(req.getOrderId());

        SitterPet sitter = new SitterPet();
        sitter.setMember(member);
        sitter.setSittingOrder(order);

        //날짜 등록
        LocalDateTime regDate = LocalDateTime.now();
        sitter.setRegDate(regDate);
        SitterPet savedSitter = sittingOrderService.requestSitting(sitter);
        return ResponseEntity.ok()
                .build();
    }

    //돌봄 요청 신청자 조회
    @GetMapping("/sitting-order/sitters/{id}")
    public String sittersList(@PathVariable Long id, Model model){

        List<Member> sitterPets = sittingOrderService.sitterList(id);
        model.addAttribute("sitter", sitterPets);

        return "sittingOrder/sittersList";
    }

    //돌봄 요청 신청자 반려동물 리스트 조회
    @GetMapping("/sitting-order/sitter/pet/{id}")
    public String sitterPetList(@PathVariable Long id, Model model){
        List<Pet> petMember = petService.findPetMember(id);
        model.addAttribute("pet", petMember);
        return "sittingOrder/sitterPetList";
    }

    //돌봄 요청 신청자 응답
    @PutMapping("/sitting-order/sitter/{id}/")
    public ResponseEntity<sitterApplyResponse> sitterResponse(@RequestBody sitterApplyResponse rep, Model model){
        Long id = rep.getId();
        SitterPet sitter = sittingOrderService.findSitter(id);
        String answer = rep.getStatus();
        OrderStatus status = null;
        if(answer.equals("Y")){
            status = OrderStatus.ACCEPT;
        } else if(answer.equals("N")){
            status = OrderStatus.REJECT;
        }

        sittingOrderService.responseSitting(sitter, status);
        
        return ResponseEntity.ok().build();
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
