package com.szczygiel.pcbuildershop.controller;

import com.szczygiel.pcbuildershop.dto.AddItemDto;
import com.szczygiel.pcbuildershop.dto.EditItemDto;
import com.szczygiel.pcbuildershop.exception.InvalidItemException;
import com.szczygiel.pcbuildershop.exception.ItemNotFoundException;
import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.service.ItemService;
import com.szczygiel.pcbuildershop.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item")
public class ItemController {

    //todo add sorting by price as not required requestparam

    private final ItemService itemService;
    private final ValidationUtil validationUtil;

    @Autowired
    public ItemController(ItemService itemService, ValidationUtil validationUtil) {
        this.itemService = itemService;
        this.validationUtil = validationUtil;
    }

    @GetMapping("{itemID}")
    public Item getItemById(Long itemID) {
        return itemService.getItem(itemID)
                .orElseThrow(() -> new ItemNotFoundException(itemID));
    }

    @GetMapping("category")
    public List<Item> getItemsByCategory(@RequestParam Long categoryId, @RequestParam int page, @RequestParam int size) {
        page = validationUtil.validatePage(page);
        size = validationUtil.validateSize(size);

        return itemService.getItemsByCategory(categoryId, page, size);
    }

    @GetMapping("user")
    public List<Item> getItemsByUser(@RequestParam Long userId, @RequestParam int page, @RequestParam int size) {
        page = validationUtil.validatePage(page);
        size = validationUtil.validateSize(size);

        return itemService.getItemsByUser(userId, page, size);
    }

    @GetMapping("search")
    public List<Item> getItemsByPhrase(@RequestParam String phrase, @RequestParam int page, @RequestParam int size) {
        page = validationUtil.validatePage(page);
        size = validationUtil.validateSize(size);

        return itemService.getItemsByPhrase(phrase, page, size);
    }

    @GetMapping("search/category")
    public List<Item> getItemsByPhraseAndCategory(
            @RequestParam String phrase,
            @RequestParam Long categoryId,
            @RequestParam int page,
            @RequestParam int size) {
        page = validationUtil.validatePage(page);
        size = validationUtil.validateSize(size);
        return itemService.getItemsByPhraseAndCategory(phrase, categoryId, page, size);
    }

    @PutMapping
    public EditItemDto editItem(@RequestBody EditItemDto editedItem) {
        if (itemService.isItemExists(editedItem.getId())) {
            throw new ItemNotFoundException(editedItem.getId());
        }
        return itemService.editItem(editedItem);
    }

    @PostMapping
    public Item addItem(@RequestBody AddItemDto itemDto){
        String errors = validationUtil.validItemDto(itemDto);

        if(!errors.isEmpty()) {
            throw new InvalidItemException(errors);
        }

        return itemService.addItem(itemDto);
    }

    @DeleteMapping("{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        if (itemService.isItemExists(itemId)) {
            throw new ItemNotFoundException(itemId);
        }
        itemService.deleteItem(itemId);
    }
}
