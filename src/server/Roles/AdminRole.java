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
public class AdminRole implements Role
{
    @Override
    public boolean canSetTeam(String username, String newTeam)
    {
        return true;
    }

    @Override
    public boolean canSetCompany(String username, String newCompany)
    {
        return true;
    }
    
    @Override
    public boolean canAddUser(String team)
    {
        return true;
    }

    @Override
    public boolean canRemoveUser(String team)
    {
        return true;
    }

    @Override
    public boolean canChangeRole(String team, String newRole)
    {
        return compareTo(newRole) >= 0;
    }
    
    @Override
    public boolean canTeamChat(String team)
    {
        return true;
    }

    @Override
    public boolean canAllChat()
    {
        return true;
    }
    
    @Override
    public String toString()
    {
        return ADMINISTRATOR;
    }

    @Override
    public int compareTo(String o)
    {
        switch(o)
        {
            case ANONYMOUS:
                return 1;
            case USER:
                return 1;
            case DEVELOPER:
                return 1;
            case SCRUM_MASTER:
                return 1;
            case ADMINISTRATOR:
                return 0;
            default:
                return -1;
        }
    }

    @Override
    public int getEnum()
    {
        return Role.E_ADMIN;
    }

}
