package com.verhees.cm.security;

public enum UserRoles {
    ADMIN("ADMIN"),
    USER("ROLE_USER");

    public String type;

    UserRoles(String type) {
        this.type = type;
    }
}
