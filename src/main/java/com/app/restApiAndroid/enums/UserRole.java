package com.app.restApiAndroid.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.StringJoiner;

public enum UserRole {
    ADMINS("admins"),
    PAGES("pages"),
    USERS("users"),
    NULLFIELD("campoVazio"),
    INVALIDO("invalido");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

    @JsonCreator
    public static UserRole fromString(String value) {

        if(value.isEmpty())
            return UserRole.NULLFIELD;

        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().trim().equalsIgnoreCase(value)) {
                return userRole;
            }
        }


        return UserRole.INVALIDO;
    }

    public static String userRolesText(){
        StringJoiner userRolesText = new StringJoiner(", ");

        for (UserRole userRole : UserRole.values()) {
            if(!userRole.name().equalsIgnoreCase(INVALIDO.name()) &&
            !userRole.name().equalsIgnoreCase(NULLFIELD.name()))
                userRolesText.add(userRole.name());
        }

        return userRolesText.toString();
    }
}
