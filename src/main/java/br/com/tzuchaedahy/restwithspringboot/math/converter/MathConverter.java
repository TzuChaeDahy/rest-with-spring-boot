package br.com.tzuchaedahy.restwithspringboot.math.converter;

public class MathConverter {
    public static boolean isNumeric(String strNumber) {
        if (strNumber == null) {
            return false;
        }

        String number = strNumber.replace(",", ".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public static double convertToDouble(String strNumber) {
        if (strNumber == null) {
            return 0D;
        }

        String number = strNumber.replace(",", ".");

        return Double.parseDouble(number);
    }
}
