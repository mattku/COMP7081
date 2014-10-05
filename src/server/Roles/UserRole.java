/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.Roles;

/**
 *
 * @author Brian
 */
public class UserRole implements Role
{
    @Override
    public boolean canAddUser(String team)
    {
        return false;
    }

    @Override
    public boolean canRemoveUser(String team)
    {
        return false;
    }

    @Override
    public boolean canChangeRole(String team, String newRole)
    {
        return false;
    }
    
    @Override
    public boolean canTeamChat(String team)
    {
        return false;
    }

    @Override
    public boolean canAllChat()
    {
        return true;
    }
    
    @Override
    public String toString()
    {
        return USER;
    }

    @Override
    public int compareTo(String o)
    {
        switch(o)
        {
            case ANONYMOUS:
                return 1;
            case USER:
                return 0;
            case DEVELOPER:
                return -1;
            case SCRUM_MASTER:
                return -1;
            case ADMINISTRATOR:
                return -1;
            default:
                return -1;
        }
    }

    @Override
    public int getEnum()
    {
        return Role.E_USER;
    }

}
