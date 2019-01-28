package com.chris.eban.domain;


/**
 * generally result exception
 */
public class ResultException extends Exception {
    // debug message
    private String debugMessage;

    public ResultException(String message, Throwable cause, String debugMessage) {
        super(message, cause);
        this.debugMessage = debugMessage;
    }
}
