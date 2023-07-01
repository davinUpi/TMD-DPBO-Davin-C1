package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 2100195
 * 
 * Kelas yang mengadung hal2 yang dibutuhkan
 * mayoritas objek dalam game
 * 
 */
abstract public class GameObject {
    
    protected int x, y;
    protected int width, height;
    protected int velX, velY;
    protected CollisionBox colBox;
    
    public GameObject(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colBox = new CollisionBox(x,y,width, height);
    }
    
    //setter
    public void setX(int x) {
        this.x = x;
        updateCollisionBox();
    }

    public void setY(int y) {
        this.y = y;
        updateCollisionBox();
    }

    public void setWidth(int width) {
        this.width = width;
        updateCollisionBox();
    }

    public void setHeight(int height) {
        this.height = height;
        updateCollisionBox();
    }
    
    public void setVelX(int velX){
        this.velX = velX;
    }
    
    public void setVelY(int velY){
        this.velY = velY;
    }
    
    public void setColBox(CollisionBox colBox){
        this.colBox = colBox;
    }
    
    // adder(?)
    
    public void addVelX(int delta){
        velX += delta;
    }
    
    public void addVelY(int delta){
        velY += delta;
    }
    
    //getter
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public int getVelX(){
        return velX;
    }
    
    public int getVelY(){
        return velY;
    }

    public CollisionBox getCollisionBox() {
        return colBox;
    }
    
    //collisionBox handling
    protected void updateCollisionBox() {
        colBox.setMinX(x);
        colBox.setMinY(y);
        colBox.setMaxX(x + width);
        colBox.setMaxY(y + height);
    }
    
    /// perpanjangan dari metode di kelas Collision Box
    public boolean collidesWith(GameObject other) {
        return colBox.isCollided(other.getCollisionBox());
    }

    public CollisionSide getCollisionSide(GameObject other) {
        return colBox.getCollisionSide(other.getCollisionBox());
    }
    
    public abstract void update();
    
}
