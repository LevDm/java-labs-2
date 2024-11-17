package ru.dmitriev.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.dmitriev.MySecondTestAppSpringBoot.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnnualBonusServiceImpTest {

    @Test
    void calculateManager() {
        Positions position = Positions.TL;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;
        var result = new QuarterlyBonusServiceImp().calculate(position, salary, bonus, workDays);
        double expected = 194732.51028806584362139917695473;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateNotManager() {
        Positions position = Positions.SA;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;
        var result = new QuarterlyBonusServiceImp().calculate(position, salary, bonus, workDays);
        double expected = 0;
        assertThat(result).isEqualTo(expected);
    }
}