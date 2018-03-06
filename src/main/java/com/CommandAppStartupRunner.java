package com;

import com.service.StatusReceiver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandAppStartupRunner implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger(CommandAppStartupRunner.class);

    private StatusReceiver statusReceiver;

    @Autowired
    public CommandAppStartupRunner(StatusReceiver statusReceiver) {
        this.statusReceiver = statusReceiver;
    }

    @Override
    public void run(String...args){

        LOGGER.debug("Starting to process feeds");
        try{
            statusReceiver.receiveFeeds();
        }
        catch (Exception e){
            LOGGER.error("Error processing feeds", e);
        }
    }
}