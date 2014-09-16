/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.net.Socket;
import java.sql.SQLException;
import server.DB.*;

public class Administrator extends User {

    public Administrator(String userID, String role, String password
            , Socket socket, Server server) {
        super(userID, role, password, socket, server);
    }
    
    //Adds a user class somewhere
    private void addUser(User user) throws SQLException {
        Users.addUser(DB.connect(), user.getUserID(), user.getPassword(), user.getRole());
    }
}
