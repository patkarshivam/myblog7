package com.myblog7.payload;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
//this class is a pojo class
//pojo class:A class which dealing with encapsulation (getter,setter and variables)