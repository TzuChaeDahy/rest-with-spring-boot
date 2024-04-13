package br.com.tzuchaedahy.restwithspringboot.math;

import br.com.tzuchaedahy.restwithspringboot.exceptions.ExceptionResponse;
import br.com.tzuchaedahy.restwithspringboot.exceptions.UnsupportedMathOperationException;
import br.com.tzuchaedahy.restwithspringboot.math.converter.MathConverter;
import br.com.tzuchaedahy.restwithspringboot.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/math")
@Tag(name = "Math", description = "Endpoints for managing math operations")
public class MathController {

    SimpleMath math = new SimpleMath();

    @GetMapping(
            value = "/sum/{numberOne}/{numberTwo}"
    )
    @Operation(
            summary = "Sum two numbers",
            description = "Sum two numbers",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = String.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = " Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
            }
    )
    public double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        if (!MathConverter.isNumeric(numberOne) || !MathConverter.isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("please, set a numeric value.");
        }

        return math.sum(MathConverter.convertToDouble(numberOne), MathConverter.convertToDouble(numberTwo));
    }

    @GetMapping(
            value = "/subtract/{numberOne}/{numberTwo}"
    )
    @Operation(
            summary = "Subtract two numbers",
            description = "Subtract two numbers",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = String.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = " Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
            }
    )
    public double subtract(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        if (!MathConverter.isNumeric(numberOne) || !MathConverter.isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("please, set a numeric value.");
        }

        return math.subtract(MathConverter.convertToDouble(numberOne), MathConverter.convertToDouble(numberTwo));
    }

    @GetMapping(
            value = "/multiply/{numberOne}/{numberTwo}"
    )
    @Operation(
            summary = "Multiply two numbers",
            description = "Multiply two numbers",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = String.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = " Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
            }
    )
    public double multiply(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        if (!MathConverter.isNumeric(numberOne) || !MathConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("please, set a numeric value.");
        }

        return math.multiply(MathConverter.convertToDouble(numberOne), MathConverter.convertToDouble(numberTwo));
    }

    @GetMapping(
            value = "/divide/{numerator}/{denominator}"
    )
    @Operation(
            summary = "Divide two numbers",
            description = "Divide two numbers",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = String.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = " Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
            }
    )
    public double divide(
            @PathVariable(value = "numerator") String numerator,
            @PathVariable(value = "denominator") String denominator
    ) {
        if (!MathConverter.isNumeric(numerator) || !MathConverter.isNumeric(denominator)) {
            throw new UnsupportedMathOperationException("please, set a numeric value.");
        }

        if (denominator.equals("0") || denominator.equals("0.0")) {
            throw new UnsupportedMathOperationException("please, set a not zero value!");
        }

        return math.divide(MathConverter.convertToDouble(numerator), MathConverter.convertToDouble(denominator));
    }

    @GetMapping(
            value = "/media/{numberOne}/{numberTwo}"
    )
    @Operation(
            summary = "Media of two numbers",
            description = "Media of two numbers",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = String.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = " Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
            }
    )
    public double media(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        if (!MathConverter.isNumeric(numberOne) || !MathConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("please, set a numeric value.");
        }

        return math.media(MathConverter.convertToDouble(numberOne), MathConverter.convertToDouble(numberTwo));
    }

    @GetMapping(
            value = "/root/{index}/{number}"
    )
    @Operation(
            summary = "Root of a number by an index",
            description = "Root of a number by an index",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = String.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = " Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
            }
    )
    public double root(
            @PathVariable(value = "index") String index,
            @PathVariable(value = "number") String number
    ) {
        if (!MathConverter.isNumeric(index) || !MathConverter.isNumeric(number)) {
            throw new UnsupportedMathOperationException("please, set a numeric value.");
        }

        if (index.equals("0") || index.equals("0.0")) {
            throw new UnsupportedMathOperationException("please, set a not zero index");
        }

        return math.root(MathConverter.convertToDouble(number), MathConverter.convertToDouble(index));
    }
}
