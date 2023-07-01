/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 2100195
 * 
 * kelas untuk menampung 
 * data2 user
 * 
 */
public class User {
    
    private String name;
    private int score;
    private int landings;
    
    public User(String name){
        this.name = name;
        score = 0;
        landings = 0;
    }
    
    public User(String name, int score, int landings){
        this.name = name;
        this.score = score;
        this.landings = landings;
    }
    // Getters

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getLandings() {
        return landings;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLandings(int landings) {
        this.landings = landings;
    }
}
