package com.sefrinaldi.demo_purchase.dto;

import com.sefrinaldi.demo_purchase.entity.Items;
import com.sefrinaldi.demo_purchase.entity.PoD;
import com.sefrinaldi.demo_purchase.entity.PoH;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Created : 04/10/24 - 22.22
 * @Author : caniago
 */

@Data
@Builder
public class PurchaseOrderRespDto {
    private Long id;
    private Date dateTime;
    private String description;
    private Integer totalPrice;
    private Integer totalCost;
}
