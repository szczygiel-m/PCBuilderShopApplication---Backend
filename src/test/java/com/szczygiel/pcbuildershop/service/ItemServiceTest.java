package com.szczygiel.pcbuildershop.service;

import com.szczygiel.pcbuildershop.DataTestSamples;
import com.szczygiel.pcbuildershop.dto.ItemDto;
import com.szczygiel.pcbuildershop.dto.ItemSearchRequest;
import com.szczygiel.pcbuildershop.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void setup() {
        itemRepository.saveAll(DataTestSamples.getItemSamples());
    }

    @AfterEach
    public void teardown() {
        entityManager
                .createNativeQuery("drop database test");
    }


    @Test
    public void validItemId_shouldReturnItem() {
        ItemDto actual = itemService.getItem(1L).orElseThrow();
        ItemDto expected = DataTestSamples.getItemDtoSamples().get(0);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCategoryId(), actual.getCategoryId());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getPrice(), actual.getPrice());
    }

    @Test
    public void invalidItemId_shouldNotReturnItem() {
        Optional<ItemDto> gotItem = itemService.getItem(1234L);

        assertEquals(Optional.empty(), gotItem);
    }


    @Test
    public void validCategoryIdAndUserId_shouldReturnOneItem() {
        ItemSearchRequest request = new ItemSearchRequest();
        request.setCategoryId(1L);
        request.setUserId(1L);

        List<ItemDto> expected = List.of(DataTestSamples.getItemDtoSamples().get(0));
        List<ItemDto> actual = itemService.getAllItemsByCategoryIdAndUserId(request).getContent();

        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getCategoryId(), actual.get(0).getCategoryId());
        assertEquals(expected.get(0).getUserId(), actual.get(0).getUserId());
        assertEquals(expected.get(0).getTitle(), actual.get(0).getTitle());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
        assertEquals(expected.get(0).getPrice(), actual.get(0).getPrice());
    }

    @Test
    public void inValidCategoryIdAndValidUserId_shouldReturnNoItem() {
        ItemSearchRequest request = new ItemSearchRequest();
        request.setCategoryId(112334L);
        request.setUserId(1L);

        List<ItemDto> expected = List.of();
        List<ItemDto> actual = itemService.getAllItemsByCategoryIdAndUserId(request).getContent();

        assertEquals(expected, actual);
    }

    @Test
    public void validCategoryIdAndInvalidUserId_shouldReturnNoItem() {
        ItemSearchRequest request = new ItemSearchRequest();
        request.setCategoryId(1L);
        request.setUserId(112345L);

        List<ItemDto> expected = List.of();
        List<ItemDto> actual = itemService.getAllItemsByCategoryIdAndUserId(request).getContent();

        assertEquals(expected, actual);
    }


    @Test
    public void validCategoryId_shouldReturnOneItem() {
        itemRepository.saveAll(DataTestSamples.getItemSamples());
        ItemSearchRequest request = new ItemSearchRequest();
        request.setCategoryId(3L);

        List<ItemDto> expected = List.of(DataTestSamples.getItemDtoSamples().get(2));
        List<ItemDto> actual = itemService.getAllItemsByCategoryId(request).getContent();

        assertEquals(expected.size(), actual.size());

        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getCategoryId(), actual.get(0).getCategoryId());
        assertEquals(expected.get(0).getUserId(), actual.get(0).getUserId());
        assertEquals(expected.get(0).getTitle(), actual.get(0).getTitle());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
        assertEquals(expected.get(0).getPrice(), actual.get(0).getPrice());
    }

    @Test
    public void SecondinValidCategoryId_shouldReturnNoItem() {
        ItemSearchRequest request = new ItemSearchRequest();
        request.setCategoryId(112334L);

        List<ItemDto> expected = List.of();
        List<ItemDto> actual = itemService.getAllItemsByCategoryId(request).getContent();

        assertEquals(expected, actual);
    }


    @Test
    public void validUserId_shouldReturnOneItem() {
        itemRepository.saveAll(DataTestSamples.getItemSamples());
        ItemSearchRequest request = new ItemSearchRequest();
        request.setUserId(3L);

        List<ItemDto> expected = List.of(DataTestSamples.getItemDtoSamples().get(2));
        List<ItemDto> actual = itemService.getAllItemsByUserId(request).getContent();

        assertEquals(expected.size(), actual.size());

        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getCategoryId(), actual.get(0).getCategoryId());
        assertEquals(expected.get(0).getUserId(), actual.get(0).getUserId());
        assertEquals(expected.get(0).getTitle(), actual.get(0).getTitle());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
        assertEquals(expected.get(0).getPrice(), actual.get(0).getPrice());
    }

    @Test
    public void inValidCategoryId_shouldReturnNoItem() {
        ItemSearchRequest request = new ItemSearchRequest();
        request.setUserId(112334L);

        List<ItemDto> expected = List.of();
        List<ItemDto> actual = itemService.getAllItemsByUserId(request).getContent();

        assertEquals(expected, actual);
    }

}
