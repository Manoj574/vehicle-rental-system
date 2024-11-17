package com.practice.lld.project_design_practice.strategy;

import java.util.Date;

public interface IPriceCalculationStrategy {

    Float getPrice(Date fromDate, Date toDate);
}
