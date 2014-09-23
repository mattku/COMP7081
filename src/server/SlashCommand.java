package server;

import common.Util;
import server.DB.*;

/**
 *
 * @author aequites
 */
public final class SlashCommand
{

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_MASTER = "master";
    public static final String ROLE_DEV = "dev";
    public static final String ROLE_USER = "user";

    private SlashCommand()
    {
    }

    public static final boolean process(User pUser, String s)
    {
        String[] as;
        String sRole, sNewRole, sTeam;
        boolean bIsAdmin;

        int i, l;

        if (pUser == null || s == null)
        {
            return false;
        }

        if ((l = s.length()) == 0)
        {
            return false;
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
            return false;
        }

        as = s.trim().split("\\s+");
        s = as[0].toLowerCase();

        sRole = pUser.getRole().toString();
        bIsAdmin = sRole.equals(ROLE_ADMIN);

        if (bIsAdmin || sRole.equals(ROLE_MASTER))
        {
            switch (s)
            {
                case "/adduser":
                    l = as.length;
                    if (l < 4)
                    {
                        s = "Server> Usage: /adduser name password role [team] [company]\n";
                    } else
                    {
                        try
                        {
                            sNewRole = as[3].toLowerCase();
                            if (l == 4)
                            {
                                AddUserHandler.handle(pUser, as[1], as[2], as[3].toLowerCase(), null, null);
                            } else if (l == 5)
                            {
                                AddUserHandler.handle(pUser, as[1], as[2], as[3].toLowerCase(), as[4].toLowerCase(), null);
                            } else
                            {
                                AddUserHandler.handle(pUser, as[1], as[2], as[3].toLowerCase(), as[4].toLowerCase(), as[5].toLowerCase());
                            }

                            s = "Server> User \"" + as[1] + "\" added with role \"" + sNewRole + "\"\n";
                        } catch (Exception ex)
                        {
                            s = "Server> Error adding user \"" + as[1] + "\"\n";
                        }
                    }
                    break;
                case "/deluser":
                    if (as.length < 2)
                    {
                        s = "Server> Usage: /deluser name\n";
                    } else
                    {
                        try
                        {
                            RemoveUserHandler.handle(pUser, as[1]);
                            s = "Server> User \"" + as[1] + "\" deleted\n";
                        } catch (Exception ex)
                        {
                            s = "Server> Error deleting user \"" + as[1] + "\"\n";
                        }
                    }
                    break;
                case "/setrole":
                    if (as.length < 3)
                    {
                        s = "Server> Usage: /setrole name role\n";
                    } else
                    {
                        try
                        {
                            sNewRole = as[2].toLowerCase();
                            SetRoleHandler.handle(pUser, as[1], as[2]);

                            s = "Server> User \"" + as[1] + "\" set to role \"" + sNewRole + "\"\n";
                        } catch (Exception ex)
                        {
                            s = "Server> Error setting role for user \"" + as[1] + "\"\n";
                        }
                    }
                    break;
                default:
                    s = "Server> Valid commands: /adduser /deluser /setrole\n";
                    break;
            }

            pUser.getUt().writeMsg(s);

            return true;
        }

        return false;
    }
}
