package com.learn.oauth2.model.dto;

import lombok.Data;
import rx.Subscription;
@Data
public class RequestHeaderDTO {
    private UserDTO userDto;
    private Subscription subscription;
}
