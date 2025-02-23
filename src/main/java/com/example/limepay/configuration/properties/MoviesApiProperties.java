package com.example.limepay.configuration.properties;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Configuration
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties("demo")
public class MoviesApiProperties {

    @NotEmpty
    String basePath;

    @NotEmpty
    String movies;

    @Min(5)
    int timeout;
}
