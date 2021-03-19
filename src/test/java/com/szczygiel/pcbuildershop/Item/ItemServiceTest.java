package com.szczygiel.pcbuildershop.Item;

import com.szczygiel.pcbuildershop.DataTestSamples;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void validItemId_shouldReturnItem() {
        //given
        Item addedItem = itemRepository.save(DataTestSamples.getItemSamples().get(0));
        //when
        ItemDto actual = itemService.getItem(addedItem.getId()).orElseThrow();
        //then
        assertEquals(addedItem.getId(), actual.getId());
        assertEquals(addedItem.getCategory().getId(), actual.getCategoryId());
        assertEquals(addedItem.getUserProfile().getId(), actual.getUserId());
        assertEquals(addedItem.getTitle(), actual.getTitle());
        assertEquals(addedItem.getDescription(), actual.getDescription());
        assertEquals(addedItem.getPrice(), actual.getPrice());
    }

    @Test
    public void invalidItemId_shouldNotReturnItem() {
        //given
        //when
        Optional<ItemDto> gotItem = itemService.getItem(1234L);
        //then
        assertEquals(Optional.empty(), gotItem);
    }


    @Test
    public void validCategoryIdAndUserId_shouldReturnOneItem() {
        //given
        List<Item> addedItems = itemRepository.saveAll(DataTestSamples.getItemSamples());
        ItemSearchRequest request = new ItemSearchRequest();
        request.setCategoryId(addedItems.get(0).getCategory().getId());
        request.setUserId(addedItems.get(0).getUserProfile().getId());
        //when
        List<Item> expected = List.of(addedItems.get(0));
        List<ItemDto> actual = itemService.getAllItemsByCategoryIdAndUserId(request).getContent();
        //then
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getCategory().getId(), actual.get(0).getCategoryId());
        assertEquals(expected.get(0).getUserProfile().getId(), actual.get(0).getUserId());
        assertEquals(expected.get(0).getTitle(), actual.get(0).getTitle());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
        assertEquals(expected.get(0).getPrice(), actual.get(0).getPrice());
    }

    @Test
    public void inValidCategoryIdAndValidUserId_shouldReturnNoItem() {
        //given
        ItemSearchRequest request = new ItemSearchRequest();
        request.setCategoryId(112334L);
        request.setUserId(1L);
        //when
        List<ItemDto> expected = List.of();
        List<ItemDto> actual = itemService.getAllItemsByCategoryIdAndUserId(request).getContent();
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void validCategoryIdAndInvalidUserId_shouldReturnNoItem() {
        //given
        ItemSearchRequest request = new ItemSearchRequest();
        request.setCategoryId(1L);
        request.setUserId(112345L);
        //when
        List<ItemDto> expected = List.of();
        List<ItemDto> actual = itemService.getAllItemsByCategoryIdAndUserId(request).getContent();
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void inValidCategoryId_shouldReturnNoItem() {
        //given
        ItemSearchRequest request = new ItemSearchRequest();
        request.setCategoryId(112334L);
        //when
        List<ItemDto> expected = List.of();
        List<ItemDto> actual = itemService.getAllItemsByCategoryId(request).getContent();
        //then
        assertEquals(expected, actual);
    }


    @Test
    public void validUserId_shouldReturnOneItem() {
        //given
        List<Item> addedItems = itemRepository.saveAll(DataTestSamples.getItemSamples());
        ItemSearchRequest request = new ItemSearchRequest();
        request.setUserId(addedItems.get(2).getUserProfile().getId());
        //when
        List<Item> expected = List.of(addedItems.get(2));
        List<ItemDto> actual = itemService.getAllItemsByUserId(request).getContent();
        //then
        assertEquals(expected.size(), actual.size());

        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getCategory().getId(), actual.get(0).getCategoryId());
        assertEquals(expected.get(0).getUserProfile().getId(), actual.get(0).getUserId());
        assertEquals(expected.get(0).getTitle(), actual.get(0).getTitle());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
        assertEquals(expected.get(0).getPrice(), actual.get(0).getPrice());
    }
}
