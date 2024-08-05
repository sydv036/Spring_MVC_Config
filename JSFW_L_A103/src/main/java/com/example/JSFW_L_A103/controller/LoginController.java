package com.example.JSFW_L_A103.controller;

import com.example.JSFW_L_A103.dtos.request.LoginRequest;
import com.example.JSFW_L_A103.dtos.request.MemberAddRequest;
import com.example.JSFW_L_A103.entity.Member;
import com.example.JSFW_L_A103.service.IMemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private IMemberService memberService;

    @Autowired
    private HttpServletRequest httpServletRequest;


    @GetMapping({"", "/login", "/"})
    public String displayLogin(Model model) {
        model.addAttribute("object", new LoginRequest());
        return "login";
    }

    @GetMapping("/register")
    public String displayRegister(Model model) {
        model.addAttribute("member", new MemberAddRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("member") MemberAddRequest memberAddRequest,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("member", memberAddRequest);
            return "register";
        }
        String result = memberService.createMember(memberAddRequest);
        return "redirect:/";
    }
}
