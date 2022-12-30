package com.vector;

import lombok.Data;

@Data
public class Item {
    private long id;
    private String name;
    private long quantity;
    private int costPerUnit;
    private String unit;
}
