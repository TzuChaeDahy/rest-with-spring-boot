package br.com.tzuchaedahy.restwithspringboot.math;

import br.com.tzuchaedahy.restwithspringboot.math.converter.MathConverter;
import br.com.tzuchaedahy.restwithspringboot.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/math")
public class MathController {

    SimpleMath math = new SimpleMath();
    @RequestMapping(
            value = "/sum/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if (!MathConverter.isNumeric(numberOne) || !MathConverter.isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("please, set a numeric value.");
        }

        return math.sum(MathConverter.convertToDouble(numberOne), MathConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/subtract/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public double subtract(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if (!MathConverter.isNumeric(numberOne) || !MathConverter.isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("please, set a numeric value.");
        }

        return math.subtract(MathConverter.convertToDouble(numberOne), MathConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/multiply/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public double multiply(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if (!MathConverter.isNumeric(numberOne) || !MathConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("please, set a numeric value.");
        }

        return math.multiply(MathConverter.convertToDouble(numberOne), MathConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/divide/{numerator}/{denominator}",
            method = RequestMethod.GET
    )
    public double divide(
            @PathVariable(value = "numerator") String numerator,
            @PathVariable(value = "denominator") String denominator
    ) throws Exception {
        if (!MathConverter.isNumeric(numerator) || !MathConverter.isNumeric(denominator)) {
            throw new UnsupportedMathOperationException("please, set a numeric value.");
        }

        if (denominator.equals("0") || denominator.equals("0.0")) {
            throw new UnsupportedMathOperationException("please, set a not zero value!");
        }

        return math.divide(MathConverter.convertToDouble(numerator), MathConverter.convertToDouble(denominator));
    }

    @RequestMapping(
            value = "/media/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public double media(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if (!MathConverter.isNumeric(numberOne) || !MathConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("please, set a numeric value.");
        }

        return math.media(MathConverter.convertToDouble(numberOne), MathConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/root/{index}/{number}",
            method = RequestMethod.GET
    )
    public double root(
            @PathVariable(value = "index") String index,
            @PathVariable(value = "number") String number
    ) throws Exception {
        if (!MathConverter.isNumeric(index) || !MathConverter.isNumeric(number)) {
            throw new UnsupportedMathOperationException("please, set a numeric value.");
        }

        if (index.equals("0") || index.equals("0.0")) {
            throw new UnsupportedMathOperationException("please, set a not zero index");
        }

        return math.root(MathConverter.convertToDouble(number), MathConverter.convertToDouble(index));
    }
}
