package br.com.tzuchaedahy.restwithspringboot.person.controller;

import br.com.tzuchaedahy.restwithspringboot.person.controller.dto.request.AccountCredentialsDTO;
import br.com.tzuchaedahy.restwithspringboot.person.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
@Tag(
        name = "Authentication",
        description = "Endpoints to manage authentication"
)
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(
            summary = "Authenticates a user",
            description = "Authenticates a user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(

                            )
                    )
            }
    )
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AccountCredentialsDTO data) {
        if (IsAccountCredentialsInvalid(data)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid client request");
        }

        var token = authService.authenticate(data);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid token");
        }

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    private static boolean IsAccountCredentialsInvalid(AccountCredentialsDTO data) {
        return data == null ||
                data.getUserName() == null || data.getPassword() == null ||
                data.getUserName().isBlank() || data.getPassword().isBlank();
    }
}
