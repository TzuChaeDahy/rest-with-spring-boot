package br.com.tzuchaedahy.restwithspringboot.math;

public class SimpleMath {
    public double sum(double numberOne, double numberTwo) {
        return numberOne + numberTwo;
    }

    public double subtract(double numberOne, double numberTwo) {
        return numberOne - numberTwo;
    }

    public double multiply(double numberOne, double numberTwo) {
        return numberOne * numberTwo;
    }

    public double divide(double numberOne, double numberTwo) {
        return numberOne / numberTwo;
    }

    public double media(double numberOne, double numberTwo) {
        return (numberOne + numberTwo)/2;
    }

    public double root(double numberOne, double numberTwo) {
        return Math.pow(numberOne, (1/numberTwo));
    }
}
