package com.sefrinaldi.demo_purchase.dto;

import lombok.Data;

/**
 * @Created : 03/10/24 - 15.17
 * @Author : caniago
 */

@Data
public class ItemReqDto {

    private String name;
    private String description;
    private Integer price;
    private Integer cost;
}
