package com.sefrinaldi.demo_purchase.service.validation;

import com.sefrinaldi.demo_purchase.entity.PoH;
import com.sefrinaldi.demo_purchase.repository.PurchaseOrderHeaderRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.Optional;

/**
 * @Created : 04/10/24 - 22.25
 * @Author : caniago
 */

@Service
@RequiredArgsConstructor
@RequestScope
public class ValidationPurchaseOrderHeaderService {

    private final PurchaseOrderHeaderRepository purchaseOrderHeaderRepository;
    private HashMap<Long, PoH> purchaseHeaderHashMap = new HashMap<>();

    @SneakyThrows
    public PoH getPurchaseHeaderById(Long id) {
        if (!purchaseHeaderHashMap.containsKey(id)) {
            Optional<PoH> poHOptional = purchaseOrderHeaderRepository.findById(id);
            if (poHOptional.isEmpty()) {
                throw new NotFoundException("purchase header is not found");
            }
            purchaseHeaderHashMap.put(id, poHOptional.get());
        }
        return purchaseHeaderHashMap.get(id);
    }
}
