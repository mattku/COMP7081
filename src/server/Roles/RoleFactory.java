/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.Roles;

import server.User;

/**
 *
 * @author Brian
 */
public class RoleFactory
{
    public static Role createRole(String role, User user)
    {
        switch(role)
        {
            case Role.USER:
                return new UserRole();
            case Role.ADMINISTRATOR:
                return new AdminRole();
            case Role.DEVELOPER:
                return new DevRole();
            case Role.SCRUM_MASTER:
                return new ScrumMasterRole(user);
            default:
                return new AnonRole();
        }
    }
}
