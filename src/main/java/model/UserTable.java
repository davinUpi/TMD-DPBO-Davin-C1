/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 2100195
 * 
 * kelas untuk berinteraksi dengan
 * tabel user (sekarang tscore)
 * di basis data
 * 
 */
public class UserTable extends Database {
    
    /// memasukkan data baru ke db
    public int insert(User user){
        String query = "INSERT into tscore(username, score, standing) values ( ?, ?,?)";
        try {
            return this.executeEdit(query, user.getName(), user.getScore(), user.getLandings());
        } catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }
    
    /// memperbaharui data user tertentu
    public int update(User user){
        String query = "UPDATE tscore set score = ? , standing = ? WHERE username = ?";
        try{
            return this.executeEdit(query, user.getScore(), user.getLandings(), user.getName());
        } catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }
    
    /// menghapus data user tertentu
    public int delete(User user){
        String query = "DELETE FROM tscore where username = ?";
        try {
            return this.executeEdit(query, user.getName());
        } catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }
    
    /// mengambil data semua user
    public ArrayList<User> select(){
        var arr = new ArrayList<User>();
        
        String query = "SELECT * FROM tscore";
        try (ResultSet rs = this.executeQuery(query)){
            
            while(rs.next()){
                arr.add(new User(
                        rs.getString("username"), 
                        rs.getInt("score"), 
                        rs.getInt("standing")
                ));
            } 
            return arr;
        }catch (SQLException e){
             e.printStackTrace();
        }
        return null;
    }
    
    /// mengambil data user dengan nama/ username tertentu
    public User selectByName(String name){
        String query = "SELECT * FROM tscore WHERE username = ?";
        try(ResultSet rs = this.executeQuery(query, name)){
            
            if(rs.next()){
                return new User(
                        rs.getString("username"),
                        rs.getInt("score"),
                        rs.getInt("standing")
                );
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
}
