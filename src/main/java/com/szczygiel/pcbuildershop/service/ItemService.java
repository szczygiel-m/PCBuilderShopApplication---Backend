package com.szczygiel.pcbuildershop.service;

import com.szczygiel.pcbuildershop.dto.Converter;
import com.szczygiel.pcbuildershop.dto.ItemDto;
import com.szczygiel.pcbuildershop.dto.ItemSearchRequest;
import com.szczygiel.pcbuildershop.model.Item;
import com.szczygiel.pcbuildershop.repository.ItemRepository;
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

    public ItemService(ItemRepository itemRepository, ValidationUtil validationUtil) {
        this.itemRepository = itemRepository;
        this.validationUtil = validationUtil;
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

        if (request.getCategoryId() == null && request.getUserId() == null) {
            foundItems = getAllItems(request);
        }
        if (request.getCategoryId() != null && request.getUserId() == null) {
            foundItems = getAllItemsByCategoryId(request);
        }
        if (request.getCategoryId() == null && request.getUserId() != null) {
            foundItems = geAllItemsByUserId(request);
        }
        if (request.getCategoryId() != null && request.getUserId() != null) {
            foundItems = getAllItemsByCategoryIdAndUserId(request);
        }

        return foundItems;
    }

    private Page<ItemDto> getAllItemsByCategoryIdAndUserId(ItemSearchRequest request) {
        Page<ItemDto> foundItems;
        if (request.getSortDirection().equals("ASC")) {
            foundItems = Converter.itemPageToDto(itemRepository
                    .findAllByCategoryIdAndUserId(request.getCategoryId(),
                            request.getUserId(),
                            request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.asc(request.getSortParam())))));
        } else {
            foundItems = Converter.itemPageToDto(itemRepository
                    .findAllByCategoryIdAndUserId(request.getCategoryId(),
                            request.getUserId(),
                            request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.desc(request.getSortParam())))));
        }
        return foundItems;
    }

    private Page<ItemDto> geAllItemsByUserId(ItemSearchRequest request) {
        Page<ItemDto> foundItems;
        if (request.getSortDirection().equals("ASC")) {
            foundItems = Converter.itemPageToDto(itemRepository
                    .findAllByUserId(request.getUserId(),
                            request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.asc(request.getSortParam())))));
        } else {
            foundItems = Converter.itemPageToDto(itemRepository
                    .findAllByUserId(request.getUserId(),
                            request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.desc(request.getSortParam())))));
        }
        return foundItems;
    }

    private Page<ItemDto> getAllItemsByCategoryId(ItemSearchRequest request) {
        Page<ItemDto> foundItems;
        if (request.getSortDirection().equals("ASC")) {
            foundItems = Converter.itemPageToDto(itemRepository
                    .findAllByCategoryId(request.getCategoryId(),
                            request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.asc(request.getSortParam())))));
        } else {
            foundItems = Converter.itemPageToDto(itemRepository
                    .findAllByCategoryId(request.getCategoryId(),
                            request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.desc(request.getSortParam())))));
        }
        return foundItems;
    }

    private Page<ItemDto> getAllItems(ItemSearchRequest request) {
        Page<ItemDto> foundItems;
        if (request.getSortDirection().equals("ASC")) {
            foundItems = Converter.itemPageToDto(itemRepository
                    .findAll(request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.asc(request.getSortParam())))));
        } else {
            foundItems = Converter.itemPageToDto(itemRepository
                    .findAll(request.getPhrase(),
                            PageRequest.of(request.getPage(), request.getSize(),
                                    Sort.by(Sort.Order.desc(request.getSortParam())))));
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
        return Converter.itemToDto((itemRepository.save(Converter.dtoToItem(itemDto))));
    }

    public void deleteItem(Long itemID) {
        itemRepository.deleteById(itemID);
    }

    public boolean isItemExists(Long itemId) {
        return !itemRepository.existsById(itemId);
    }
}
