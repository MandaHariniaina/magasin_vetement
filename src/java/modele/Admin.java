/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
import base.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ACER
 */
public class Admin {
    
    private String password;
    
    public boolean select(String p) throws Exception{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBConnection.createConnection();
            st = con.prepareStatement("SELECT * FROM admin WHERE password='"+p+"'");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        finally{
            st.close();
            con.close();
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
