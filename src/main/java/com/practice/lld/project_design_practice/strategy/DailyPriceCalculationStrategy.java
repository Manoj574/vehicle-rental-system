package com.practice.lld.project_design_practice.strategy;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
public class DailyPriceCalculationStrategy implements IPriceCalculationStrategy {

    @Override
    public Float getPrice(Date fromDate, Date toDate) {
        return Duration.between(fromDate.toInstant(), toDate.toInstant()).toDays() * 1000f;
    }
}
