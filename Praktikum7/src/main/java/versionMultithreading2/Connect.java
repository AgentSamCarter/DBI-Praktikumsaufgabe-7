package versionMultithreading2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect
{
    public static Connection dbConnection() throws SQLException
            // n = 10 : 18 sek
            // n = 20 : 38 sek
            // n = 50 : 92 sek
    {   //Wird verwendet um eine Verbindung zu einer MySQL-Datenbank herzustellen
        //Datenbank URL, Datenbank user und passwort werden hinterlegt
        Connection conn = null;
        String connectionUrl = "jdbc:mysql://192.168.232.130:3306/DBI?rewriteBatchedStatements=true";
        String connectionUser = "dbi";
        String connectionPassword ="dbi";
        //URL, USER und PASSWORD werden übergeben
        try
        {
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            conn.setAutoCommit(false);
        }
        catch (Exception e)
        {
            System.err.println("Konnte keine Verbindung herstellen!");
            System.exit(0);
        }
        System.out.println("Datenbankverbindung hergestellt!");
        return conn;
    }

    public static void dbDisconnect(Connection conn) throws SQLException
    {
        conn.close();
    }
}