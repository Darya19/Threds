package com.epam.multithreding.entity.state.impl;

import com.epam.multithreding.entity.Port;
import com.epam.multithreding.entity.Ship;
import com.epam.multithreding.entity.state.ShipState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LeaveStateImpl implements ShipState {

    private static Logger logger = LogManager.getLogger();

    @Override
    public void changeState(Ship ship) {
        Port.getPort().releaseDock();
        logger.log(Level.INFO, "ship {} release dock and leaving state", ship.getShipId());
        ship.setCurrentState(new ReadyStateImpl());
    }
}