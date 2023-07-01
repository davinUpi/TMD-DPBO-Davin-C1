/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
public class Player extends GameObject{
    
    private int accX;
    private int accY;
    
    private boolean goUp;
    private boolean goLeft;
    private boolean goRight;
    
    private boolean allowedToJump;
    private boolean isJump;

    public Player(int x, int y, int width, int height){
        super(x, y, width, height);
        velX = 0;
        velY = 0;
        
        accX = 0;
        accY = 0;
        
        goUp = goLeft = goRight = isJump = allowedToJump = false;
    }
    
    public boolean isCollided(CollisionBox otherBox){
        return getCollisionBox().isCollided(otherBox);
    }
    
    public void setAccX(int acc){
        accX = acc;
    }
    
    public void setAccY(int acc){
        accY = acc;
    }
    
    public void setGoRight(boolean status){
        goRight = status;
    }
    
    public void setGoLeft(boolean status){
        goLeft = status;
    }
    
    public void setGoUp (boolean status){
        goUp = status;
    }
    
    public void setIsJump(boolean status){
        isJump = status;
    }
    
    public void setAllowedToJump(boolean status){
        allowedToJump = status;
    }
    
    public int getAccX(){
        return accX;
    }
    
    public int getAccY(){
        return accY;
    }
    
    public boolean getGoUp(){
        return goUp;
    }
    
    public boolean getGoLeft(){
        return goLeft;
    }
    
    public boolean getGoRight(){
        return goRight;
    }
    
    public boolean getAllowedToJump(){
        return allowedToJump;
    }
    
    public boolean getIsJump(){
        return isJump;
    }
    
    public void addAccX(int delta){
        accX += delta;
    }
    
    public void addAccY(int delta){
        accY += delta;
    }
    
    
    /// hanya pergerakkan
    @Override
    public void update(){
        x += velX;
        y += velY;
        updateCollisionBox();
    }
    
}
