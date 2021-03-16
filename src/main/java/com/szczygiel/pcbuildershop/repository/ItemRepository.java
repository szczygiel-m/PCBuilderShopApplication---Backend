package com.szczygiel.pcbuildershop.repository;

import com.szczygiel.pcbuildershop.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where (i.title like %?1%)")
    Page<Item> findAll(String phrase, PageRequest of);

    @Query("SELECT i from Item i where (i.category.Id = ?1) and (i.title like %?2%)")
    Page<Item> findAllByCategoryId(Long categoryId, String phrase, PageRequest of);

    @Query("SELECT i from Item i where (i.userProfile.Id = ?1) and (i.title like %?2%)")
    Page<Item> findAllByUserId(Long userId, String phrase, PageRequest of);

    @Query("SELECT i from Item i where (i.category.Id = ?1) and (i.userProfile.Id = ?2) and (i.title like %?3%)")
    Page<Item> findAllByCategoryIdAndUserId(Long categoryId, Long userId, String phrase, PageRequest of);
}
