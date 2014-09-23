
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Brian
 */
public final class Users
{
    public enum PwdResult
    {
        SUCCESS, INCORRECT_PASSWORD, NO_SUCH_USER
    }
    private static final String tableName = "users";
    private static final String addUser = "INSERT INTO " + tableName + " VALUES(?,?,?,?)";
    private static final String removeUser = "DELETE FROM " + tableName + " WHERE user_id = ?";
    private static final String getPassword = "SELECT password FROM " + tableName + " WHERE user_id = ?";
    private static final String setRole = "UPDATE " + tableName + " SET role = ? WHERE user_id = ?";
    private static final String getRole = "SELECT role FROM " + tableName + " WHERE user_id = ?";
    private static final String getTeam = "SELECT team FROM " + tableName + " WHERE user_id = ?";

    public static PwdResult getPassword(Connection conn, String username, String passHash) throws SQLException
    {
        try (PreparedStatement stmt = conn.prepareStatement(getPassword))
        {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
            {
                return PwdResult.NO_SUCH_USER;
            }
            if(rs.getString(1).equals(passHash))
            {
                return PwdResult.SUCCESS;
            }
        }
        return PwdResult.INCORRECT_PASSWORD;
    }
    
    public static String getTeam(Connection conn, String username) throws SQLException
    {
        try (PreparedStatement stmt = conn.prepareStatement(getTeam))
        {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
            {
                return null;
            }
            return rs.getString(1);
        }    
    }

    public static String getRole(Connection conn, String username) throws SQLException
    {
        try (PreparedStatement stmt = conn.prepareStatement(getRole))
        {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
            {
                return null;
            }
            return rs.getString(1);
        }    
    }
    
    public static void setRole(Connection conn, String username, String role) throws SQLException
    {
        try (PreparedStatement stmt = conn.prepareStatement(setRole))
        {
            stmt.setString(1, role);
            stmt.setString(2, username);
            stmt.execute();
        }
    }

    public static void addUser(Connection conn, String username, String passHash, String role, String team) throws SQLException
    {
        try (PreparedStatement stmt = conn.prepareStatement(addUser))
        {
            stmt.setString(1, username);
            stmt.setString(2, passHash);
            stmt.setString(3, role);
            stmt.setString(4, team);

            stmt.execute();
        }
    }

    public static void removeUser(Connection conn, String username) throws SQLException
    {
        try (PreparedStatement stmt = conn.prepareStatement(removeUser))
        {
            stmt.setString(1, username);
            stmt.execute();
        }
    }
}
