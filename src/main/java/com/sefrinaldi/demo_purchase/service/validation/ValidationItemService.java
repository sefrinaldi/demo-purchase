package com.sefrinaldi.demo_purchase.service.validation;

import com.sefrinaldi.demo_purchase.entity.Items;
import com.sefrinaldi.demo_purchase.entity.Users;
import com.sefrinaldi.demo_purchase.repository.ItemRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

/**
 * @Created : 03/10/24 - 15.37
 * @Author : caniago
 */

@Service
@RequiredArgsConstructor
@RequestScope
public class ValidationItemService {

    private final ItemRepository itemRepository;

    private HashMap<Long, Items> itemsHashMap = new HashMap<>();

    @SneakyThrows
    public Items getItemById(Long id) {
        if (!itemsHashMap.containsKey(id)) {
            Optional<Items> stockOptional = itemRepository.findById(id);
            if (stockOptional.isEmpty()) {
                throw new NotFoundException("item is not found");
            }
            itemsHashMap.put(id, stockOptional.get());
        }
        return itemsHashMap.get(id);
    }
}
