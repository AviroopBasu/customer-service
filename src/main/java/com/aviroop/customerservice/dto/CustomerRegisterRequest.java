package com.aviroop.customerservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRegisterRequest {
    private String userId;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String password;
}
