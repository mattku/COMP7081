package server;

import common.Util;
import java.sql.Connection;
import server.DB.*;

/**
 *
 * @author aequites
 */
public final class SlashCommand
{
    private SlashCommand() {}

    public static final boolean process(User u, String s)
    {
        if (u == null || s == null)
            return false;
        
        s = s.trim();
        if (s.length() == 0 || s.charAt(0) != '/')
            return false;

        String[] as = s.split("\\s+");
        s = as[0].toLowerCase();
        
        if (u.getRole().equals("admin"))
        {
            if (s.equals("/adduser"))
            {
                if (as.length < 4)
                    s = "Server> Syntax: /adduser name password role [team]\n";
                else
                try
                {
                    if (as.length >= 5)
                        Users.addUser(DB.connect(), as[1], Util.mySQLCompatibleMD5(as[2]), as[3], as[4]);
                    else
                        Users.addUser(DB.connect(), as[1], Util.mySQLCompatibleMD5(as[2]), as[3]);

                    s = "Server> User " + as[1] + " successfully added\n";
                }
                catch (Exception ex)
                {
                    s = "Server> Error adding user " + as[1] + '\n';
                }
                
                u.getUt().writeMsg(s);
 
                return true;
            }
		}
        
        return false;
    }
}
