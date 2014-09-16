/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import java.io.Serializable;
import main.ChatMessage;
import main.Util;

/**
 *
 * @author Brian
 */
public class AddUserMessage extends ChatMessage implements Serializable
{
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
