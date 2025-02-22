package com.example.limepay.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorCode {
    
    String code;
    
    String message;

}
