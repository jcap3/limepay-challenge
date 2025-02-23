package com.example.limepay;

import com.example.limepay.config.ApplicationTestConfiguration;
import com.example.limepay.config.TestCacheConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import({ApplicationTestConfiguration.class, TestCacheConfig.class})
public class LimePayApplicationTests {

    protected static final String BASE_PATH = "/api/directors";

    protected static final String GET_DIRECTORS_BY_MOVIE_DIRECTED_API = BASE_PATH + "/by-movie-count";

    @BeforeEach
    void setUp() {
        mockServer.reset();
    }

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ClientAndServer mockServer;

}
