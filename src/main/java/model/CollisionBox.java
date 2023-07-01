/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Davin
 */
public class CollisionBox {
    
    //The minimal values
    private int minX;
    private int minY;
    
    // the maximum values
    private int maxX;
    private int maxY;
    
    public CollisionBox(int x, int y, int width, int height) {
        

        this.minX = x;
        this.minY = y;
        this.maxX = x + width - 1;
        this.maxY = y + height - 1;
    }
    
    // move the box
    public void translate(int deltaX, int deltaY){
        
        minX += deltaX;
        maxX += deltaX;
        
        minY += deltaY;
        maxY += deltaY;
    }
    
    //sclae the box
    public void scale(int scale){
        // get the box's width and height
        int width = this.maxX - this.minX;
        int height = this.maxY - this.minY;
        
        // find the center of the box
        int centerX = (this.minX + this.maxX) / 2;
        int centerY = (this.minY + this.maxY) / 2;
        
        // set the box to its scaled size
        int halfWidth = (width * scale) / 2;
        int halfHeight = (height * scale) / 2;
        
        this.minX = centerX - halfWidth;
        this.minY = centerY - halfHeight;
        this.maxX = centerX + halfWidth;
        this.maxY = centerY + halfHeight;
    }
    
    /// detect if the box collided with another one
    public boolean isCollided(CollisionBox otherBox) {
    return (minX <= otherBox.getMaxX() &&
            maxX >= otherBox.getMinX() &&
            minY <= otherBox.getMaxY() &&
            maxY >= otherBox.getMinY());
    }
    
    /// get whhich side of the box is collided with
    public CollisionSide getCollisionSide(CollisionBox otherBox){
        int overlapX = Math.min(this.maxX, otherBox.getMaxX()) - Math.max(this.minX, otherBox.getMinX());
        int overlapY = Math.min(this.maxY, otherBox.getMaxY()) - Math.max(this.minY, otherBox.getMinY());
        
        if(overlapX > overlapY){
            if(this.minY < otherBox.getMinY()){
                return CollisionSide.BOTTOM;
            }
            else{
                return CollisionSide.TOP;
            }
        }
        else{
            if(this.minX < otherBox.getMinX()){
                return CollisionSide.RIGHT;
            }
            else{
                return CollisionSide.LEFT;
            }
        }
    }
    
    // setter
    public void setMinX(int minX) {
        this.minX = minX;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }
    
    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }
    
    // getter
    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }
    
    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
    
}
