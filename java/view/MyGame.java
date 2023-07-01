package view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.SwingUtilities;

/**
 *
 * @author Davin
 * 
 * kelas ini merupakan "hub" utama
 * berfungsi mirip dengan router untuk
 * merutekan ke tampilan2 yang ada
 * 
 */
public class MyGame {
    // WindoWView untuk membuat window
    final private WindowView window;
    
    // Tampilan2 yang ada
    private MainMenuView MainMenu;
    private GameView gameDisplay;
    
    public MyGame(){
        window = new WindowView("Paraiso");
        MainMenu = new MainMenuView(this);
        
        // Default langsung tampilkan main menu
        window.switchDisplay(MainMenu);
        start();
    }
    
    // metode untuk mengalihkan tampilan ke main menu
    public void switchToMainMenu(){
        // inisialisasi di sini guna refresh
        MainMenu = new MainMenuView(this);
        window.switchDisplay(MainMenu);
        start();
    }
    
    // metode untuk mengalihkan tampilan ke game
    public void switchToGameView(String name){
        
        // insiialisasi di sini guna refresh.
        // name di pass untuk mengambil data player
        // window juga di pass guna mendapatkan ukuran
        gameDisplay = new GameView(this, name, window);
        window.switchDisplay(gameDisplay);
        
        start();
        gameDisplay.startGame();
    }
    
    // untuk mulai menampilkan isi
    public final void start(){
        
        // menggunakan thread untuk menampilkan isi window
        SwingUtilities.invokeLater(() -> {
            window.setVisible(true);
        });
    }
    
    // untuk keluar dari program
    public void stop(){
        
        // menggunakan thread untuk menyembunyikan isi window
        // dan juga mematikan game
        SwingUtilities.invokeLater(() -> {
            window.dispose();
            System.exit(0);
        });
    }
}
