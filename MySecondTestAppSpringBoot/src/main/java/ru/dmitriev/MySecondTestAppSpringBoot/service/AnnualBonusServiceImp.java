package ru.dmitriev.MySecondTestAppSpringBoot.service;


import org.springframework.stereotype.Service;
import ru.dmitriev.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;


@Service
public class AnnualBonusServiceImp implements AnnualBonusService{
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        int daysInYear = isLeapYear(Year.now().getValue()) ? 366 : 365;
        return salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;
    }
}