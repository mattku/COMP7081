package server;

import common.Util;
import server.DB.*;

/**
 *
 * @author aequites
 */
public final class SlashCommand
{
    public static final String ROLE_ADMIN   = "admin";
    public static final String ROLE_MASTER  = "master";
    public static final String ROLE_DEV     = "dev";
    public static final String ROLE_USER    = "user";
    
    
    private SlashCommand() {}


    public static final boolean process(User pUser, String s)
    {
        String[] as;
        String sRole, sNewRole, sTeam;
        boolean bIsAdmin;

        int i, l;
        
        if (pUser == null || s == null)
            return false;
        
        if ((l = s.length()) == 0)
            return false;
        
        for (i=0; i<l; ++i)
        {
            char c = s.charAt(i);
            
            if (c == ' ') continue;
            if (c == '/') break;
            return false;
        }
        
        as = s.trim().split("\\s+");
        s = as[0].toLowerCase();
        
        sRole = pUser.getRole();
        bIsAdmin = sRole.equals(ROLE_ADMIN);
        
        if (bIsAdmin || sRole.equals(ROLE_MASTER))
        {
            if (s.equals("/adduser"))
            {
                l = as.length;
                
                if (l < 4)
                    s = "Server> Usage: /adduser name password role [team] [company]\n";
                else
                try
                {
                    sNewRole = as[3].toLowerCase();
                    
                    if (bIsAdmin && l == 4)
                        Users.addUser(DB.connect(), as[1], Util.mySQLCompatibleMD5(as[2]), sNewRole, "");
                    else
                    {
                        sTeam = as[4].toLowerCase();

                        // Combine team and company as "team company" in one field
                        if (l >= 6)
                            sTeam += ' ' + as[5].toLowerCase();
                        
                        if (bIsAdmin || pUser.getTeam().equals(sTeam))
                            Users.addUser(DB.connect(), as[1], Util.mySQLCompatibleMD5(as[2]), sNewRole, sTeam);
                        else
                            throw new Exception();
                    }

                    s = "Server> User \"" + as[1] + "\" added with role \"" + sNewRole + "\"\n";
                }
                catch (Exception ex)
                {
                    s = "Server> Error adding user \"" + as[1] + "\"\n";
                }
            }
            else if (s.equals("/deluser"))
            {
                if (as.length < 2)
                    s = "Server> Usage: /deluser name\n";
                else
                try
                {
                    if (bIsAdmin || pUser.getTeam().equals(Users.getTeam(DB.connect(), as[1])))
                        Users.removeUser(DB.connect(), as[1]);
                    else
                        throw new Exception();

                    s = "Server> User \"" + as[1] + "\" deleted\n";
                }
                catch (Exception ex)
                {
                    s = "Server> Error deleting user \"" + as[1] + "\"\n";
                }
            }
            else if (s.equals("/setrole"))
            {
                if (as.length < 3)
                    s = "Server> Usage: /setrole name role\n";
                else
                try
                {
                    sNewRole = as[2].toLowerCase();

                    if (bIsAdmin || pUser.getTeam().equals(Users.getTeam(DB.connect(), as[1])))
                        Users.setRole(DB.connect(), as[1],  sNewRole);
                    else
                        throw new Exception();

                    s = "Server> User \"" + as[1] + "\" set to role \"" + sNewRole + "\"\n";
                }
                catch (Exception ex)
                {
                    s = "Server> Error setting role for user \"" + as[1] + "\"\n";
                }
            }
            else
                s = "Server> Valid commands: /adduse /deluser /setrole\n";

            pUser.getUt().writeMsg(s);

            return true;
		}
        
        return false;
    }
}
