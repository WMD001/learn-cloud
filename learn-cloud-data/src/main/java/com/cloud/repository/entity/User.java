package com.cloud.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "db_user")
public class User {

    @Id
    @GeneratedValue
    private String id;
    private String username;
    private String password;

}
