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
        this.teamMembers = new ArrayList<>();
    }
    
    //Attempts to add a user object to teamMembers
    //Will return true if successful, false if not
    public boolean addUser(User user) {
        return teamMembers.add(user);
    }
    
    public boolean containsMember(User user) {
        return teamMembers.contains(user);
    }
    
    //Returns a string array of the userIDs of each team member
    public String[] getTeamList() {
        String[] teamList = new String[teamMembers.size()];
        int c = 0;
        for(User u : teamMembers) {
            teamList[c++] = u.getUserID();
        }
        return teamList;
    }
    
    //Getters and Setters
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<User> getTeamMembers() {
        return teamMembers;
    }
}
