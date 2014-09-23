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
public class AdminRole implements Role
{

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
    public String toString()
    {
        return ADMINISTRATOR;
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
                return 1;
            case ADMINISTRATOR:
                return 0;
            default:
                return -1;
        }
    }
}
