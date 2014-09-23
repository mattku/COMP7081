package common;

import java.io.Serializable;

/**
 * This object is sent to and from clients to servers.
 * When clients send the data to the server, the username and password
 * are stored in this object.  The password is passed along into this
 * object and hashed during the initialization.
 * @author Matthew Ku
 */
public class LoginMessage implements Serializable {
    private static final long serialVersionUID = 1112122202L;
    private final String userId;
    private final String passHash;
    /**
     * Constructor
     * userID: the username
     * password: Taken in plain text, formated into a hash string
     */
    public LoginMessage(String userID, String password) {
        this.userId = userID;
        this.passHash = Util.mySQLCompatibleMD5(password);
    }

    //Getter and setter methods
    public String getUserId() {
        return userId;
    }

    public String getPassHash() {
        return passHash;
    }
}
