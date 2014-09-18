/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import common.LoginMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import server.DB.DB;
import server.DB.Users;
import server.DB.Users.PwdResult;

/**
 *
 * @author Brian
 */
public class Login
{

    private Login()
    {
    }

	//  handle login message and return the correct type of user,    
	// or an anonymous user if the login info is not found
    public static User loginUser(Server server, Socket sock) throws IOException
    {
        ObjectOutputStream sOutput = new ObjectOutputStream(sock.getOutputStream());
        ObjectInputStream sInput = new ObjectInputStream(sock.getInputStream());
        try
        {
            LoginMessage msg = (LoginMessage) sInput.readObject();
            Connection conn = DB.connect();
            switch (Users.checkPassword(conn, msg.getUserId(), msg.getPassHash()))
            {
                case SUCCESS:
                    return new User(msg.getUserId(), msg.getPassHash(), Users.getRole(conn, msg.getUserId()), sInput, sOutput, server);
                default:
                    return new User("Anonymous", "", "", sInput, sOutput, server);
            }
        } catch (ClassNotFoundException ex)
        {
            return new User("Anonymous", "", "", sInput, sOutput, server);
        } catch (SQLException ex)
        {
            DB.printSQLException(ex);
            return new User("Anonymous", "", "", sInput, sOutput, server);
        }
    }
	
}
