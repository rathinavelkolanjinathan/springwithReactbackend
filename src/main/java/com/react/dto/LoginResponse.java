package com.react.dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class LoginResponse {
    private String message;
    private boolean isStatus;


}
