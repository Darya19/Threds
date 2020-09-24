package com.epam.myltithreding.entity;

import com.epam.myltithreding.exception.ProjectException;

public class Stock {

    private static Stock stock;
    private int takenPlace = 0;
    private final int stockCapacity = 10;

    private Stock() {
    }

    public static Stock getStock() {
        if (stock == null) {
            stock = new Stock();
        }
        return stock;
    }

    public boolean loadCargoToStock()  {
        if (stockCapacity - takenPlace <= 0) {
            return false;
        } else {
            takenPlace++;
            return true;
        }
    }

    public boolean unloadCargoFromStock() {
        if (takenPlace <= 0) {
            return false;
        } else {
            takenPlace--;
            return true;
        }
    }
}
