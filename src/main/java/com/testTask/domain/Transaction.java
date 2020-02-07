package com.testTask.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.testTask.domain.enums.TransactionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString(of = {"id", "amountOfMoney", "date", "transactionType", "user"})
@EqualsAndHashCode(of = "id")
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "transaction_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.Full.class)
    @Column(name = "money_amount")
    private Float amountOfMoney;

    @JsonView(Views.Full.class)
    @Column(name = "date", nullable = false)
    private String date;

    @JsonView(Views.Full.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false)
    private TransactionType transactionType;

    @JsonView(Views.Full.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
