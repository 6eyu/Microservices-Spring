package com.learn.oauth2.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Integer id;
    private String email;
    private String status;
    private BigDecimal credit;
    private BigDecimal balance;
}
