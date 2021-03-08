package com.szczygiel.pcbuildershop.repository;

import com.szczygiel.pcbuildershop.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
