package com.example.limepay.error;

public class LimePayChallengeErrorCodes {
    
    private LimePayChallengeErrorCodes() {}

    public static final ErrorCode INTERNAL_ERROR = createErrorCode("999", "Internal Server Error");
    
    private static ErrorCode createErrorCode(String code, String message) {
        return new ErrorCode(code, message);
    }
}
