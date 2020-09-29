package com.epam.multithreding.entity.state.impl;

import com.epam.multithreding.entity.Port;
import com.epam.multithreding.entity.Ship;
import com.epam.multithreding.entity.state.ShipState;

public class LoadStateImpl implements ShipState {

    @Override
    public void changeState(Ship ship) {
        Port.getPort().unloadCargoFromStock(ship);
        ship.setCurrentState(new LeaveStateImpl());
    }
}

