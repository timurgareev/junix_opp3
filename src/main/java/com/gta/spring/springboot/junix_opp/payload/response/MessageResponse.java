package com.gta.spring.springboot.junix_opp.payload.response;

public class MessageResponse {
   private String message;

   public MessageResponse(String message) {
      this.message = message;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }


}
