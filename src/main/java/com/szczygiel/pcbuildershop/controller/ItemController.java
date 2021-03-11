package com.szczygiel.pcbuildershop.controller;

import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("{itemID}")
    public Optional<Item> getItemById(Long itemID) {
        return itemService.getItem(itemID);
    }

    @PutMapping
    public Optional<Item> editItem(@RequestBody Item item) {
        return itemService.editItem(item);
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @DeleteMapping("{itemID}")
    public void deleteItem(@PathVariable Long itemID) {
        itemService.deleteItem(itemID);
    }
}
