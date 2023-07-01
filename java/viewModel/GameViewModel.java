/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

/// mendapatkan data user yg bermain
import model.UserTable;
import model.User;

import model.Player;
import model.Block;
import model.CollisionBox;
import model.CollisionSide;

import viewData.PlayerData;
import viewData.BlockData;

import view.MoveDirection;

/**
 *
 * @author 2100195
 * 
 * "Back end" game
 * mengurus game logic loop
 * 
 */
public class GameViewModel {
    
    /// untuk panjang dan score block
    private final Random random;
    
    /// untuk mengambil data user
    private final UserTable model;
    private final User user;
    
    /// gameObjects
    private Player player;
    private LinkedList<Block> blocks; 

    /// ukuran window
    private int windowWidth;
    private int windowHeight;
    
    private int score;
    private int standing;
    
    private boolean gameOver;
    
    public GameViewModel(String username){
        random = new Random();
        
        /// mengambil data user yg bermain
        model = new UserTable();
        user = model.selectByName(username);   
        score = user.getScore();
        standing = user.getLandings();
        
        
        player = new Player(0,0,25,25);
        blocks = new LinkedList<>();
        
        gameOver = false;
    }
    
    /// metode untuk mendapatkan data2 player
    /// guna rendering
    public PlayerData getPlayerData(){
        return new PlayerData(
                player.getX(),
                player.getY(),
                player.getWidth(),
                player.getHeight()
        );
    }
    
    /// metode untuk emndapatkan data2 Blocks
    /// guna rendering
    public ArrayList<BlockData> getBlocksData(){
        var temp = new ArrayList<BlockData>();
        blocks.forEach((n) -> temp.add( new BlockData(n.getX(), n.getY(), n.getWidth(), n.getHeight(), n.getScore())));
        return temp;
    }
    
    /// metode untuk mendapatkan score
    public int getScore(){
        return score;
    }
    
    /// metode untuk mendapatkan standing
    public int getStanding(){
        return standing;
    }
    
    /// metode untuk mendapatkan lebar window yang tersimpan
    public int getWindowWidth() {
        return windowWidth;
    }
    
    /// metode untuk menetapkan lebar window yang akan disimpan
    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    /// metode untuk mendapatkan tinggi window yang tersimpan
    public int getWindowHeight() {
        return windowHeight;
    }
    
