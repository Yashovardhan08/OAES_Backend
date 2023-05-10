package com.iiitb.dbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class DBServiceApplication {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(DBServiceApplication.class);
        logger.log(Level.INFO,"STARTED THE APPLICATION");
        SpringApplication.run(DBServiceApplication.class, args);
        logger.log(Level.INFO,"STOPPED THE APPLICATION");
    }
}
