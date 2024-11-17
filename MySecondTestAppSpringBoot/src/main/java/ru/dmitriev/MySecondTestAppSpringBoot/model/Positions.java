package ru.dmitriev.MySecondTestAppSpringBoot.model;


import lombok.Getter;

@Getter
public enum Positions {

    DEV(2.2, false),
    HR(1.2, false),
    TL(2.6, true),
    SA(1.7, false),
    QA(1.6, false),
    PM(2.8, true);

    private final double positionCoefficient;
    private final boolean isManager;

    Positions(double positionCoefficient, boolean isManager) {
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }
}