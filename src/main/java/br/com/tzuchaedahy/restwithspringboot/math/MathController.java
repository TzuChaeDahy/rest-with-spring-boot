package br.com.tzuchaedahy.restwithspringboot.math;

import br.com.tzuchaedahy.restwithspringboot.math.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/math")
public class MathController {
    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}")
    public double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("please, set a numeric value.");
        }

        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/subtract/{numberOne}/{numberTwo}")
    public double subtract(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("please, set a numeric value.");
        }

        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/multiply/{numberOne}/{numberTwo}")
    public double multiply(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("please, set a numeric value.");
        }

        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/divide/{numerator}/{denominator}")
    public double divide(
            @PathVariable(value = "numerator") String numerator,
            @PathVariable(value = "denominator") String denominator
    ) throws Exception {
        if (!isNumeric(numerator) || !isNumeric(denominator)) {
            throw new UnsupportedMathOperationException("please, set a numeric value.");
        }

        if (denominator.equals("0") || denominator.equals("0.0")) {
            throw new UnsupportedMathOperationException("please, set a not zero value!");
        }

        return convertToDouble(numerator) / convertToDouble(denominator);
    }

    @RequestMapping(value = "/media/{numberOne}/{numberTwo}")
    public double media(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("please, set a numeric value.");
        }

        return (convertToDouble(numberOne) + convertToDouble(numberTwo))/2;
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null) {
            return false;
        }

        String number = strNumber.replace(",", ".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private double convertToDouble(String strNumber) {
        if (strNumber == null) {
            return 0D;
        }

        String number = strNumber.replace(",", ".");

        return Double.parseDouble(number);
    }
}
