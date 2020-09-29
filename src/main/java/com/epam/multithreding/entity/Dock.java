package com.epam.multithreding.entity;

public class Dock {

    private int dockId;
    private boolean isFree;

    public Dock(int dockId, boolean isFree) {
        this.dockId = dockId;
        this.isFree = isFree;
    }

    public int getDockId() {
        return dockId;
    }

    public void setDockId(int dockId) {
        this.dockId = dockId;
    }

    public boolean releaseDock() {
        isFree = true;
        return isFree;
    }

    public boolean occupyDock() {
        isFree = false;
        return isFree;
    }

    public boolean getDockState() {
        return isFree;
    }
}
