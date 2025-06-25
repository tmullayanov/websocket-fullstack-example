package com.wfh.example.backend.endpoints.rest;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @Operation(summary = "Sample controller that welcomes you")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Welcome!",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HelloResponse.class)) })
            })
    @GetMapping("/hello")
    public ResponseEntity<HelloResponse> hello() {
        var isHello = true;
        var resp = new HelloResponse("hello", isHello);

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @AllArgsConstructor
    @Getter
    public static class HelloResponse {
        @Schema(description = "text of response", required = true)
        private String response;

        @JsonProperty("isHello")
        @Schema(description = "isHello23", name="isHello")
        private boolean isHello;

        @JsonGetter("isHello")
        public boolean isHello() {
            return isHello;
        }
    }
}
