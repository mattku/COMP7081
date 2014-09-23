package common;

import java.io.Serializable;

/**
 * Handles messages between the User and the Server.
 * Extends the ChatMessage class and provides
 * members for data specific to Users.
 * 
 * @author Brian
 * @author Matthew Ku
 */
public class AddUserMessage extends ChatMessage implements Serializable
{
    //User members to be stored within object and passed to server
    private static final long serialVersionUID = 1112122201L;
    private final String userId;
    private final String passHash;
    private final String role;
    private final String team;
    
    public AddUserMessage(String userId, String password, String role, String team)
    {
        super(0, "");
        this.userId = userId;
        this.passHash = Util.mySQLCompatibleMD5(password);
        this.role = role;
        this.team = team;
    }

    //Getter and Setter methods
    public String getUserId()
    {
        return userId;
    }

    public String getPassHash()
    {
        return passHash;
    }

    public String getRole()
    {
        return role;
    }

    public String getTeam()
    {
        return team;
    }
}
