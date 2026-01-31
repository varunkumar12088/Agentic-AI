package com.learning.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "prices")
@Data
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "price_1")
    private Long price1;
    @Column(name = "price_2")
    private Long price2;
    @Column(name = "price_3")
    private Long price3;
    @Column(name = "price_4")
    private Long price4;
    @Column(name = "activation_date")
    private Instant activationDate;
    @Column(name = "created_date")
    private  Instant createdDate;

}
