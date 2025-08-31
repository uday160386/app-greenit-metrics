package com.example.metrics.model;

public class Loan {
    private String id;
    private String clientId;
    private int amount;
    private String currency;
    private int termInMonths;
    private double interestRate;
    private String status;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public int getTermInMonths() { return termInMonths; }
    public void setTermInMonths(int termInMonths) { this.termInMonths = termInMonths; }
    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
