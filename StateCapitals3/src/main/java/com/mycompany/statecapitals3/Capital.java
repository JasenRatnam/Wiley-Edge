/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.statecapitals3;

/**
 *
 * @author TheBoss
 */
public class Capital {
    private String name;
    private int population;
    private double squareMilage;

    public Capital(String name, int population, double squareMilage) {
        this.name = name;
        this.population = population;
        this.squareMilage = squareMilage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getSquareMilage() {
        return squareMilage;
    }

    public void setSquareMilage(double squareMilage) {
        this.squareMilage = squareMilage;
    }

    @Override
    public String toString() {
        return "Capital{" + "name=" + name + ", population=" + population + ", squareMilage=" + squareMilage + '}';
    }
    
    
    
}
