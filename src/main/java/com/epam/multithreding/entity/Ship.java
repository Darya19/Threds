package com.epam.multithreding.entity;

import com.epam.multithreding.entity.state.impl.ReadyStateImpl;
import com.epam.multithreding.entity.state.ShipState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;

public class Ship implements Callable<Ship> {

    private int shipId;
    private int availableCargo;
    private int capacityForLoad;
    private ShipState currentState;

    private static Logger logger = LogManager.getLogger();

    public Ship(int shipId, int availableCargo, int capacityForLoad) {
        this.shipId = shipId;
        this.availableCargo = availableCargo;
        this.capacityForLoad = capacityForLoad;
        this.currentState = new ReadyStateImpl();
        logger.log(Level.DEBUG, "ship {} was created, current state = ready", shipId);
    }

    public Ship call() throws Exception {
        do {
            ShipState state = this.getCurrentState();
            state.changeState(this);
        }
        while (!this.getCurrentState().getClass().equals(ReadyStateImpl.class));
        return this;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public void setAvailableCargo(int availableCargo) {
        this.availableCargo = availableCargo;
    }

    public int getCapacityForLoad() {
        return capacityForLoad;
    }

    public void setCapacityForLoad(int capacityForLoad) {
        this.capacityForLoad = capacityForLoad;
    }

    public int getAvailableCargo() {
        return availableCargo;
    }

    public ShipState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ShipState currentState) {
        this.currentState = currentState;
    }

    public boolean loadCargo() {
        boolean add = true;
        if (capacityForLoad > 0) {
            availableCargo++;
            capacityForLoad--;
            logger.log(Level.INFO, "one cargo LOAD to ship {}", shipId);
        } else {
            add = false;
            logger.log(Level.INFO, "impossible load cargo to ship {}", shipId);
        }
        return add;
    }

    public boolean unloadCargo() {
        boolean remove = true;
        if (availableCargo > 0) {
            availableCargo--;
            capacityForLoad++;
            logger.log(Level.INFO, "one cargo UNLOAD from ship {}", shipId);
        } else {
            remove = false;
            logger.log(Level.INFO, "impossible unload cargo from ship {}", shipId);
        }
        return remove;
    }
}
