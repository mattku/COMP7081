/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import server.Server;
import server.User;
import java.net.Socket;
import java.sql.SQLException;
import server.DB.*;

public class Administrator extends User {

    public Administrator(String userID, String password, String role, String team
            , ObjectInputStream sInput, ObjectOutputStream sOutput, Server server) {
        super(userID, password, role, team, sInput, sOutput, server);
    }
    
    //Adds a user class somewhere
    private void addUser(User user) throws SQLException {
        Users.addUser(DB.connect(), user.getUserID(), user.getPassword(), user.getRole(), user.getTeam());
    }
}
