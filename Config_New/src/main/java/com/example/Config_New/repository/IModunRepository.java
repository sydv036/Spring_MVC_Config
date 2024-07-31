package com.example.Config_New.repository;

import com.example.Config_New.entity.Modun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IModunRepository extends JpaRepository<Modun, Integer> {
}
