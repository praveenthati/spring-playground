package com.example;

public class Dimensions {

    private String type;
    private double radius;
    private double width;
    private double length;


    public String getType() {
        return this.type;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }


    public double getLength() {
        return this.length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getArea() {
        if(getType() == null)
            return 0;

        switch (getType().toUpperCase()) {
            case "CIRCLE":
                return Math.PI * getRadius() * getRadius();
            case "RECTANGLE":
                return getWidth() * getLength();
            default:
                return 0;
        }
    }
}

