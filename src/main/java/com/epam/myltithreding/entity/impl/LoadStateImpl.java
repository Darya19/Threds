package com.epam.myltithreding.entity.impl;

import com.epam.myltithreding.entity.Ship;
import com.epam.myltithreding.entity.ShipState;
import com.epam.myltithreding.entity.Stock;

public class LoadStateImpl implements ShipState {

    @Override
    public void changeState(Ship ship) {
        Stock stock = Stock.getStock();
        while (ship.loadCargo()) {
            stock.unloadCargoFromStock();
        }
        ship.setCurrentState(new LeaveStateImpl());
    }
}

