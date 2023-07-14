package com.animal.animalhood.controller;

import com.animal.animalhood.domain.Member;
import com.animal.animalhood.domain.SitterPet;
import com.animal.animalhood.domain.SittingOrder;
import com.animal.animalhood.dto.addMemberRequest;
import com.animal.animalhood.dto.loginRequest;
import com.animal.animalhood.service.MemberDetailService;
import com.animal.animalhood.service.MemberService;
import com.animal.animalhood.service.PetService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberDetailService memberDetailService;

    private final PetService petService;

    @GetMapping("/login")
    public String login(){

     //   return "login";
        return "oauthLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

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

    @GetMapping("/myPage")
    public String myPage(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String email = userDetails.getUsername();
        UserDetails member = memberDetailService.loadUserByUsername(email);
        model.addAttribute("member", member);

        List<SittingOrder> order = petService.findSitterPet(email);
        model.addAttribute("order", order);

        return "myPage";
    }
}
