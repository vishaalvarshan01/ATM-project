package com.wipro.atmcustomer.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class MiniStatement {
    public MiniStatement() {
    }

    public MiniStatement(Long id, Double amount, BankCustomer bankCustomer, LocalDate date, LocalTime time, String status) {
        this.id = id;
        this.amount = amount;
        this.bankCustomer = bankCustomer;
        this.date = date;
        this.time = time;
        this.status = status;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @OneToOne
    private BankCustomer bankCustomer;
    private LocalDate date;
    private LocalTime time;

    private String status; // DEPOSITED, WITHDRAW



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public BankCustomer getCustomer() {
        return bankCustomer;
    }

    public void setCustomer(BankCustomer bankCustomer) {
        this.bankCustomer = bankCustomer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}