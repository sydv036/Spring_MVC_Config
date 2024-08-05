package com.example.JSFW_L_A103.dtos.reponse;

import com.example.JSFW_L_A103.entity.Content;
import com.example.JSFW_L_A103.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.io.Serializable;
import java.time.LocalDate;

//@Projection(types = {Content.class})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ContentReponse implements Serializable {
    private String id;
    private String title;
    private String brief;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createDate;
    private String content;
    private Integer authorId;

//    @Value("#{target.id}")
//    String getId();
//
//    @Value("#{target.title}")
//    String getTitle();
//
//    @Value("#{target.brief}")
//    String getBrief();
//
//    @Value("#{target.createdDate}")
//    LocalDate getCreateDate();
}
