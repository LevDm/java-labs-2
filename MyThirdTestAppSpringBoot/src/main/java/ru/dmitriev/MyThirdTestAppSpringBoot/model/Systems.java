package ru.dmitriev.MyThirdTestAppSpringBoot.model;

import lombok.Getter;

@Getter
public enum Systems {
    ERP("Enterprise Resource Planning"),
    CRM("Customer Relationship Management"),
    WMS("Warehouse Management System"),
    SERVICE_1("Service 1");

    private final String name;
    Systems(String name) {
        this.name = name;
    }
}