package com.szczygiel.pcbuildershop.Item;

import com.szczygiel.pcbuildershop.util.DtoConverter;
import com.szczygiel.pcbuildershop.util.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ValidationUtil validationUtil;
    private final DtoConverter converter;

    public ItemService(ItemRepository itemRepository, ValidationUtil validationUtil, DtoConverter converter) {
        this.itemRepository = itemRepository;
        this.validationUtil = validationUtil;
        this.converter = converter;
    }

    public Optional<ItemDto> getItem(Long itemID) {
        if (isItemExists(itemID)) {
            Item foundItem = itemRepository.findById(itemID).orElseThrow();
            return Optional.of(new ItemDto(foundItem.getId(),
                    foundItem.getCategory().getId(),
                    foundItem.getUserProfile().getId(),
                    foundItem.getCreated(),
                    foundItem.getTitle(),
                    foundItem.getDescription(),
                    foundItem.getPrice()));
        }

        return Optional.empty();
    }

    public Page<ItemDto> searchForItems(ItemSearchRequest request) {
        Page<ItemDto> foundItems = null;

        request.setPage(validationUtil.validatePage(request.getPage()));
        request.setSize(validationUtil.validateSize(request.getSize()));
        request.setSortParam(validationUtil.validateSortParam(request.getSortParam()));
        request.setSortDirection(validationUtil.validateSortDirection(request.getSortDirection()));

        if (request.getCategoryId() == null && request.getUserId() == null) {
            foundItems = getAllItems(request);
        }
        if (request.getCategoryId() != null && request.getUserId() == null) {
            foundItems = getAllItemsByCategoryId(request);
        }
        if (request.getCategoryId() == null && request.getUserId() != null) {
            foundItems = getAllItemsByUserId(request);
        }
        if (request.getCategoryId() != null && request.getUserId() != null) {
            foundItems = getAllItemsByCategoryIdAndUserId(request);
        }

        return foundItems;
    }

    public Page<ItemDto> getAllItemsByCategoryIdAndUserId(ItemSearchRequest request) {
        Page<ItemDto> foundItems;
        if (request.getSortDirection().equals(Sort.Direction.ASC)) {
            foundItems = converter.itemPageCollectionToItemDtoCollection(itemRepository
                    .findAllByCategoryIdAndUserId(request.getCategoryId(),
                            request.getUserId(),
                            request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.asc(request.getSortParam().toString())))));
        } else {
            foundItems = converter.itemPageCollectionToItemDtoCollection(itemRepository
                    .findAllByCategoryIdAndUserId(request.getCategoryId(),
                            request.getUserId(),
                            request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.desc(request.getSortParam().toString())))));
        }
        return foundItems;
    }

    public Page<ItemDto> getAllItemsByUserId(ItemSearchRequest request) {
        Page<ItemDto> foundItems;
        if (request.getSortDirection().equals(Sort.Direction.ASC)) {
            foundItems = converter.itemPageCollectionToItemDtoCollection(itemRepository
                    .findAllByUserId(request.getUserId(),
                            request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.asc(request.getSortParam().toString())))));
        } else {
            foundItems = converter.itemPageCollectionToItemDtoCollection(itemRepository
                    .findAllByUserId(request.getUserId(),
                            request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.desc(request.getSortParam().toString())))));
        }
        return foundItems;
    }

    public Page<ItemDto> getAllItemsByCategoryId(ItemSearchRequest request) {
        Page<ItemDto> foundItems;
        if (request.getSortDirection().equals(Sort.Direction.ASC)) {
            foundItems = converter.itemPageCollectionToItemDtoCollection(itemRepository
                    .findAllByCategoryId(request.getCategoryId(),
                            request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.asc(request.getSortParam().toString())))));
        } else {
            foundItems = converter.itemPageCollectionToItemDtoCollection(itemRepository
                    .findAllByCategoryId(request.getCategoryId(),
                            request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.desc(request.getSortParam().toString())))));
        }
        return foundItems;
    }

    public Page<ItemDto> getAllItems(ItemSearchRequest request) {
        Page<ItemDto> foundItems;
        if (request.getSortDirection().equals(Sort.Direction.ASC)) {
            foundItems = converter.itemPageCollectionToItemDtoCollection(itemRepository
                    .findAll(request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.asc(request.getSortParam().toString())))));
        } else {
            foundItems = converter.itemPageCollectionToItemDtoCollection(itemRepository
                    .findAll(request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.desc(request.getSortParam().toString())))));
        }
        return foundItems;
    }

    @Transactional
    public ItemDto editItem(ItemDto item) {
        Item itemEdited = itemRepository.findById(item.getId()).orElseThrow();

        itemEdited.setTitle(item.getTitle());
        itemEdited.setDescription(item.getDescription());
        itemEdited.setPrice(item.getPrice().setScale(2, RoundingMode.CEILING));

        return item;
    }

    public ItemDto addItem(ItemDto itemDto) {
        itemDto.setPrice(itemDto.getPrice().setScale(2, RoundingMode.CEILING));
        Item insertedItem = converter.itemDtoToItem(itemDto);
        return converter.itemToItemDto((itemRepository.save(insertedItem)));
    }

    public void deleteItem(Long itemID) {
        itemRepository.deleteById(itemID);
    }

    public boolean isItemExists(Long itemId) {
        return itemRepository.existsById(itemId);
    }
}
