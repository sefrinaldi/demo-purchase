package com.sefrinaldi.demo_purchase.controller;

import com.sefrinaldi.demo_purchase.dto.PurchaseDetailRespDto;
import com.sefrinaldi.demo_purchase.dto.PurchaseOrderReqDto;
import com.sefrinaldi.demo_purchase.dto.PurchaseOrderRespDto;
import com.sefrinaldi.demo_purchase.entity.PoD;
import com.sefrinaldi.demo_purchase.entity.PoH;
import com.sefrinaldi.demo_purchase.service.PurchaseOrderService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Created : 05/10/24 - 11.35
 * @Author : caniago
 */

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseOrderService purchaseOrderService;

    @PostMapping("/create-purchase")
    public ResponseEntity<PurchaseOrderRespDto> createPurchase(@RequestBody PurchaseOrderReqDto purchaseOrderReqDto) {
        return ResponseEntity.ok(purchaseOrderService.createPurchaseOrder(purchaseOrderReqDto));
    }

    @GetMapping("/get-purchase/{id}")
    public ResponseEntity<PurchaseOrderRespDto> getPurchaseById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ofNullable(purchaseOrderService.getPurchaseHeaderById(id));
    }

    @GetMapping("/get-purchase-all")
    public ResponseEntity<Page<PoH>> getAllPurchaseHeader(Pageable pageable) {
        return ResponseEntity.ofNullable(purchaseOrderService.getAllPurchaseHeader(pageable));
    }

    @GetMapping("/get-purchase-detail/{id}")
    public ResponseEntity<PurchaseDetailRespDto> getPurchaseDetailById(@PathVariable(value = "id") Long id) throws NotFoundException {
        return ResponseEntity.ofNullable(purchaseOrderService.getPurchaseDetailById(id));
    }

    @GetMapping("/get-purchase-detail-all")
    public ResponseEntity<Page<PoD>> getAllPurchaseDetail(Pageable pageable) {
        return ResponseEntity.ofNullable(purchaseOrderService.getAllPurchaseDetail(pageable));
    }
}
