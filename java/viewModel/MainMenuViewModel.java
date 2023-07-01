/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import model.UserTable;
import model.User;
import java.util.ArrayList;

/**
 *
 * @author 2100195
 * 
 * kelas "backend" Main Menu
 * 
 */
public class MainMenuViewModel {
    
    /// atribut untuk mengambil dan menampung
    /// data2 user
    private ArrayList<User> data;
    final private UserTable model;
    
    public MainMenuViewModel(){
        data = new ArrayList<>();
        model = new UserTable();
    }
    
    /// mengambil data2 user terbaru
    public void fetchUserData(){
        model.connect();
        data = model.select();
        model.close();
    }
    
    /// memberikan data2 user
    public ArrayList<User> getData(){
        return data;
    }
    
    /// mengambil data user dengan nama tertentu
    /// dari data yang sudah diambil
    public User getDataByName(String name){
        User temp = null;
        if(data != null){
            temp = data.stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        }
        return temp;
    }
    
    /// memasukkan user baru ke basis data
    public void insertUser(String name){
        User temp = new User(name);
        
        model.connect();
        model.insert(temp);
        model.close();
    }
    
}
