package br.com.tzuchaedahy.restwithspringboot.greeting;

import br.com.tzuchaedahy.restwithspringboot.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@Tag(name = "Greeting", description = "Endpoints to manage greetings")
public class GreetingController {
    private static final String template = "hello, %s!";
    private static final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    @Operation(
            summary = "Greets a user",
            description = "Greets a user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            schema = @Schema(implementation = Greeting.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public Greeting greeting(@RequestParam(name = "name", defaultValue = "world") String name) {
        return new Greeting(
                counter.incrementAndGet(),
                String.format(template, name)
        );
    }
}
