/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.sql.Connection;
import server.DB.DB;
import server.DB.Users;

/**
 *
 * @author Brian
 */
public class SetTeamHandler
{

    static void handle(User pUser, String username, String newTeamName) throws Exception
    {
        if(pUser.getRole().canSetTeam(username, newTeamName))
        {
            //change the database
            try (Connection conn = DB.connect()) 
            {
                Users.setTeam(conn, username, newTeamName);
            }
            Server s = pUser.getServer();
            //if the user is online
            User oldUser = s.getUser(username);
            if(oldUser == null)
            {
                return;
            }
            //the team should always exist
            Team oldTeam = s.getTeam(oldUser.getTeam());
            if(oldTeam == null)
            {
                throw new Error("User online but their team isn't.");
            }
            //remove user from their old team
            oldTeam.removeUser(oldUser);
            if(oldTeam.getTeamMembers().isEmpty())
            {
                s.removeTeam(oldTeam);
            }
            //add them to the new team
            oldUser.setTeam(newTeamName);
            Team newTeam = s.getTeam(newTeamName);
            if(newTeam == null)
            {
                newTeam = new Team(newTeamName);
                s.addTeam(newTeamName, newTeam);
            }
            newTeam.addUser((oldUser));
        }
        else throw new Exception("Insufficient permission");
    }
    
}
