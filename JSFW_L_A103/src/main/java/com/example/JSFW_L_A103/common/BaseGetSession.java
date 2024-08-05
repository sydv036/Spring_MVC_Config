package com.example.JSFW_L_A103.common;

import com.example.JSFW_L_A103.entity.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseGetSession {
    @Autowired
    private HttpServletRequest httpServletRequest;

    @ModelAttribute("memberSession")
    public Member getMemberSession() {
        HttpSession session = httpServletRequest.getSession();
        Member member = (Member) session.getAttribute("user");
        return member;
    }
}
