package com.sefrinaldi.demo_purchase.controller;

import com.sefrinaldi.demo_purchase.dto.ItemReqDto;
import com.sefrinaldi.demo_purchase.dto.ItemRespDto;
import com.sefrinaldi.demo_purchase.entity.Items;
import com.sefrinaldi.demo_purchase.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Created : 03/10/24 - 23.52
 * @Author : caniago
 */

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/create-item")
    public ResponseEntity<ItemRespDto> createItem(@RequestBody ItemReqDto itemReqDto) {
        return ResponseEntity.ok(itemService.createItem(itemReqDto));
    }

    @PutMapping("/update-item/{id}")
    public ResponseEntity<ItemRespDto> updateItem(
            @PathVariable(value = "id") Long id,
            @RequestBody ItemReqDto itemReqDto) {
        return ResponseEntity.ok(itemService.updateItem(itemReqDto, id));
    }

    @GetMapping("/get-item/{id}")
    public ResponseEntity<ItemRespDto> getItemById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ofNullable(itemService.getItemById(id));
    }

    @DeleteMapping("/delete-item/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable(value = "id") Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-item-all")
    public ResponseEntity<Page<Items>> getAllItems(Pageable pageable) {
        return ResponseEntity.ofNullable(itemService.getAllItems(pageable));
    }
}
