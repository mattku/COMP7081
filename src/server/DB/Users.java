package server.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import server.Team;

/**
 * Handles queries relevant to a user
 * to the database.
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
    private static final String addUser = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?)";
    private static final String removeUser = "DELETE FROM " + tableName + " WHERE user_id = ?";
    //getters
    private static final String getPassword = "SELECT password FROM " + tableName + " WHERE user_id = ?";
    private static final String getRole = "SELECT role FROM " + tableName + " WHERE user_id = ?";
    private static final String getTeam = "SELECT team FROM " + tableName + " WHERE user_id = ?";
    //setters
    private static final String setRole = "UPDATE " + tableName + " SET role = ? WHERE user_id = ?";
    private static final String setTeam = "UPDATE " + tableName + " SET team = ? WHERE user_id = ?";
    private static final String setCompany = "UPDATE " + tableName + " SET company = ? WHERE user_id = ?";

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
    
    //This won't really work because team is now an object, not a String
    public static String getTeam(Connection conn, String username) throws SQLException
    {
        try (PreparedStatement stmt = conn.prepareStatement(getTeam))
        {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
            {
                throw new SQLException("user does not exist");
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
                throw new SQLException("user does not exist");
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

            if (stmt.executeUpdate() == 0)
                throw new SQLException("user does not exist");
        }
    }

    public static void addUser(Connection conn, String username, String passHash, String role, String team, String company) throws SQLException
    {
        try (PreparedStatement stmt = conn.prepareStatement(addUser))
        {
            stmt.setString(1, username);
            stmt.setString(2, passHash);
            stmt.setString(3, role);
            stmt.setString(4, team);
            stmt.setString(5, company);

            if (stmt.executeUpdate() == 0)
                throw new SQLException("user already exists");
        }
    }

    public static void removeUser(Connection conn, String username) throws SQLException
    {
        try (PreparedStatement stmt = conn.prepareStatement(removeUser))
        {
            stmt.setString(1, username);

            if (stmt.executeUpdate() == 0)
                throw new SQLException("user does not exist");
        }
    }
    
    public static void setTeam(Connection conn, String username, String newTeam) throws SQLException
    {
        try (PreparedStatement stmt = conn.prepareStatement(setTeam))
        {
            stmt.setString(1, newTeam);
            stmt.setString(2, username);
            
            if (stmt.executeUpdate() == 0)
                throw new SQLException("team does not exist");
        }
    }

    public static void setCompany(Connection conn, String username, String newCompany) throws SQLException
    {
        try (PreparedStatement stmt = conn.prepareStatement(setCompany))
        {
            stmt.setString(1, newCompany);
            stmt.setString(2, username);
            
            if (stmt.executeUpdate() == 0)
                throw new SQLException("company does not exist");
        }
    }
}
