package com.szczygiel.pcbuildershop.service;

import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.repository.ItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
    public Item editItem(Item item) {
        Item itemEdited = itemRepository.findById(item.getId()).orElseThrow();

        itemEdited.setTitle(item.getTitle());
        itemEdited.setDescription(item.getDescription());
        itemEdited.setPrice(item.getPrice());

        return itemEdited;
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Long itemID) {
        itemRepository.deleteById(itemID);
    }

    public List<Item> getItemsByCategory(Long categoryId, int page, int size) {
        return itemRepository.findAllByCategoryId(categoryId, PageRequest.of(page, size));
    }

    public List<Item> getItemsByUser(Long userId, int page, int size){
        return itemRepository.findAllByUserId(userId, PageRequest.of(page, size));
    }

    public List<Item> getItemsByPhrase(String phrase, int page, int size) {
        return itemRepository.findByPhrase(phrase, PageRequest.of(page, size));
    }

    public List<Item> getItemsByPhraseAndCategory(String phrase, Long categoryId, int page, int size) {
        return itemRepository.findByPhraseAndCategory(phrase, categoryId, PageRequest.of(page, size));
    }

    public boolean isItemExists(Long itemId) {
        return !itemRepository.existsById(itemId);
    }
}
