package com.ohgiraffers.restapi.section02.responseEntity;

import java.util.Map;

public class ResponseMessage {

    private int httpStatusCode;
    private String message;
    private Map<String, Object> result;

    public ResponseMessage() {
    }

    public ResponseMessage(int httpStatusCode, String message, Map<String, Object> result) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.result = result;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
            "httpStatusCode=" + httpStatusCode +
            ", message='" + message + '\'' +
            ", result=" + result +
            '}';
    }
}
