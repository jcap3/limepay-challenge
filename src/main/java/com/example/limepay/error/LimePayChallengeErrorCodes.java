package com.example.limepay.error;

public class LimePayChallengeErrorCodes {
    
    private LimePayChallengeErrorCodes() {}

    public static final ErrorCode INTERNAL_ERROR = createErrorCode("999", "Internal Server Error");

    public static final ErrorCode INVALID_REQUESTED_THRESHOLD = createErrorCode("001", "Invalid requested threshold");

    public static final ErrorCode EMPTY_DIRECTORS_LIST = createErrorCode("002", "No directors directed more than the requested threshold");

    private static ErrorCode createErrorCode(String code, String message) {
        return new ErrorCode(code, message);
    }
}
