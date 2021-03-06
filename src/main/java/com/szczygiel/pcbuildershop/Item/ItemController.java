package com.szczygiel.pcbuildershop.Item;

import com.szczygiel.pcbuildershop.exception.InvalidItemException;
import com.szczygiel.pcbuildershop.exception.InvalidRequestException;
import com.szczygiel.pcbuildershop.exception.ItemNotFoundException;
import com.szczygiel.pcbuildershop.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("api/v1/item")
public class ItemController {

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
        if (errors.hasErrors()) {
            throw new InvalidRequestException(validationUtil.getErrorsMessages(errors));
        }

        return itemService.searchForItems(request);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('item:update')")
    public ItemDto editItem(@RequestBody ItemDto editedItem) {
        if (itemService.isItemExists(editedItem.getId())) {
            throw new ItemNotFoundException(editedItem.getId());
        }
        return itemService.editItem(editedItem);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('item:add')")
    public ItemDto addItem(@RequestBody ItemDto itemDto) {
        String errors = validationUtil.validateItemDto(itemDto);

        if (!errors.isEmpty()) {
            throw new InvalidItemException(errors);
        }

        return itemService.addItem(itemDto);
    }

    @DeleteMapping("{itemId}")
    @PreAuthorize("hasAuthority('item:delete')")
    public void deleteItem(@PathVariable Long itemId) {
        if (itemService.isItemExists(itemId)) {
            throw new ItemNotFoundException(itemId);
        }
        itemService.deleteItem(itemId);
    }
}
