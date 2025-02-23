package com.example.limepay.controller;

import com.example.limepay.model.Response;
import com.example.limepay.model.director.DirectorsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;

public interface MovieDirectorApi {

    @Operation(summary = "Get movie directors",
            description = "Get movie directors with more than X number of movies directed",
            tags = { "movie-director" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned directors that directed more than X number of movies",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DirectorsResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid threshold value",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No directors found with more than X number of movies",
                    content = @Content) })
    ResponseEntity<Response<DirectorsResponse>> getMovieDirector(
            @Parameter(description = "Threshold value to filter directors by number of movies directed",
                    required = true, example = "1")
            @Min(1) int threshold);
}
