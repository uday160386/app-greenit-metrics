package com.example.metrics.model;

import java.util.List;

public class ErrorResponse {
    private String code;
    private String message;
    private List<ValidationDetail> details;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public List<ValidationDetail> getDetails() { return details; }
    public void setDetails(List<ValidationDetail> details) { this.details = details; }

    public static class ValidationDetail {
        private String field;
        private String reason;

        public String getField() { return field; }
        public void setField(String field) { this.field = field; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
    }
}
