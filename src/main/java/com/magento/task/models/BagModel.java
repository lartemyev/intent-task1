package com.magento.task.models;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class BagModel {

    private final String NOT_AVAILABLE = "<not available>";

    private String title;
    private Double rating;
    private MoneyModel price;

}
