/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewData;

/**
 *
 * @author 2100195
 * 
 * kelas untuk emnampung data2 gameObject
 * yang dibutuhkan untuk rendering
 * 
 */
public class GameObjectData {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    
    public GameObjectData(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
}
