package br.com.tzuchaedahy.restwithspringboot.person.controller;

import br.com.tzuchaedahy.restwithspringboot.person.controller.dto.request.AccountCredentialsDTO;
import br.com.tzuchaedahy.restwithspringboot.person.controller.dto.response.TokenDTO;
import br.com.tzuchaedahy.restwithspringboot.person.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            description = "Authenticates a user"
    )
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AccountCredentialsDTO data) {
        if (data == null || !isStringParamValid(data.getUserName()) || !isStringParamValid(data.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid client request");
        }

        var token = authService.authenticate(data);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid token");
        }

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @Operation(
            summary = "Refresh an old user token",
            description = "Refresh an old user token"
    )
    @PutMapping("/refresh/{username}")
    public ResponseEntity<?> refreshToken(
            @PathVariable("username") String userName,
            @RequestHeader("Authorization") String refreshToken
    ) {
        if (!isStringParamValid(userName) || !isStringParamValid(refreshToken)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid client request");
        }

        var token = authService.refreshToken(refreshToken, userName);

        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid refresh token");
        }

        return token;
    }

    private static boolean isStringParamValid(String param) {
        if (param == null) {
            return false;
        } else return !param.isBlank();
    }
}
