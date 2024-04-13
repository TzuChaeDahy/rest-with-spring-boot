package br.com.tzuchaedahy.restwithspringboot.person.controller;
import br.com.tzuchaedahy.restwithspringboot.exceptions.ExceptionResponse;
import br.com.tzuchaedahy.restwithspringboot.person.model.Person;
import br.com.tzuchaedahy.restwithspringboot.person.service.PersonService;
import br.com.tzuchaedahy.restwithspringboot.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/person")
@Tag(

        name = "Person",
        description = "Endpoints to manage people"
)
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML}
    )
    @Operation(
            summary = "Find a person by id",
            description = "Find a person by id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = Person.class)
                                    // TODO: IMPLEMENT DTO STRUCTURE
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<Person> findByID(@PathVariable(value = "id") long id) {
        Person person = personService.findByID(id);

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping(
            value = "/all",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML}
    )
    @Operation(
            summary = "Find all people",
            description = "Find all people",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Person.class)
                                    )
                                    // TODO: IMPLEMENT DTO STRUCTURE
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<Person>> findAll() {
        List<Person> people = personService.findAll();

        return new ResponseEntity<>(people, HttpStatus.OK);
    }
    @Operation(
            summary = "Persist a person",
            description = "Persist a person",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "201",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = Long.class)
                                    // TODO: IMPLEMENT DTO STRUCTURE
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    )
            }
    )
    @PostMapping(
            value = "/create",
            consumes = {MediaType.APPLICATION_JSON},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML}
    )

    public ResponseEntity<Long> create(@RequestBody @Validated Person person) {
        Long id = personService.create(person);

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/update",
            consumes = {MediaType.APPLICATION_JSON},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML}
    )
    @Operation(
            summary = "Update a person",
            description = "Update a person",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = Long.class)
                                    // TODO: IMPLEMENT DTO STRUCTURE
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<Long> update(@RequestBody Person person) {
        Long id =  personService.update(person);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(
            summary = "Delete a person",
            description = "Delete a person",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> delete(@PathVariable long id) {
        personService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
