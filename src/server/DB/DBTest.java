/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.DB;

import java.sql.Connection;
import java.sql.SQLException;
import common.Util;

/**
 *
 * @author Brian
 */
public class DBTest
{
    public static void main(String[] args)
    {
        try
        {
            Connection conn = DB.connect();
            
            Users.addUser(conn, "testUser", "testPwd", "test");
            assert(Users.checkPassword(conn, "testUser", "testPwd") == Users.PwdResult.SUCCESS);
            Users.removeUser(conn, "testUser");
            assert(Users.checkPassword(conn, "testUser", "testPwd") == Users.PwdResult.NO_SUCH_USER);
            
            assert(Users.checkPassword(conn, "user", Util.mySQLCompatibleMD5("password")) == Users.PwdResult.SUCCESS);
            assert(Users.checkPassword(conn, "notaname", Util.mySQLCompatibleMD5("password")) == Users.PwdResult.NO_SUCH_USER);
            assert(Users.checkPassword(conn, "user", Util.mySQLCompatibleMD5("notpassword")) == Users.PwdResult.INCORRECT_PASSWORD);
            
            assert(Users.getRole(conn, "user").equals("user"));
            assert(Users.getRole(conn, "admin").equals("admin"));
            assert(Users.getRole(conn, "notaname") == null);
            
            System.out.println("Test success!");
        }
        catch(SQLException sqlE)
        {
            DB.printSQLException(sqlE);
        }
    }
}
