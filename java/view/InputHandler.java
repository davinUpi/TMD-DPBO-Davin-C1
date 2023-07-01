/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import viewModel.GameViewModel;

/**
 *
 * @author 2100195
 * 
 * kelas untuk mengatasi input keyboard user
 */
public class InputHandler extends KeyAdapter implements KeyListener{
    
    private final GameViewModel viewModel;
    private final GameView game;
    
    public InputHandler(GameViewModel vm, GameView gim){
        viewModel = vm;
        game = gim;
    }
    
    @Override
    public synchronized void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        
        if(game.getRunningStatus()){
            
            /// gerak ke kiri dengan menekan tombol A atau panah kiri
            if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
                viewModel.setPlayerMovement(MoveDirection.LEFT,MoveDirection.MOVE);
            }
            
            /// gerak ke kanan bila menekan tombol D atau panah kanan
            if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
                viewModel.setPlayerMovement(MoveDirection.RIGHT,MoveDirection.MOVE);
            }
        }
    }
    
    @Override
    public synchronized void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(game.getRunningStatus()){
            /// melompat bila memencet dan melepaskan tombol W atau panah atas
            if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP){
                viewModel.setPlayerMovement(MoveDirection.UP, MoveDirection.MOVE);
            }
            
            /// berhenti bergerak ke kiri ketika
            /// tombol A atau panah kiri dilepas
            if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
                viewModel.setPlayerMovement(MoveDirection.LEFT, MoveDirection.STOP);
            }
            
            /// berhenti bergerak ke kanan ketika
            /// tombol D atau panah kanan dilepas
            if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
                viewModel.setPlayerMovement(MoveDirection.RIGHT, MoveDirection.STOP);
            }
            
            /// mengakhiri permainan ketika spasi ditekan lalu dilepaskan
            if(keyCode == KeyEvent.VK_SPACE){
                viewModel.setGameOver(true);
            }
        }
    }
    
}
