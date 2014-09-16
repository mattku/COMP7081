/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author a00795612
 */
public class User {

    //Basic properties of a user
    private String userID;
    private String role;
    private String password;
    private UserThread ut;

    /**
     * Constructor userID - unique string identifying each user role - true if
     * user is administrator
     */
    User(String userID, String role, String password, Socket socket, Server server) {
        this.userID = userID;
        this.role = role;
        this.password = Util.mySQLCompatibleMD5(password);
        runUserThread(socket, server);
    }

    private void runUserThread(Socket socket, Server server) {
        ut = new UserThread(socket, server);
        ut.start();
    }

    public void closeUserThread() {
        try {
            ut.sInput.close();
            ut.sOutput.close();
            ut.socket.close();
        } catch (IOException ioE) {
            // not much I can do
        }
    }

    public UserThread getUt() {
        return ut;
    }

    public String getUserID() {
        return userID;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    private void changeID(String newID) {
        this.userID = newID;
    }

    private void changeAdminStatus(String newRole) {
        this.role = newRole;
    }

}
