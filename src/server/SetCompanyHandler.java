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
public class SetCompanyHandler
{

    static void handle(User pUser, String username, String newCompany) throws Exception
    {
        if(pUser.getRole().canSetCompany(username, newCompany))
        {
            try(Connection conn = DB.connect())
            {
                Users.setCompany(conn, username, newCompany);
            }
        }
        else throw new Exception("Insufficient permission");
    }
    
}
