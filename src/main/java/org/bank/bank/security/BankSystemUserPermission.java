package org.bank.bank.security;

public enum BankSystemUserPermission {

    USER("user"),
    ADMIN("admin");

    private String permission;

    BankSystemUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
