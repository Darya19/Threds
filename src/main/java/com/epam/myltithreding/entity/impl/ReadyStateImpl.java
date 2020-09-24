package com.epam.myltithreding.entity.impl;

import com.epam.myltithreding.entity.Port;
import com.epam.myltithreding.entity.Ship;
import com.epam.myltithreding.entity.ShipState;

public class ReadyStateImpl implements ShipState {
    @Override
    public void changeState(Ship ship) {
        Port.getPort().occupyDock();
        if (ship.getAvailableCargo() > 0) {
            ship.setCurrentState(new LoadStateImpl());
        } else ship.setCurrentState(new UnloadStateImpl());
    }
}
