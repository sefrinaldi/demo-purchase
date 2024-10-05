package com.sefrinaldi.demo_purchase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Created : 02/10/24 - 10.53
 * @Author : caniago
 */

@Entity(name = "PO_H")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PoH extends BaseDomain{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Date dateTime;
    private String description;
    private Integer totalPrice;
    private Integer totalCost;
}
