package com.example.metrics.model;

import java.util.List;
import java.util.Map;

public class LoanListResponse {
    private List<Loan> data;
    private Map<String, Object> metadata;
    public List<Loan> getData() { return data; }
    public void setData(List<Loan> data) { this.data = data; }
    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
}
