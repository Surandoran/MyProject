package com.my.article.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CustomerDTO {

    private String email;
    private String password;
    private String phone;
    private String auth;

}
