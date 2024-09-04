package com.arithmetic.calculator.utils;

import java.util.Random;

public class OperationUtil {


    public static Double getResultFromOperation(String operationType, double n1, double n2) {
        double result;
        switch (operationType) {
            case "addition":
                result = n1 + n2;
                break;
            case "subtraction":
                result = n1 - n2;
                break;
            case "multiplication":
                result = n1 * n2;
                break;
            case "division":
                result = n2 != 0 ? n1 / n2 : Double.NaN;
                break;
            case "square_root":
                result = Math.sqrt(n1);
                break;
            case "random_string":
                result = new Random().nextInt();
                break;
            default:
                return null;
        }
        return result;
    }

}
