package com.epam.multithreding.entity.impl;

import com.epam.multithreding.entity.Port;
import com.epam.multithreding.entity.Ship;
import com.epam.multithreding.entity.ShipState;

public class UnloadStateImpl implements ShipState {

    @Override
    public void changeState(Ship ship) {
        Port.getPort().loadCargoToStock(ship);
        ship.setCurrentState(new LoadStateImpl());
    }
}
