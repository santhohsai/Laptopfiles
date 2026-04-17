package com.santhosh.java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "User_Info")
public class UserInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @NotNull(message = "username must not be null")
    @NotEmpty(message = "username must not be empty")
    private String username;

    @NotNull(message = "password must not be null")
    @NotEmpty(message = "password must not be empty")
    private String password;


}
