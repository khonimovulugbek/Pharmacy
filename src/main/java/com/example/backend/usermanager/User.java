package com.example.backend.usermanager;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
    private Integer id;
    private String name;
    private String surname;
    private String middlename;
    private String phone_number;
    private String email;
    private String address;
    private String userName;
    private String password;
    private String type;

}
