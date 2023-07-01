/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/// konfigurasi basis data sesaui
/// komputer masing2
import config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


/**
 *
 * @author 2100195
 * 
 * kelas abstract untuk berinteraksi
 * dengan basis data mysql
 * 
 */
abstract public class Database {
    
    protected Connection mysqli;
    
    private final String url;
    private final String dbuser;
    private final String dbpass;
    
    public Database(){
        url = "jdbc:mysql://"+DatabaseConfig.dbhost+"/" + DatabaseConfig.dbname;
        dbuser = DatabaseConfig.dbuser;
        dbpass = DatabaseConfig.dbpass;
        connect();
    }
    
    /// berusaha untuk terhubung dengan basis data
    final public void connect(){
        try{
            mysqli = DriverManager.getConnection(url, dbuser, dbpass);
        }catch (SQLException e){
            e.printStackTrace();
        }       
    }
    
    /// menjalankan SELECT dengan query murni
    protected ResultSet executeQuery(String query){
        try {
            Statement stmt = mysqli.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch(SQLException e){
            e.printStackTrace();    
        }
        return null;
    }
    
    /// menjalankan SELECT dengan query prepared
    protected ResultSet executeQuery(String query, Object... params){
    try {
        PreparedStatement stmt = mysqli.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt.executeQuery();
    } catch (SQLException e){
        e.printStackTrace();
    }
    return null;
    }
    
    /// menjalankan query manipulasi data dengan query
    protected int executeEdit(String query) throws SQLException{
        Statement stmt = mysqli.createStatement();
        return stmt.executeUpdate(query);
    }
    
    /// menjalankan query manipulasi data dengan query prepared
    protected int executeEdit(String query, Object... params) throws SQLException {
        try (PreparedStatement stmt = mysqli.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            return stmt.executeUpdate();
        }
    }
   
    /// menutup hubungan ke basis data
    public void close(){
        try{
            if(mysqli != null && !mysqli.isClosed()){
                mysqli.close();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
