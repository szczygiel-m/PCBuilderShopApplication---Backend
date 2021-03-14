package com.szczygiel.pcbuildershop.controller;

import com.szczygiel.pcbuildershop.exception.ItemNotFoundException;
import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("{itemID}")
    public Item getItemById(Long itemID) {
        return itemService.getItem(itemID)
                .orElseThrow(() -> new ItemNotFoundException(itemID));
    }

    @GetMapping("category")
    public List<Item> getItemsByCategory(@RequestParam Long categoryId, @RequestParam int page, @RequestParam int size) {
        return itemService.getItemsByCategory(categoryId, page, size);
    }

    @GetMapping("user")
    public List<Item> getItemsByUser(@RequestParam Long userId, @RequestParam int page, @RequestParam int size) {
        return itemService.getItemsByUser(userId, page, size);
    }

    @GetMapping("search")
    public List<Item> getItemsByPhrase(@RequestParam String phrase, @RequestParam int page, @RequestParam int size) {
        return itemService.getItemsByPhrase(phrase, page, size);
    }

    @GetMapping("search/category")
    public List<Item> getItemsByPhraseAndCategory(
            @RequestParam String phrase,
            @RequestParam Long categoryId,
            @RequestParam int page,
            @RequestParam int size) {
        return itemService.getItemsByPhraseAndCategory(phrase, categoryId, page, size);
    }

    @PutMapping
    public Item editItem(@RequestBody Item item) {
        if (itemService.isItemExists(item.getId())) {
            throw new ItemNotFoundException(item.getId());
        }
        return itemService.editItem(item);
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        if (itemService.isItemExists(item.getId())) {
            throw new ItemNotFoundException(item.getId());
        }
        return itemService.addItem(item);
    }

    @DeleteMapping("{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        if (itemService.isItemExists(itemId)) {
            throw new ItemNotFoundException(itemId);
        }
        itemService.deleteItem(itemId);
    }
}
