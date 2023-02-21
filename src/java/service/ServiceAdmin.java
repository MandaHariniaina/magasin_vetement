/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import modele.Admin;

/**
 *
 * @author ACER
 */
public class ServiceAdmin {
    
    public boolean login(String password) throws Exception{
        Admin admin = new Admin();
        return admin.select(password);
    }
    
}
