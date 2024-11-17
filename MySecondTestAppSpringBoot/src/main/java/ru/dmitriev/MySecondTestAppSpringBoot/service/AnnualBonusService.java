package ru.dmitriev.MySecondTestAppSpringBoot.service;

import ru.dmitriev.MySecondTestAppSpringBoot.model.Positions;

public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
}