package com.epam.myltithreding.entity;

public enum PierState {

    OPEN,
    CLOSE;

    private PierState pierState;

    PierState(PierState pierState) {
        this.pierState = pierState;
    }

    PierState() {

    }
}
