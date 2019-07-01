package com.learn.oauth2.model.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Integer id;
    private AccountDTO accountDto;
    private String email;
    private String name;

    public UserDTO() {}

    public UserDTO(Integer id, AccountDTO account, String email, String name) {

        this.id = id;
        this.accountDto = account;
        this.email = email;
        this.name = name;

    }
}
