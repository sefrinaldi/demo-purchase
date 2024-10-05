package com.sefrinaldi.demo_purchase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Created : 02/10/24 - 10.52
 * @Author : caniago
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Items extends BaseDomain{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String description;
    private Integer price;
    private Integer cost;
}
