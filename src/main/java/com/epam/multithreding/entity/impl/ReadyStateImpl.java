package com.epam.multithreding.entity.impl;

import com.epam.multithreding.entity.Port;
import com.epam.multithreding.entity.Ship;
import com.epam.multithreding.entity.ShipState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadyStateImpl implements ShipState {

    private static Logger logger = LogManager.getLogger();

    @Override
    public void changeState(Ship ship) {
        Port.getPort().occupyDock();
        logger.log(Level.INFO, "ship {} occupy dock", ship.getShipId());
        if (ship.getAvailableCargo() > 0) {
            ship.setCurrentState(new UnloadStateImpl());
        } else ship.setCurrentState(new LoadStateImpl());
    }
}
