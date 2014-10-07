package server;

import server.Roles.Role;

/**
 *
 * @author aequites
 */
public final class SlashCommand
{
    public static final int E_IGNORED   = 0;
    public static final int E_CONSUMED  = 1;


    private SlashCommand()
    {
    }

    public static final int process(User pUser, String sMsg)
    {
        String[] as;
        String s = sMsg;
        int eRole;

        int i, l;

        if (pUser == null || s == null || (l = s.length()) == 0)
        {
            return E_CONSUMED;
        }

        for (i = 0; i < l; ++i)
        {
            char c = s.charAt(i);

            if (c == ' ')
            {
                continue;
            }

            if (c == '/')
            {
                break;
            }

            return E_IGNORED; // not a command
        }

        eRole = pUser.getRoleEnum();

        // Do nothing and consume the command if the user has no management or
        // team chat rights so the server doesn't have to check every message.
        if (eRole < Role.E_DEV)
            return E_CONSUMED;
        
        as = s.trim().split("\\s+");
        s = as[0];

        if (s.length() == 0)
            return E_CONSUMED;

        l = as.length;
        if (l > 1 && s.equals("/t"))
        {
            // team chat: return the string's char index after /t plus a space
            return sMsg.indexOf("/t") + 3;
        }

        // Only admins and scrum masters are allowed beyond this point
        if (eRole < Role.E_MASTER)
            return E_CONSUMED;

        switch (s)
        {
            case "/adduser":
                if (l < 4)
                {
                    s = "Server> Usage: /adduser name password role [team] [company]\n";
                }
                else
                {
                    try
                    {
                        String sNewRole = as[3].toLowerCase();

                        if (as[2].equals("null")) as[2] = "";
                        
                        if (l == 4)
                        {
                            AddUserHandler.handle(pUser, as[1], as[2], sNewRole, null, null);
                        }
                        else if (l == 5)
                        {
                            as[4] = as[4].equals("null") ? null : as[4].toLowerCase();
                            
                            AddUserHandler.handle(pUser, as[1], as[2], sNewRole, as[4], null);
                        }
                        else
                        {
                            as[4] = as[4].equals("null") ? null : as[4].toLowerCase();

                            AddUserHandler.handle(pUser, as[1], as[2], sNewRole, as[4], as[5].toLowerCase());
                        }

                        s = "Server> User \"" + as[1] + "\" added with role \"" + sNewRole + "\"\n";
                    }
                    catch (Exception ex)
                    {
                        s = "Server> Error adding user \"" + as[1] + "\"\n";
                    }
                }
                break;

            case "/deluser":
                if (l < 2)
                {
                    s = "Server> Usage: /deluser name\n";
                }
                else
                {
                    try
                    {
                        RemoveUserHandler.handle(pUser, as[1]);
                        s = "Server> User \"" + as[1] + "\" deleted\n";
                    }
                    catch (Exception ex)
                    {
                        s = "Server> Error deleting user \"" + as[1] + "\"\n";
                    }
                }
                break;

            case "/setrole":
                if (l < 3)
                {
                    s = "Server> Usage: /setrole name role\n";
                }
                else
                {
                    try
                    {
                        String sNewRole = as[2].toLowerCase();
                        SetRoleHandler.handle(pUser, as[1], sNewRole);

                        s = "Server> User \"" + as[1] + "\" set to role \"" + sNewRole + "\"\n";
                    }
                    catch (Exception ex)
                    {
                        s = "Server> Error setting role for user \"" + as[1] + "\"\n";
                    }
                }
                break;
            case "/setteam":
                if(l < 2)
                {
                    s = "Server> Usage: /setteam name team\n";
                    break;
                }
                try
                {
                    String sNewTeam = (l > 2) ? as[2].toLowerCase() : "";
                    
                    SetTeamHandler.handle(pUser, as[1], sNewTeam);
                    s = "Server> User \"" + as[1] + "\" set to team \"" + sNewTeam + "\"\n";
                }
                catch (Exception ex)
                {
                    s = "Server> Error setting team for user \"" + as[1] + "\"\n";
                }
                break;
            case "/setcompany":
                if(l < 2)
                {
                    s = "Server> Usage: /setcompany name company\n";
                    break;
                }
                try
                {
                    String sNewCompany = (l > 2) ? as[2].toLowerCase() : "";

                    SetCompanyHandler.handle(pUser, as[1], sNewCompany);
                    s = "Server> User \"" + as[1] + "\" set to company \"" + sNewCompany + "\"\n";
                }
                catch (Exception ex)
                {
                    s = "Server> Error setting company for user \"" + as[1] + "\"\n";
                }
                break;
            default:
                s = "Server> Valid commands: /adduser /deluser /setrole /setteam /setcompany /t\n";
        }

        pUser.sendMessage(s);

        return E_CONSUMED;
    }
}
