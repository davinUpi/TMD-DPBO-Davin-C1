/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewData;

/**
 *
 * @author 2100195
 * 
 * Kelas untuk menampug data2 Block
 * yang dibutuhkan untuk rendering
 * 
 */
public class BlockData extends GameObjectData {
    
    private int score;
    
    public BlockData (int x, int y, int width, int height, int score){
        super(x, y, width, height);
        this.score = score;
    }
    
    public int getScore(){
        return score;
    }
    
}
