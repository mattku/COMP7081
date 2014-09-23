/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

/**
 *
 * @author Brian
 */
public class ScrumMasterRole implements Role
{
    User user;
    public ScrumMasterRole(User u)
    {
        user = u;
    }
    
    @Override
    public boolean canAddUser(String team)
    {
        return user.getTeam().equals(team);
    }

    @Override
    public boolean canRemoveUser(String team)
    {
        return user.getTeam().equals(team);
    }

    @Override
    public boolean canChangeRole(String team, String newRole)
    {
        return compareTo(newRole) > 0 && 
               user.getTeam().equals(team);
    }
    
    @Override
    public String toString()
    {
        return SCRUM_MASTER;
    }

    @Override
    public int compareTo(String o)
    {
        switch(o)
        {
            case USER:
                return 1;
            case DEVELOPER:
                return 1;
            case SCRUM_MASTER:
                return 0;
            case ADMINISTRATOR:
                return -1;
            default:
                return -1;
        }
    }
}
