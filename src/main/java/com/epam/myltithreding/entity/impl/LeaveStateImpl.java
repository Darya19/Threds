package com.epam.myltithreding.entity.impl;

import com.epam.myltithreding.entity.Port;
import com.epam.myltithreding.entity.Ship;
import com.epam.myltithreding.entity.ShipState;

public class LeaveStateImpl implements ShipState {

    @Override
    public void changeState(Ship ship) {
        Port.getPort().releaseDock();

    }
}