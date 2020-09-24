package com.epam.myltithreding.entity;

import com.epam.myltithreding.exception.ProjectException;

import java.util.concurrent.Callable;

public class Ship implements Callable<Ship> {

    private int availableCargo;
    private int capacityForLoad;
    private ShipState currentState;

    public Ship(int availableCargo, int capacityForLoad) throws ProjectException {
        if (availableCargo < 0 || capacityForLoad < 0) {
            throw new ProjectException("incorrect input data");
        }
        this.availableCargo = availableCargo;
        this.capacityForLoad = capacityForLoad;
        this.currentState = new ReadyStateImpl();
    }

    public Ship call() throws Exception {
        return null;
    }

    public void setAvailableCargo(int availableCargo) throws ProjectException {
        if (availableCargo < 0) {
            throw new ProjectException("incorrect input data");
        }
        this.availableCargo = availableCargo;
    }

    public int getCapacityForLoad() {
        return capacityForLoad;
    }

    public void setCapacityForLoad(int capacityForLoad) throws ProjectException {
        if (capacityForLoad < 0) {
            throw new ProjectException("incorrect input data");
        }
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
        if (capacityForLoad > availableCargo) {
            availableCargo++;
            capacityForLoad--;
        } else {
            add = false;
        }
        return add;
    }

    public boolean unloadCargo() {
        boolean remove = true;
        if (availableCargo > 0) {
            availableCargo--;
            capacityForLoad++;
        } else {
            remove = false;
        }
        return remove;
    }
}
