package com.example.schach.client;

import java.io.Serializable;

public class Information implements Serializable {
    private String fieldName;
    private Integer x;
    private Integer y;

    public Information(String fieldName, Integer x, Integer y) {
        this.fieldName = fieldName;
        this.x = x;
        this.y = y;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Information{" +
                "fieldName='" + fieldName + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
