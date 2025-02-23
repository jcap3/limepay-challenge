package com.example.limepay;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LimePayApplication.class})
@AutoConfigureMockMvc
class LimePayApplicationTests {

    protected static final String BASE_PATH = "/api/directors";

    protected static final String GET_DIRECTORS_BY_MOVIE_DIRECTED_API = BASE_PATH + "/by-movie-count";

    protected static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    protected MockMvc mockMvc;

    @BeforeClass
    public static void beforeClass() {
    }

    @Before
    public void before() {

    }

    @AfterClass
    public static void afterClass() {
    }

    @After
    public void after() {

    }
}
