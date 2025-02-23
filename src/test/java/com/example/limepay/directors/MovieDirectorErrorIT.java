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

public class MovieDirectorErrorIT extends LimePayApplicationTests {

    @Test
    public void testGetDirectorsByMoviesDirectedErrorNoDirectors() throws Exception {

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
                .param("threshold", "99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("999")))
                .andExpect(jsonPath("$.message", is("error")))
                .andExpect(jsonPath("$.errorCode.code", is("002")))
                .andExpect(jsonPath("$.errorCode.message", is("No directors directed more than the requested threshold")))
                .andDo(print());
    }

    @Test
    public void testGetDirectorsByMoviesDirectedErrorInvalidThreshold() throws Exception {

        mockMvc.perform(get(GET_DIRECTORS_BY_MOVIE_DIRECTED_API)
                        .param("threshold", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("999")))
                .andExpect(jsonPath("$.message", is("error")))
                .andExpect(jsonPath("$.errorCode.code", is("001")))
                .andExpect(jsonPath("$.errorCode.message", is("Invalid requested threshold")))
                .andDo(print());
    }

    @Test
    public void testGetDirectorsByMoviesDirectedErrorInternalError() throws Exception {
        mockServer.when(HttpRequest.request("/api/movies/search")
                        .withMethod("GET")
                        .withQueryStringParameter(Parameter.param("page", "1")))
                .respond(HttpResponse.response()
                        .withStatusCode(500));

        mockMvc.perform(get(GET_DIRECTORS_BY_MOVIE_DIRECTED_API)
                        .param("threshold", "1"))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.code", is("999")))
                .andExpect(jsonPath("$.message", is("error")))
                .andExpect(jsonPath("$.errorCode.code", is("999")))
                .andExpect(jsonPath("$.errorCode.message", is("Internal Server Error")))
                .andDo(print());
    }
}