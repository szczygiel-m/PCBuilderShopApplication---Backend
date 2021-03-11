package com.szczygiel.pcbuildershop.service;

import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public Optional<Item> editItem(Item item) {
        Optional<Item> itemEdited = itemRepository.findById(item.getId());

        if(itemEdited.isPresent()){
            itemEdited.orElseGet(null).setTitle(item.getTitle());
            itemEdited.orElseGet(null).setDescription(item.getDescription());
            itemEdited.orElseGet(null).setPrice(item.getPrice());

            return itemEdited;
        }

        return itemEdited;
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Long itemID) {
        itemRepository.deleteById(itemID);
    }
}
