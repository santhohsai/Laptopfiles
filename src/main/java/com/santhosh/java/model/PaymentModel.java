package com.santhosh.java.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PaymentDb")
public class PaymentModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private int price;
    private String mode;
    private String paymentstatus;

    public PaymentModel( int price, String mode,String Initaited) {
        //this.paymentstatus = paymentstatus;
        this.mode = mode;
        this.price = price;
    }

    public PaymentModel() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus;
    }


}
