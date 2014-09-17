/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;
import main.Util;

/**
 * This object is to and from clients to servers.
 * When clients send the data to the server, the username and password
 * are stored in this object.  The password is passed along into this
 * object and hashed during the initialization.
 * @author A00795612
 */
public class LoginMessage implements Serializable {
    
    private String userId;
    private String password;
    /**
     * Constructor
     * userID: the username
     * password: Taken in plain text, formated into a hash string
     */
    LoginMessage(String userID, String password) {
        this.userId = userID;
        this.password = Util.mySQLCompatibleMD5(password);
    }

    //Getter and setter methods
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
