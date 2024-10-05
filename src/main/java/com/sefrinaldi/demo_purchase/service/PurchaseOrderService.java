package com.sefrinaldi.demo_purchase.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sefrinaldi.demo_purchase.dto.PurchaseDetailRespDto;
import com.sefrinaldi.demo_purchase.dto.PurchaseOrderReqDto;
import com.sefrinaldi.demo_purchase.dto.PurchaseOrderRespDto;
import com.sefrinaldi.demo_purchase.entity.Items;
import com.sefrinaldi.demo_purchase.entity.PoD;
import com.sefrinaldi.demo_purchase.entity.PoH;
import com.sefrinaldi.demo_purchase.repository.PurchaseOrderDetailRepository;
import com.sefrinaldi.demo_purchase.repository.PurchaseOrderHeaderRepository;
import com.sefrinaldi.demo_purchase.service.validation.ValidationItemService;
import com.sefrinaldi.demo_purchase.service.validation.ValidationPurchaseOrderHeaderService;
import jakarta.persistence.EntityManager;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Created : 04/10/24 - 22.19
 * @Author : caniago
 */

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderHeaderRepository purchaseOrderHeaderRepository;
    private final PurchaseOrderDetailRepository purchaseOrderDetailRepository;
    private final ValidationItemService validationItemService;
    private final ValidationPurchaseOrderHeaderService validationPurchaseOrderHeaderService;
    private final ObjectMapper objectMapper;

    public PurchaseOrderRespDto createPurchaseOrder(PurchaseOrderReqDto purchaseOrderReqDto) {
        List<PoD> purchaseDetailList = new ArrayList<>();

        var totalPrice = new AtomicInteger(0);
        var totalCost = new AtomicInteger(0);

        purchaseOrderReqDto.getPurchaseDetails()
                .forEach(purchase -> {
                    Items items = validationItemService.getItemById(purchase.getItemId());

                    Integer quantity = purchase.getItemQty();
                    int price = quantity * items.getPrice();
                    int cost = quantity * items.getCost();

                    totalPrice.updateAndGet(v -> v + price);
                    totalCost.updateAndGet(v -> v + cost);

                    PoD poD = PoD.builder()
                            .itemId(items)
                            .itemQty(purchase.getItemQty())
                            .itemPrice(price)
                            .itemCost(cost)
                            .build();

                    purchaseDetailList.add(poD);
                });

        PoH poH = PoH.builder()
                .dateTime(new Date())
                .description(purchaseOrderReqDto.getDescription())
                .totalPrice(totalPrice.intValue())
                .totalCost(totalCost.intValue())
                .build();

        PoH response = purchaseOrderHeaderRepository.save(poH);

        purchaseDetailList.forEach(purchase -> {
            purchase.setPohId(response);
        });

        purchaseOrderDetailRepository.saveAll(purchaseDetailList);

        return PurchaseOrderRespDto.builder()
                .id(poH.getId())
                .dateTime(poH.getDateTime())
                .description(poH.getDescription())
                .totalPrice(totalPrice.intValue())
                .totalCost(totalCost.intValue())
                .build();
    }

    public PurchaseOrderRespDto getPurchaseHeaderById(Long id) {
        PoH poH = validationPurchaseOrderHeaderService.getPurchaseHeaderById(id);
        return objectMapper.convertValue(poH, PurchaseOrderRespDto.class);
    }

    public Page<PoH> getAllPurchaseHeader(Pageable pageable) {
        return purchaseOrderHeaderRepository.findAll(pageable);
    }

    public PurchaseDetailRespDto getPurchaseDetailById(Long id) throws NotFoundException {
        Optional<PoD> poDOptional = purchaseOrderDetailRepository.findById(id);
        if (poDOptional.isEmpty()) {
            throw new NotFoundException("purchase detail is not found");
        }
        return objectMapper.convertValue(poDOptional.get(), PurchaseDetailRespDto.class);
    }

    public Page<PoD> getAllPurchaseDetail(Pageable pageable) {
        return purchaseOrderDetailRepository.findAll(pageable);
    }
}
