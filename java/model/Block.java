/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 2100195
 * 
 * kelas gameObject ebrupa blok
 * yang dapat dipinjak oleh player
 * 
 */
public class Block extends GameObject {
    
    /// mecatat score miliknya
    private int score;
    
    /// mencatat apakah pernah dipinjak
    private boolean hasBeenLanded;
    
    public Block(int x, int y, int width, int height){
        super(x, y, width, height);
        velY = 2;
        hasBeenLanded = false;
    }
    
    public Block(int x, int y, int width, int height, int score){
        super(x, y, width, height);
        this.score = score;
        velY = 2;
        hasBeenLanded = false;
    }
    
    /// berusuan dengan status telah dipinjak
    public void setHasBeenLanded(boolean status){
        hasBeenLanded = status;
    }
    
    public boolean getHasBeenLanded(){
        return hasBeenLanded;
    }
    
    /// berurusan dengan score
    public void setScore(int score){
        this.score = score;
    }
    
    public int getScore(){
        return score;
    }
    
    /// Hanya gerak ke atas
    @Override
    public void update(){
        y -= velY;
        updateCollisionBox();
    }
    
}
