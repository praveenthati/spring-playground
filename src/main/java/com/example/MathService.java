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

    String area(String shape, double width, double length, double radius) {
        if (shape == null)
            return "Invalid";

        switch (shape.toUpperCase()) {
            case "CIRCLE":

                Dimensions circle = new Dimensions();
                circle.setRadius(radius);
                circle.setType("circle");
                return String.format("Area of a circle with a radius of %f is %f", radius, circle.getArea());
            case "RECTANGLE":

                Dimensions rectangle = new Dimensions();
                rectangle.setLength(length);
                rectangle.setWidth(width);
                rectangle.setType("rectangle");
                return String.format("Area of a %fx%f rectangle is %f", length, width, rectangle.getArea());
            default:
                return "Invalid";
        }
    }

    String area(Dimensions dimensions) {
        if (dimensions == null || dimensions.getType() == null)
            return "Invalid";

        switch (dimensions.getType().toUpperCase()) {
            case "CIRCLE":
                if(dimensions.getRadius() <= 0)
                    return "Invalid";
                return String.format("Area of a circle with a radius of %f is %f", dimensions.getRadius(), dimensions.getArea());
            case "RECTANGLE":
                if(dimensions.getLength() <= 0)
                    return "Invalid";
                if(dimensions.getWidth() <= 0)
                    return "Invalid";
                return String.format("Area of a %fx%f rectangle is %f", dimensions.getLength(), dimensions.getWidth(), dimensions.getArea());
            default:
                return "Invalid";
        }
    }

}
