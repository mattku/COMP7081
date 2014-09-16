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
    private static final String passwordCheck = "SELECT password FROM " + tableName + " WHERE user_id = ?";
    private static final String roleCheck = "SELECT role FROM " + tableName + "WHERE user_id = ?";
    
    public static PwdResult checkPassword(Connection conn, String username, String passHash) throws SQLException
    {
        try (PreparedStatement stmt = conn.prepareStatement(passwordCheck))
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
    
    public static String getRole(Connection conn, String username) throws SQLException
    {
        try (PreparedStatement stmt = conn.prepareStatement(roleCheck))
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
}
