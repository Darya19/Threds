package com.epam.multithreding.parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ShipParser {

    private static final String REGEX = "\\s";

    private static Logger logger = LogManager.getLogger();

    public List<Integer> parseShipCharacteristics(String value) {
        List<Integer> characteristics = new ArrayList<>();
        try {
            String[] stringCharacteristics = value.split(REGEX);
            for (String characteristic : stringCharacteristics) {
                int intValue = Integer.parseInt(characteristic);
                characteristics.add(intValue);
            }
            logger.log(Level.INFO, "characteristics of a ship was parsed successfully");
            return characteristics;
        } catch (NumberFormatException e) {
            logger.log(Level.WARN, "parsing issues");
        }
        return characteristics;
    }
}
