package com.example.JSFW_L_A103.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberUpdateRequest {
    private Integer id;
    @NotBlank(message = "First name must be not blank!")
    private String firstName;
    @NotBlank(message = "Last name must be not blank!")
    private String lastName;
    private String userName;
    private String password;
    @NotBlank(message = "Phone must be not blank!")
    @Pattern(regexp = "^(03|05|07|08|09)\\d{8}$", message = "Phone not match Phone VN!")
    private String phone;
    private String email;
    @NotBlank(message = "Description must be not blank!")
    private String description;
    private LocalDate createdDate;
    private LocalTime updateTime = LocalTime.now();
    private String role;
}
