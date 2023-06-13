package com.animal.animalhood.controller;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.dto.addMemberRequest;
import com.animal.animalhood.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
    @PostMapping("/member")
    public String newMember(addMemberRequest req){
        Member member = new Member();
        member.setName(req.getName());
        member.setLoginId(req.getLoginId());
        member.setPassword(req.getPassword());
        member.setAddress(req.getAddress());
        member.setEMail(req.getEMail());
        member.setMobile(req.getMobile());
        memberService.join(member);
        return "redirect:/login";
    }
}
