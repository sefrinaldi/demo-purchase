package com.sefrinaldi.demo_purchase.dto;

import com.sefrinaldi.demo_purchase.entity.Items;
import com.sefrinaldi.demo_purchase.entity.PoH;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * @Created : 05/10/24 - 15.31
 * @Author : caniago
 */

@Data
public class PurchaseDetailRespDto {
    private Long id;
    private PoH pohId;
    private Items itemId;
    private Integer itemQty;
    private Integer itemCost;
    private Integer itemPrice;
}
