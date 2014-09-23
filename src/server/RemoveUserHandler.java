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
public class RemoveUserHandler
{
    public static void handle(User user, String name) throws Exception
    {
        Connection conn;
        conn = DB.connect();
        if(user.getRole().canRemoveUser(Users.getTeam(conn, name)))
        {
            Users.removeUser(conn, name);
            conn.close();
            return;
        }
        conn.close();
        throw new Exception();
    }
}
