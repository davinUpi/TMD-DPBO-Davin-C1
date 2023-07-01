package view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Canvas;

/**
 *
 * @author 2100195
 * 
 * Kelas untuk membuat window
 * 
 */
public class WindowView extends JFrame {
    
    /// constructor yang hanya membutuhkan judul
    public WindowView(String title){
    setTitle(title);
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(new BorderLayout());
    setResizable(false);
    setVisible(true);
    }
    
    /// constructor yang membutuhkan judul dan ukurannya
    public WindowView(String title, int width, int height){
        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setResizable(false);
        setVisible(true);
    }
    
    /// metode untuk mengganti tampilan dengan swing/ awt component
    public void switchDisplay(Component component){
        getContentPane().removeAll();
        getContentPane().add(component, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    /// metode untuk mengganti tampilan dengan Cancas
    public void switchDisplay(Canvas canvas){
        getContentPane().removeAll();
        getContentPane().add(canvas, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
}
