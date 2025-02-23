package com.example.limepay.directors;

import com.example.limepay.LimePayApplicationTests;
import org.junit.jupiter.api.Test;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;
import org.mockserver.model.Parameter;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MovieDirectorSuccessIT extends LimePayApplicationTests {

    @Test
    public void testGetDirectorsByMoviesDirectedSuccess() throws Exception {

        String page1 = new String(Files.readAllBytes(Paths.get("src/test/resources/testdataresponse/page1.json")));
        String page2 = new String(Files.readAllBytes(Paths.get("src/test/resources/testdataresponse/page2.json")));

        mockServer.when(HttpRequest.request("/api/movies/search")
                .withMethod("GET")
                .withQueryStringParameter(Parameter.param("page", "1")))
                .respond(HttpResponse.response()
                        .withBody(page1, MediaType.JSON_UTF_8)
                        .withStatusCode(200));

        mockServer.when(HttpRequest.request("/api/movies/search")
                        .withMethod("GET")
                        .withQueryStringParameter(Parameter.param("page", "2")))
                .respond(HttpResponse.response()
                        .withBody(page2, MediaType.JSON_UTF_8)
                        .withStatusCode(200));

        mockMvc.perform(get(GET_DIRECTORS_BY_MOVIE_DIRECTED_API)
                .param("threshold", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.directors").isArray())
                .andExpect(jsonPath("$.body.directors", hasSize(2)))
                .andExpect(jsonPath("$.body.directors[*]", hasItem(containsString("Christopher Nolan"))))
                .andExpect(jsonPath("$.body.directors[*]", hasItem(containsString("David Fincher"))))
                .andDo(print());
    }
}
