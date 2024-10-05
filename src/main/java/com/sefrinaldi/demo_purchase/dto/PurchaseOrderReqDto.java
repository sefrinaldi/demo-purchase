package com.sefrinaldi.demo_purchase.dto;

import lombok.Data;

import java.util.List;

/**
 * @Created : 04/10/24 - 22.22
 * @Author : caniago
 */

@Data
public class PurchaseOrderReqDto {
    private List<PurchaseDetail> purchaseDetails;
    private String description;

    @Data
    public static class PurchaseDetail {
        private Long itemId;
        private Integer itemQty;
    }
}
