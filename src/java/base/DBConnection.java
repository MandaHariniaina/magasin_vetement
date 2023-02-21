/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Manda
 */
public class DBConnection {
    public static Connection createConnection() throws Exception
    {
        Connection c=null;
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","venteVetements","mdpprom13");
            c.setAutoCommit(false);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return c;
    }
}
