package com.example.JSFW_L_A103.controller;

import com.example.JSFW_L_A103.common.BaseGetSession;
import com.example.JSFW_L_A103.dtos.request.MemberUpdateRequest;
import com.example.JSFW_L_A103.entity.Member;
import com.example.JSFW_L_A103.service.IMemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member/profile")
public class MemberController extends BaseGetSession {


    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping
    public String getProfile(Model model) {
        Member member = (Member) model.getAttribute("memberSession");
        MemberUpdateRequest memberUpdateRequest = mapper.map(member, MemberUpdateRequest.class);
        model.addAttribute("profile", memberUpdateRequest);
        return "profile";
    }

    @PostMapping
    public String updateProfile(@Valid @ModelAttribute("profile") MemberUpdateRequest memberUpdateRequest,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("profile", memberUpdateRequest);
            return "profile";
        }
        HttpSession session = httpServletRequest.getSession();
        Member member = (Member) model.getAttribute("memberSession");
        memberUpdateRequest.setEmail(member.getEmail());
        memberUpdateRequest.setUserName(member.getUserName());
        memberUpdateRequest.setPassword(member.getPassword());
        memberUpdateRequest.setCreatedDate(member.getCreatedDate());
        memberUpdateRequest.setRole(member.getRole());
        String result = memberService.updateMember(memberUpdateRequest);
        session.setAttribute("user", (Member) mapper.map(memberUpdateRequest, Member.class));
        return "redirect:/member/profile";
    }


}
