package server.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DB class handles connecting
 * to the database.
 *
 * @author Brian
 */
public class DB
{
    private static final String dbURL = "jdbc:mysql://localhost:3306/chatproject";
    private static final String dbUser = "root";
    private static final String dbPwd = "";
        
    private DB()
    {}
    
    public static Connection connect() throws SQLException
    {
        return DriverManager.getConnection(dbURL, dbUser, dbPwd);
    }
    
    public static void printSQLException(SQLException ex) 
    {

        for (Throwable e : ex) 
        {
            if (e instanceof SQLException) 
            {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " +
                    ((SQLException)e).getSQLState());

                System.err.println("Error Code: " +
                    ((SQLException)e).getErrorCode());

                System.err.println("Message: " + e.getMessage());

                Throwable t = ex.getCause();
                while(t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
