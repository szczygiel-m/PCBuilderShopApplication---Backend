package com.szczygiel.pcbuildershop.service;

import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Optional<Item> getItem(Long itemID) {
        return itemRepository.findById(itemID);
    }
}
