package com.epam.myltithreding.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {

    public static Port port;
    private List<Dock> freeDocks;
    private List<Dock> occupiedDocks;
    private static final int MAX_NUMBER_OF_PIERS = 7;
    private Lock lock = new ReentrantLock();
    private Condition isFree = lock.newCondition();

    public Port() {
        this.freeDocks = new ArrayList<>(MAX_NUMBER_OF_PIERS);
        for (int i = 0; i < MAX_NUMBER_OF_PIERS; i++) {
            freeDocks.add(new Dock());
        }
        this.occupiedDocks = new ArrayList<>(MAX_NUMBER_OF_PIERS);
    }

    public static Port getPort() {
        if (port == null) {
            port = new Port();
        }
        return port;
    }

    public Dock occupyDock() {
        Dock dock = null;
        try {
            lock.lock();
            while (freeDocks.isEmpty()) {
                isFree.await();
            }
            if (freeDocks.size() > 0) {
               dock = freeDocks.get(0);
               freeDocks.remove(dock);
                occupiedDocks.add(dock);
                isFree.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        lock.unlock();
            return dock;
    }
    }

    public void releaseDock() {
        lock.lock();
        Dock dock = occupiedDocks.get(0);
            freeDocks.add(dock);
            occupiedDocks.remove(dock);
            isFree.signal();
            lock.unlock();
    }
}
