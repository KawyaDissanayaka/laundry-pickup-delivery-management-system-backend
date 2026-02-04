package com.laundry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class LaundryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaundryApplication.class, args);
    }

    @Component
    public static class DataSeederListener {
        private final com.laundry.config.DataSeeder dataSeeder;

        public DataSeederListener(com.laundry.config.DataSeeder dataSeeder) {
            this.dataSeeder = dataSeeder;
        }

        @EventListener(ApplicationReadyEvent.class)
        public void onApplicationReady() {
            dataSeeder.seedData();
        }
    }
}
