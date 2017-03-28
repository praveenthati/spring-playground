package com.example;


import java.util.List;

class MathService {

    String calculate(String operation, int x, int y) {

        if (operation == null)
            return "specify operation";

        switch (operation) {
            case "add":
                return Integer.toString(x) + " + " + Integer.toString(y) + " = " + Integer.toString(x + y);
            case "multiply":
                return Integer.toString(x) + " * " + Integer.toString(y) + " = " + Integer.toString(x * y);
            case "divide":
                if (y == 0)
                    return "y cannot be zero";

                return Integer.toString(x) + " / " + Integer.toString(y) + " = " + Integer.toString(x / y);
            case "subtract":
                return Integer.toString(x) + " - " + Integer.toString(y) + " = " + Integer.toString(x - y);
            default:
                return "valid operations are add/divide/multiply/subtract";

        }

    }

    String sum(List<String> params) {

        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;

        if (params != null && params.size() > 0) {

            for (int index = 0; index < params.size(); index++) {

                try {
                    i = i + Integer.parseInt(params.get(index));

                    stringBuilder.append(params.get(index));


                    if (index != params.size() - 1) {
                        stringBuilder.append(" + ");
                    }
                } catch (Exception ex) {
                    return "Integer values expected";
                }
            }


            stringBuilder.append(" = ");
            stringBuilder.append(Integer.toString(i));

            return stringBuilder.toString();
        }

        return "0";
    }


}
