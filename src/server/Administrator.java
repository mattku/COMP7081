package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Extends the User class to provide
 * administrative powers to a User.
 * 
 * @author Matt
 */
public class Administrator extends User {

    public Administrator(String userID, String password, String role, String team
            , ObjectInputStream sInput, ObjectOutputStream sOutput, Server server) {
        super(userID, password, role, team, sInput, sOutput, server);
    }
    
}
