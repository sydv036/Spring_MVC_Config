package com.example.JSFW_L_A103.service.impl;

import com.example.JSFW_L_A103.common.ValidationCommon;
import com.example.JSFW_L_A103.dtos.request.MemberAddRequest;
import com.example.JSFW_L_A103.dtos.request.MemberUpdateRequest;
import com.example.JSFW_L_A103.entity.Member;
import com.example.JSFW_L_A103.repository.IMemberRepository;
import com.example.JSFW_L_A103.service.IMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private IMemberRepository memberRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String createMember(MemberAddRequest memberAddRequest) {
        try {
            Member memberCreate = null;
            if (memberAddRequest != null) {
                Member member = mapper.map(memberAddRequest, Member.class);
                member.setPassword(passwordEncoder.encode(member.getPassword()));
                memberCreate = memberRepository.saveAndFlush(member);
            }
            return ValidationCommon.isCheckProcess(memberCreate);
        } catch (Exception e) {
            e.printStackTrace();
            return ValidationCommon.isCheckProcess(null);
        }
    }

    @Override
    public String updateMember(MemberUpdateRequest memberUpdateRequest) {
        try {
            Member memberUpdate = null;
            if (memberUpdateRequest != null) {
                Member member = mapper.map(memberUpdateRequest, Member.class);
                memberUpdate = memberRepository.saveAndFlush(member);
            }
            return ValidationCommon.isCheckProcess(memberUpdate);
        } catch (Exception e) {
            e.printStackTrace();
            return ValidationCommon.isCheckProcess(null);
        }
    }

    @Override
    public Member findByEmail(String email) {
        try {
            return memberRepository.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Member findMemberByEmailAndPassword(String email, String password) {
        try {
            return memberRepository.findMemberByEmailAndPassword(email, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
