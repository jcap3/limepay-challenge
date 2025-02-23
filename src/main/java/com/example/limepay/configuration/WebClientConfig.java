package com.example.limepay.configuration;

import com.example.limepay.configuration.properties.MoviesApiProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final MoviesApiProperties moviesApiProperties;

    public WebClientConfig(MoviesApiProperties moviesApiProperties) {
        this.moviesApiProperties = moviesApiProperties;
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(moviesApiProperties.getBasePath())
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
