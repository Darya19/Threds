package com.epam.myltithreding.entity;

public class Dock {

    private boolean isFree = true;

    public Dock() {
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
