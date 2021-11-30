package com.epam.configservermrc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerMrcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerMrcApplication.class, args);
    }

}
