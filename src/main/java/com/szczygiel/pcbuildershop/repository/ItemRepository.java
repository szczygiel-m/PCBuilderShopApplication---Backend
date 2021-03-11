package com.szczygiel.pcbuildershop.repository;

import com.szczygiel.pcbuildershop.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findAllByCategory_Category(String category);
}
