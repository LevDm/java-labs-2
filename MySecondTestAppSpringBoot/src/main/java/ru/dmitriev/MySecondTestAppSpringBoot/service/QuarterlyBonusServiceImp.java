package ru.dmitriev.MySecondTestAppSpringBoot.service;


import ru.dmitriev.MySecondTestAppSpringBoot.model.Positions;

public class QuarterlyBonusServiceImp implements QuarterlyBonusService {
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        if (positions.isManager()) {
            return salary * bonus * 91 * positions.getPositionCoefficient() / workDays;
        } else {
            return 0;
        }
    }
}