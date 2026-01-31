package com.learning.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tiers")
@Data
public class Tier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "tier_1")
    private Long tier1;
    @Column(name = "tier_2")
    private Long tier2;
    @Column(name = "tier_3")
    private Long tier3;
}
