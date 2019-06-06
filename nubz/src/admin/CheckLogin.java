/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

/**
 *
 * @author kevingiovanni
 */
public class CheckLogin {
    private static String username;
    private static boolean status = false;
    
    public CheckLogin(String username, boolean status){
        this.setUsername(username);
        this.setStatus(status);
    }
    
    public static String getUsername(){
        return username;
    }
    
    public static void setUsername(String uname){
        username = uname;
    }
    
    public static boolean getStatus(){
        return status;
    }
    
    public static void setStatus(boolean state){
        status = state;
    }
}
