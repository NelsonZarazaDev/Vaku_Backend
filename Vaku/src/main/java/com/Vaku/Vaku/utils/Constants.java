package com.Vaku.Vaku.utils;

import lombok.Getter;

@Getter
public enum Constants {
    CHILD_ALREADY_EXISTS("El niño ya existe"),
    PARENT_ALREADY_EXISTS("El padre de familia ya existe"),
    EMPLOYEE_ALREADY_EXISTS("El empleado ya existe"),
    EMAIL_ALREADY_EXISTS("El email ya existe"),
    PHONE_ALREADY_EXISTS("El telefono ya existe"),
    EMAIL_EMPTY("El email no puede estar vacio"),
    PHONE_EMPTY("El telefono no puede estar vacio"),
    PASSWORD_EMPTY("El telefono no puede estar vacio"),
    CHILD_NOT_EXISTS("El niño no existe"),
    INVENTORIE_NOT_EXISTS("El inventario no existe"),
    CREDENTIAL_INVALID("Credenciales invalidas!");

    private final String message;

    Constants(String s) {
        this.message = s;
    }
}