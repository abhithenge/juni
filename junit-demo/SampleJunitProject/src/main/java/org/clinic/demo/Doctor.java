package org.clinic.demo;

public enum Doctor {
    ajay("Dr Ajay"),
    sunita("Dr Sunita"),
    mangesh("Dr Mangesh");

    private String name;

    Doctor(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
