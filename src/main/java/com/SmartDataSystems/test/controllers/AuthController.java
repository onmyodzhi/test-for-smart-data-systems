package com.SmartDataSystems.test.controllers;

import com.SmartDataSystems.test.models.DTOs.AuthDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {

    @Operation(
            summary = "User Authentication",
            description = "The method authenticates the user via Keycloak and returns an access_token.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Authentication successful, access token returned.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "string", example = "token_string")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Incorrect authentication data.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Server Error.",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PostMapping("/auth")
    String auth(
            @Parameter(description = "Authentication data", required = true)
            @RequestBody AuthDto authDto
    );
}
