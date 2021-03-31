package com.szczygiel.pcbuildershop.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Long itemId) {
        super("Could not find item with id = " + itemId);
    }
}
