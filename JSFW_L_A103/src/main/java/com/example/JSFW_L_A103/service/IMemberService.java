package com.example.JSFW_L_A103.service;

import com.example.JSFW_L_A103.dtos.request.MemberAddRequest;
import com.example.JSFW_L_A103.dtos.request.MemberUpdateRequest;
import com.example.JSFW_L_A103.entity.Member;

public interface IMemberService {
    String createMember(MemberAddRequest memberAddRequest);

    String updateMember(MemberUpdateRequest memberUpdateRequest);

    Member findByEmail(String email);

    Member findMemberByEmailAndPassword(String email, String password);
}
