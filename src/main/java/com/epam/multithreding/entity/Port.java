package com.epam.multithreding.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {

    public static final Port port = new Port();
    private List<Dock> freeDocks;
    private List<Dock> occupiedDocks;
    private static final int MAX_NUMBER_OF_PIERS = 7;
    private static final int STOCK_CAPACITY = 30;
    private int takenPlaceInStock = 18;
    private Lock lockedDock = new ReentrantLock();
    private Lock lockedStock = new ReentrantLock();
    private Condition isFreeDock = lockedDock.newCondition();

    private static Logger logger = LogManager.getLogger();

    public Port() {
        this.freeDocks = new ArrayList<>(MAX_NUMBER_OF_PIERS);
        for (int i = 0; i < MAX_NUMBER_OF_PIERS; i++) {
            freeDocks.add(new Dock(i + 1, true));
        }
        this.occupiedDocks = new ArrayList<>(MAX_NUMBER_OF_PIERS);
    }

    public static Port getPort() {
        return port;
    }

    public Dock occupyDock() {
        Dock dock = null;
        try {
            lockedDock.lock();
            while (freeDocks.isEmpty()) {
                logger.log(Level.INFO, "dock list is empty, current ship wait free dock");
                isFreeDock.await();
            }
            if (freeDocks.size() > 0) {
                dock = freeDocks.get(0);
                if (dock.getDockState()) {
                    dock.occupyDock();
                }
                freeDocks.remove(dock);
                occupiedDocks.add(dock);
                logger.log(Level.INFO, "dock {} was occupied", dock.getDockId());
                isFreeDock.signal();
            }
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "lock was interrupted", e);
        } finally {
            lockedDock.unlock();
            return dock;
        }
    }

    public void releaseDock() {
        lockedDock.lock();
        Dock dock = occupiedDocks.get(0);
        if (!dock.getDockState()) {
            dock.releaseDock();
        }
        freeDocks.add(dock);
        occupiedDocks.remove(dock);
        logger.log(Level.INFO, "dock {} was released", dock.getDockId());
        isFreeDock.signal();
        lockedDock.unlock();
    }

    public void loadCargoToStock(Ship ship) {
        while (STOCK_CAPACITY - takenPlaceInStock > 0 && ship.getAvailableCargo() > 0) {
            try {
                lockedStock.lock();
                if (ship.unloadCargo()) {
                    logger.log(Level.INFO, "one cargo LOAD to stock");
                    takenPlaceInStock++;
                }
                lockedStock.unlock();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "thread was interrupted");
            }
        }
    }

    public void unloadCargoFromStock(Ship ship) {
        while (takenPlaceInStock > 0 && ship.getCapacityForLoad() > 0) {
            try {
                lockedStock.lock();
                if (ship.loadCargo()) {
                    logger.log(Level.INFO, "one cargo UNLOAD from stock");
                    takenPlaceInStock--;
                }
                lockedStock.unlock();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "thread was interrupted");
            }
        }
    }
}
