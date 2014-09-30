package server;

import server.Roles.RoleFactory;
import server.Roles.Role;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The User object holds all relevant
 * data and threads for a single user.
 * 
 * @author Matthew Ku
 */
public class User {

    //Basic properties of a user
    private String userID;
    private String password;
    private Role role;
    private String team;
    private UserThread ut;

    /**
     * Constructor userID - unique string identifying each user role - true if
     * user is administrator
     */
    User(String userID, String passHash, String role, String team, ObjectInputStream sInput,ObjectOutputStream sOutput, Server server) {
        this.userID = userID;
        this.password = passHash;
        this.role = RoleFactory.createRole(role, this);
        this.team = team;
        server.teamBroadcast(team, userID + " has connected " + " as " + role);
        runUserThread(sInput, sOutput, server);
    }

    private void runUserThread(ObjectInputStream sInput,ObjectOutputStream sOutput, Server server) {
        ut = new UserThread(this, sInput, sOutput, server);
        ut.start();
    }

    public void closeUserThread() {
            ut.close();
    }

    public UserThread getUt() {
        return ut;
    }

    public String getUserID() {
        return userID;
    }

    public Role getRole() {
        return role;
    }

    public String getTeam() {
        return team;
    }

    public String getPassword() {
        return password;
    }

    private void changeID(String newID) {
        this.userID = newID;
    }

    private void changeRole(Role newRole) {
        this.role = newRole;
    }

}
