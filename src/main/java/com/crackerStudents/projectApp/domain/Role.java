package com.crackerStudents.projectApp.domain;

import org.springframework.security.core.GrantedAuthority;

// Granted Authority - Предоставленные полномочия
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name(); // это будет строковое представление значения
    }
}
