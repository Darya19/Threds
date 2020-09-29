package com.epam.multithreding.builder;

import com.epam.multithreding.entity.Ship;

import java.util.List;

public class ShipBuilder {

    private static int id = 1;

    public Ship buildShip(List<Integer> characteristics) {
        Ship ship = new Ship(id++, characteristics.get(0), characteristics.get(1));
        return ship;
    }
}
