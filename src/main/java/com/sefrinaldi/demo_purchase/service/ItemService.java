package com.sefrinaldi.demo_purchase.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sefrinaldi.demo_purchase.dto.ItemReqDto;
import com.sefrinaldi.demo_purchase.dto.ItemRespDto;
import com.sefrinaldi.demo_purchase.entity.Items;
import com.sefrinaldi.demo_purchase.repository.ItemRepository;
import com.sefrinaldi.demo_purchase.service.validation.ValidationItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Created : 03/10/24 - 15.15
 * @Author : caniago
 */

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ValidationItemService validationItemService;
    private final ObjectMapper objectMapper;

    public ItemRespDto createItem(ItemReqDto itemReqDto) {
        Items items = objectMapper.convertValue(itemReqDto, Items.class);

        Items response = itemRepository.save(items);

        return objectMapper.convertValue(response, ItemRespDto.class);
    }

    public ItemRespDto updateItem(ItemReqDto itemReqDto, Long id) {
        Items items = validationItemService.getItemById(id);

        items.setName(itemReqDto.getName());
        items.setDescription(itemReqDto.getDescription());
        items.setPrice(itemReqDto.getPrice());
        items.setCost(itemReqDto.getCost());

        Items response = itemRepository.save(items);
        return objectMapper.convertValue(response, ItemRespDto.class);
    }

    public ItemRespDto getItemById(Long id) {
        Items items = validationItemService.getItemById(id);
        return objectMapper.convertValue(items, ItemRespDto.class);
    }

    public void deleteItem(Long id) {
        Items items = validationItemService.getItemById(id);
        itemRepository.delete(items);
    }

    public Page<Items> getAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }
}
