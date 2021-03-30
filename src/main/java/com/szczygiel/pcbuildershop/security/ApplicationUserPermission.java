package com.szczygiel.pcbuildershop.security;

public enum ApplicationUserPermission {
    ITEM_ADD("item:add"),
    ITEM_UPDATE("item:update"),
    ITEM_DELETE("item:delete");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
