package com.szczygiel.pcbuildershop.controller;

import com.szczygiel.pcbuildershop.dto.ItemDto;
import com.szczygiel.pcbuildershop.dto.ItemSearchRequest;
import com.szczygiel.pcbuildershop.exception.InvalidItemException;
import com.szczygiel.pcbuildershop.exception.InvalidRequestException;
import com.szczygiel.pcbuildershop.exception.ItemNotFoundException;
import com.szczygiel.pcbuildershop.service.ItemService;
import com.szczygiel.pcbuildershop.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
    public ItemDto getItemById(Long itemID) {
        return itemService.getItem(itemID)
                .orElseThrow(() -> new ItemNotFoundException(itemID));
    }

    @GetMapping("search")
    public Page<ItemDto> searchForItems(ItemSearchRequest request, @ApiIgnore Errors errors) {
        if(errors.hasErrors()) {
            throw new InvalidRequestException(validationUtil.getErrorsMessages(errors));
        }

        return itemService.searchForItems(request);
    }

    @PutMapping
    public ItemDto editItem(@RequestBody ItemDto editedItem) {
        if (itemService.isItemExists(editedItem.getId())) {
            throw new ItemNotFoundException(editedItem.getId());
        }
        return itemService.editItem(editedItem);
    }

    @PostMapping
    public ItemDto addItem(@RequestBody ItemDto itemDto){
        String errors = validationUtil.validateItemDto(itemDto);

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
