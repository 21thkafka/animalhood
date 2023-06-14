package com.animal.animalhood.controller;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.dto.addMemberRequest;
import com.animal.animalhood.dto.loginRequest;
import com.animal.animalhood.service.MemberDetailService;
import com.animal.animalhood.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberDetailService memberDetailService;

    @GetMapping("/login")
    public String login(){

        return "login";
    }

 /*   @PostMapping("/login")
    public String loginPost(loginRequest req){
        String id = req.getLoginId();
        String pw = req.getPassword();
        Member member = memberDetailService.loadUserByUsername(id);
        String check = memberService.loginCheck(pw, member.getPassword());
        if(member == null || check.equals("N")){
            return "login";
        }
        return "redirect:/home";
    }*/

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
    @PostMapping("/member")
    public String newMember(addMemberRequest req){
        Member member = new Member();
        member.setName(req.getName());
        member.setPassword(req.getPassword());
        member.setAddress(req.getAddress());
        member.setEmail(req.getEmail());
        member.setMobile(req.getMobile());
        memberService.join(member);
        return "redirect:/login";
    }
}
