package com.example.limepay.error.exception;

public class NoDirectorsFound extends RuntimeException {

    public NoDirectorsFound(Exception e) {
        super(e);
    }

    public NoDirectorsFound(String s) {
        super(s);
    }

}
