package com.santhosh.java.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prodcuts")
public class Productmodel
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String password;
    private int price;
    private String colour;
    private String model;
    private String role;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Productmodel(String name, String password, int price, String model, String colour, String role, String status) {
        this.name = name;
        this.password = password;
        this.price = price;
        this.model = model;
        this.colour = colour;
        this.role = role;
        this.status= status ;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }


}
