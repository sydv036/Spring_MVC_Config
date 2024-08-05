package com.example.JSFW_L_A103.dtos.request;

import com.example.JSFW_L_A103.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ContentAddRequest {
    @NotBlank(message = "Title not be empty")
    @Length(max = 50, message = "Title max length 50 characters")
    private String title;
    @NotBlank(message = "Brief not be empty")
    private String brief;
    @NotBlank(message = "Content not be empty")
    private String content;
    private LocalDate createdDate = LocalDate.now();
    private Member member;
}
