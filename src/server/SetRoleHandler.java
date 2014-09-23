/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.sql.Connection;
import server.DB.DB;
import server.DB.Users;

/**
 *
 * @author Brian
 */
public class SetRoleHandler
{
    public static void handle(User user, String name, String newRole) throws Exception
    {
        Connection conn = DB.connect();
        if(user.getRole().canChangeRole(Users.getTeam(conn, name), newRole))
        {
            Users.setRole(conn, name, newRole);
            conn.close();
        }
        else
        {
            conn.close();
            throw new Exception();
        }
        
    }
}
