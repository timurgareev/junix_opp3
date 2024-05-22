package com.gta.spring.springboot.junix_opp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTTokenSuccessResponse {
   private boolean success;
   private String token;
}