    /// metode untuk menetapkan tinggi window yang akan disimpan
    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }
    
    /// manipulasi status game over
    public void setGameOver(boolean status){
        gameOver = status;
    }
    
    public boolean checkGameOver(){
        return gameOver;
    }
    
    /// metode untuk mengurusi pergerakkan player
    /// sesuai input
    /// terhubung dengan InputHandler
    public void setPlayerMovement(MoveDirection dir, MoveDirection status){
        
        /// Bergerak
        if(status == MoveDirection.MOVE){
            
            if(dir == MoveDirection.UP && player.getAllowedToJump()){
                player.setIsJump(true);
                player.setAllowedToJump(false);
            }
            
            if(dir == MoveDirection.LEFT){
                player.setGoLeft(true);
            }
            
            if(dir == MoveDirection.RIGHT){
                player.setGoRight(true);
            }
                    
        }
        
        /// Berhenti
        else if(status == MoveDirection.STOP){
            
            if(dir == MoveDirection.LEFT){
                player.setGoLeft(false);
            }
            
            if(dir == MoveDirection.RIGHT){
                player.setGoRight(false);
            }
        }
        
    }
    
    /// menghitung pergerakkan player
    private void calculatePlayerMovement(){
        
        /// pergerakkan dengan konsep percepatan konstan
        int acc = 2;
        int maxVel = 10;
        
        /// lompat
        int gravity = 1;
        int maxJumpHeight = -20;
        
        /// Ketika melompat, player akan bergerak
        /// ke atas dengan kecepatan VelY yang dipengaruhi
        /// oleh percepatan.
        /// setelah mencapai kecepatan max, mulai ditarik kebawah
        /// oleh gravitasi
        if(player.getIsJump()){
            player.addVelY(-acc);
            if(player.getVelY() <= maxJumpHeight) player.setIsJump(false);
        }
        else{
            player.addVelY(gravity);
            if(player.getVelY() > maxVel) player.setVelY(maxVel);
        }
        
        /// ke kanan atau kiri dengan akselerasi
        /// dan berhenti secara deakselerasi
        if(player.getGoRight()){
            player.addVelX(acc);
            if(player.getVelX() > maxVel) player.setVelX(maxVel);
        }
        else if(player.getGoLeft()){
            player.addVelX(-acc);
            if(player.getVelX() < -maxVel) player.setVelX(-maxVel);
        }
        else{
            if(player.getVelX() > 0){
                player.addVelX(-acc);
            }
            else if(player.getVelX() < 0){
                player.addVelX(acc);
            }
        }
        
    }
    
    /// metode yang mengurusi hubungan player
    /// dengan window
    private void boundPlayer(){
        CollisionBox tempBox = player.getCollisionBox();
        int trigger = 2; ///---> Semacam padding dari window

        /// Batas kiri dan kanan
        if(tempBox.getMinX() < trigger){
            player.setVelX(0);
            player.setX(trigger);
        } else if(tempBox.getMaxX() > windowWidth - trigger - (player.getWidth() / 2)){
            /// weird voodoo magic
            /// entah mengapa harus beigini
            
            player.setVelX(0);
            player.setX(windowWidth - trigger - player.getWidth() - (player.getWidth() / 2));
        }

        /// batas atas dan bawah
        if(tempBox.getMinY() < 0){
            player.setY(0);
        } else if(tempBox.getMaxY() > windowHeight -trigger){
            gameOver = true;
        }
    }
    
    /// Membuat Blocks yang dipijaki
    /// player
    private void generateBlocks(){
        
        /// membuat balok dengan
        /// panjang pseudorandom
        // beserta score setiap balok
        int interval = 50;
        int min = interval * 2;
        int max = windowWidth - min;
        int blockWidth = (random.nextInt((max - min) / interval) + min / interval) * interval;
        int blockScore = ((max + min) - blockWidth) / 10;
        
        if(!blocks.isEmpty()){
            
            /// block yang tidak terlihat dihapus
            if(blocks.getFirst().getY() < 0 - 50 ){
                blocks.removeFirst();
            }
            
            /// membuat balok untuk menggantikan
            /// balok yang dihapus
            if(blocks.size() < 7){
                blocks.addLast(new Block(
                    0, 
                    blocks.getLast().getY() + 100, 
                    blockWidth, 
                    50,
                    blockScore));
            }
        }
        else{
            /// Balok pertama
            blocks.add(new Block(
                    0, 
                    windowHeight + 25, 
                    blockWidth, 
                    50, 
                    blockScore));
        }
    }
    
    /// metode untuk cek collision
    private void collisionHandler(){
        
        /// cek Collision antara player dengan semua block
        for(int i = 0 ; i < blocks.size(); i++){
            if(player.isCollided(blocks.get(i).getCollisionBox())){
                Block block = blocks.get(i);
                CollisionSide side = block.getCollisionSide(player);
                if(null != side)switch (side) {
                    /// bila player mendarat,
                    /// player bisa lompat lagi
                    /// score bertambah, standing bertambah
                    /// bila baru dipijak pertama kali
                    case TOP -> {
                        player.setVelY(0);
                        player.setY(block.getY() - ((block.getHeight() / 2) + 1));
                        player.setAllowedToJump(true);
                        if(block.getHasBeenLanded() == false){
                            score += block.getScore();
                            standing++;
                            block.setHasBeenLanded(true);
                        }
                    }
                    case BOTTOM -> {
                        player.setVelY(0);
                        player.setY(block.getY() + block.getHeight());
                        player.setIsJump(false);
                    }
                    case RIGHT -> {
                        player.setVelX(0);
                        player.setX(block.getX() + block.getWidth());
                    }
                    default -> { /// kiri tidak dihandle karena mustahil
                    }
                }
                
            }
        }
    }

    /// metode untuk memperbaharui game state
    public void updateGame(){
        calculatePlayerMovement();
        boundPlayer();
        generateBlocks();
        for(int i = 0; i < blocks.size(); i++){
            blocks.get(i).update();
        }
        player.update();
        collisionHandler();
    }
    
    /// metode untuk menyimpan data player
    public void saveGame(){
        user.setScore(score);
        user.setLandings(standing);
        model.update(user);
    }
    
}
