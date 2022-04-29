package com.example.backend.drugmanager;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Drug {
    private Integer id;
    private String name;
    private String type;
    private String dose;
    private String manufactured;
    private Integer cost;
    private Integer amount;

}
