package com.aviroop.customerservice.dto;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoginRequest {
    private String userId;
    private String password;
}
