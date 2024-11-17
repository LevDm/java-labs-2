package ru.dmitriev.MySecondTestAppSpringBoot.service;

import ru.dmitriev.MySecondTestAppSpringBoot.model.Positions;

public interface QuarterlyBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
}