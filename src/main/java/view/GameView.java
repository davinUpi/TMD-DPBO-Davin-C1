/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.image.BufferStrategy;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import viewModel.GameViewModel;

/// pembungkus data2 untuk komunikasi
/// view dengan viewModel
import viewData.PlayerData;
import viewData.BlockData;


/**
 *
 * @author User
 */
public class GameView extends Canvas implements Runnable {
    
    private final WindowView window;
    private final MyGame game;
    private final GameViewModel viewModel;
    private final InputHandler controller;
    
    private Thread gameThread;
    private volatile boolean isRunning;
    private BufferStrategy bs;
    
    /// untuk refresh
    private final String playerName;
    
    /// untuk looping
    private static final int TARGET_FPS = 60;
    private static final long TARGET_FRAME_TIME = 1000000000 / TARGET_FPS;
    
    public GameView(MyGame daGem, String username, WindowView window){
        playerName = username;
        this.window = window;
        game = daGem;
        viewModel = new GameViewModel(username);
        controller = new InputHandler(viewModel, this);
        
        /// mendapatkan ukuran window
        viewModel.setWindowWidth(this.window.getWidth());
        viewModel.setWindowHeight(this.window.getHeight());
        
        /// mendengarkan input user
        addKeyListener(controller);
        
        /// memperhatikan perubahan ukuran window
        this.window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                viewModel.setWindowWidth(window.getWidth());
                viewModel.setWindowHeight(window.getHeight());
            }
        });
        
        
    }
    
    /// memulai permainan
    public void startGame(){
        isRunning = true;
        
        gameThread = new Thread(this);
        gameThread.start();
        
        /// input langsung terbaca di window
        requestFocus();
    }
    
    /// mengecek apakah game diakhiri
    private void checkGameOver(){
        if(viewModel.checkGameOver()){
            isRunning = false;
            
            /// menyimpan data user
            viewModel.saveGame();
             
            /// memberikan user pilihan
            /// untuk mulai lagi
            /// atau keluar
            int option = JOptionPane.showOptionDialog(
                    this, 
                    "Game over", 
                    "Game over", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.INFORMATION_MESSAGE, 
                    null, 
                    new String[]{"Restart", "Exit"}, 
                    "Restart");
            if(option == 0){
                /// delay sebelum restart untuk 
                /// menghindari exception
                Timer timer = new Timer(10, (e) -> {
                        game.switchToGameView(playerName);
                    });
                    timer.setRepeats(false);
                    timer.start();
            }
            else{
                /// delay sebelum restart untuk 
                /// menghindari exception
                Timer timer = new Timer(10, (e) -> {
                game.switchToMainMenu();
            });
            timer.setRepeats(false);
            timer.start();
            }
        }
    }
    
    /// untuk menggambarkan game
    private void renderGame(){
        
        /// menggunakan buffer agar tampilan halus
        bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(SOMEBITS);
            
            // skip rendering;
            return;
        }
        
        /// menggambar latar belakang
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        if(isRunning){
            PlayerData playerData = viewModel.getPlayerData();
            ArrayList<BlockData> blocks = viewModel.getBlocksData();
            int score = viewModel.getScore();
            int standing = viewModel.getStanding();
            
            renderPlayer( g,playerData);
            renderBlocks(g, blocks);
            renderScore(g, score);
            renderStanding(g, standing);
        }
        
        /// pergantian frame
        g.dispose();
        bs.show();
        
    }
    
    /// menggambarkan semua blok yang ada
    private void renderBlocks(Graphics g, ArrayList<BlockData> blocks){
        for(int i = 0; i < blocks.size(); i++){
            BlockData temp = blocks.get(i);
            /// penggambaran balok
            g.setColor(Color.BLUE);
            g.fillRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
            
            ///penggambarakn skor dari balok
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(
                    ""+temp.getScore(), 
                    temp.getX() + temp.getWidth(), 
                    temp.getY());
        }
    }
    
    /// menggambarkan player
    private void renderPlayer(Graphics g,PlayerData data){
        
        g.setColor(Color.RED);
        g.fillRect(data.getX(), data.getY(), data.getWidth(), data.getHeight());
    }
   
    /// menggambarkan score
    private void renderScore(Graphics g, int score){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 0, 30);
    }
    
    /// menggambarkan standing
    private void renderStanding(Graphics g, int standing){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Standing: " + standing, 0, 60);
    }
    
    @Override
    public void run(){
        long previousTime = System.nanoTime();
        long accumulakaka = 0;
        
        while(isRunning){
            long currentTime = System.nanoTime();
            long elapsedTime = currentTime - previousTime;
            
            previousTime = currentTime;
            accumulakaka += elapsedTime;
            
            while (accumulakaka >= TARGET_FRAME_TIME){
                viewModel.updateGame();
                accumulakaka -= TARGET_FRAME_TIME;
            }
            
            checkGameOver();
            renderGame();
        }
    }
    
    public boolean getRunningStatus(){
        return isRunning;
    }

}
