package server;

import java.util.ArrayList;

/**
 * Team object holds a group of user objects.
 *
 * @author Matt
 */
public class Team {
    
    private ArrayList<User> teamMembers;
    private String teamName;

    public Team(String teamName) {
        this.teamName = teamName;
    }
    
    //Attempts to add a user object to teamMembers
    //Will return true if successful, false if not
    public boolean addUser(User user) {
        return teamMembers.add(user);
    }
    
    //Getters and Setters
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    
}
