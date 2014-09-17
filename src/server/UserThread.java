/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import common.*;

/**
 *
 * @author a00795612
 */
public class UserThread extends Thread {

    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput;
    // my unique id (easier for deconnection)
    private int id;
    // the date I connect
    private String date;
    //Server
    private Server server;
    
    private User user;

    // Constructore
    UserThread(User user, ObjectInputStream sInput, ObjectOutputStream sOutput, Server server) {

        this.user = user;
        this.server = server;
        this.sOutput = sOutput;
        this.sInput = sInput;
        date = new Date().toString() + "\n";
    }

    // what will run forever
    public void run() {
        // to loop until LOGOUT
        boolean keepGoing = true;
        while (keepGoing) {
            ChatMessage cm;
            try {
                cm = (ChatMessage) sInput.readObject();
            } catch (IOException e) {
                break;
            } catch (ClassNotFoundException e2) {
                break;
            }
            // the messaage part of the ChatMessage
            String message = cm.getMessage();

            if (cm instanceof AddUserMessage) {
                
            } else {
                // Switch on the type of message receive
                switch (cm.getType()) {
                    case ChatMessage.MESSAGE:
                        server.broadcast(user.getUserID() + ": " + message);
                        break;
                    case ChatMessage.LOGOUT:
                        server.display(user.getUserID() + " disconnected with a LOGOUT message.");
                        keepGoing = false;
                        break;
//                case ChatMessage.WHOISIN:
//                    server.writeMsg("List of the users connected at " + sdf.format(new Date()) + "\n");
                    // scan al the users connected
//                    for (int i = 0; i < al.size(); ++i) {
//                        Server.ClientThread ct = al.get(i);
//                        writeMsg((i + 1) + ") " + ct.username + " since " + ct.date);
//                    }
//                    break;
                }
            }

        }
        // remove myself from the arrayList containing the list of the
        // connected Clients
        server.remove(user);
        close();
    }

    // try to close everything
    public void close() {
        // try to close the connection
        try {
            if (sOutput != null) {
                sOutput.close();
            }
        } catch (Exception e) {
        }
        try {
            if (sInput != null) {
                sInput.close();
            }
        } catch (Exception e) {
        };
    }

    /*
     * Write a String to the Client output stream
     */
    public boolean writeMsg(String msg) {
        // write the message to the stream
        try {
            sOutput.writeObject(msg);
        } // if an error occurs, do not abort just inform the user
        catch (IOException e) {
            server.display("Error sending message to " + user.getUserID());
            server.display(e.toString());
        }
        return true;
    }

    public int getUId()
    {
        return id;
    }

}
