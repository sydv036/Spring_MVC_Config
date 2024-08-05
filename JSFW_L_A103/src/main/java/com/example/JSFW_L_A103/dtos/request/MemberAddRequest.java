package com.example.JSFW_L_A103.dtos.request;

import com.example.JSFW_L_A103.constant.RoleConstant;
import com.example.JSFW_L_A103.infastructor.unique.Unique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberAddRequest {
    @NotBlank(message = "Email cannot be blank!")
    @Unique(message = "Email is duplicated")
    @Email(message = "Email dose not match!")
    private String email;
    @NotBlank(message = "UserName cannot be blank!")
    @Unique(message = "UserName is duplicated")
    private String userName;
    @NotBlank(message = "Password cannot be blank!")
    @Min(value = 5, message = "Password must be at least 5 characters!")
    private String password;
    private LocalDate createdDate = LocalDate.now();
    private String role = RoleConstant.ROLE_MEMBER;
}
