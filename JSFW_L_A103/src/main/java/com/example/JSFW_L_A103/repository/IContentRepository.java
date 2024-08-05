package com.example.JSFW_L_A103.repository;

import com.example.JSFW_L_A103.dtos.reponse.ContentReponse;
import com.example.JSFW_L_A103.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IContentRepository extends JpaRepository<Content, String> {

    @Query("""
                SELECT new com.example.JSFW_L_A103.dtos.reponse.ContentReponse(ct.id ,ct.title,ct.brief,ct.createdDate,ct.content,ct.member.id) FROM Content ct
            """)
    Page<ContentReponse> findAllContent(Pageable pageable);

    @Query("""
                SELECT new com.example.JSFW_L_A103.dtos.reponse.ContentReponse(ct.id ,ct.title,ct.brief,ct.createdDate,ct.content,ct.member.id) FROM Content ct
                where  ct.title like :title
            """)
    Page<ContentReponse> findContentByTitle(@Param(value = "title") String title, Pageable pageable);

    @Query("""
                    SELECT new com.example.JSFW_L_A103.dtos.reponse.ContentReponse(ct.id ,ct.title,ct.brief,ct.createdDate,ct.content,ct.member.id)  FROM Content ct WHERE ct.id like :id
            """)
    ContentReponse findByIds(@Param(value = "id") String id);
}
