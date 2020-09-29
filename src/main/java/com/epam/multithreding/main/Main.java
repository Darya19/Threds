package com.epam.multithreding.main;

import com.epam.multithreding.builder.ShipBuilder;
import com.epam.multithreding.entity.Ship;
import com.epam.multithreding.parser.ShipParser;
import com.epam.multithreding.reader.ReaderFromFile;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ReaderFromFile reader = new ReaderFromFile();
        ShipParser parser = new ShipParser();
        ShipBuilder builder = new ShipBuilder();
        List<String> shipValues = reader.readFromFile("data\\inputData.txt");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (String line : shipValues) {
            List<Integer> intValues = parser.parseShipCharacteristics(line);
            Ship ship = builder.buildShip(intValues);
            Future<Ship> future = executorService.submit(ship);
        }
        executorService.shutdown();
    }
}
