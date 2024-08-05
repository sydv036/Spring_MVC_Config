package com.example.JSFW_L_A103.infastructor.security;

import com.example.JSFW_L_A103.entity.Member;
import com.example.JSFW_L_A103.service.IMemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public class UserDetailServiceCustom implements UserDetailsService {

    @Autowired
    private IMemberService memberService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.findByEmail(username);
        if (member == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailCustom(member);
    }
}
