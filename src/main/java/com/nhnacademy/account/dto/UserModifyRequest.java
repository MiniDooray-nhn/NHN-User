package com.nhnacademy.account.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModifyRequest {

    @NotEmpty
    @NotNull
    @Length(max = 30)
    String password;

    @Email
    @NotNull
    @Length(max = 50)
    String email;

    @NotNull
    @NotEmpty
    @Length(max = 20)
    String name;


}