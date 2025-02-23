package com.example.limepay.config;

import org.mockserver.integration.ClientAndServer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ApplicationTestConfiguration {

    final static ClientAndServer mockServer = ClientAndServer.startClientAndServer(1080);

    @Bean(destroyMethod = "stop")
    public ClientAndServer mockServer() {
        return mockServer;
    }
}
