package com.sefrinaldi.demo_purchase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * @Created : 02/10/24 - 10.53
 * @Author : caniago
 */

@Entity(name = "PO_D")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PoD {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "poh_id", referencedColumnName = "id")
    private PoH pohId;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Items itemId;

    private Integer itemQty;
    private Integer itemCost;
    private Integer itemPrice;
}
