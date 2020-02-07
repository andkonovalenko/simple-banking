package com.testTask.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(of = {"id", "mail", "password", "balance"})
@EqualsAndHashCode(of = "id")
@Table(name = "usr")
public class User {

    @Id
    @Column(name = "user_id", nullable = false)
    @JsonView(Views.Id.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(Views.Full.class)
    @Column(name = "password", nullable = false)
    private String password;

    @JsonView(Views.Full.class)
    @Column(name = "e_mail", nullable = false)
    private String mail;

    @JsonView(Views.Full.class)
    @Column(name = "balance")
    private Float balance;
}