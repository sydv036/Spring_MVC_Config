package com.example.JSFW_L_A103.repository;

import com.example.JSFW_L_A103.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepository extends JpaRepository<Member, Integer> {

    Member findByEmail(String email);

    Member findMemberByEmailAndPassword(String email, String password);
}
