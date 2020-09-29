package com.epam.multithreding.entity.state;

import com.epam.multithreding.entity.Ship;

public interface ShipState {

    void changeState(Ship ship);
}
