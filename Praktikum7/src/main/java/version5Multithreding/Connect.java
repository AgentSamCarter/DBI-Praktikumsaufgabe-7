package version5Multithreding;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect
{

    public static HikariDataSource connectToDatabase() {

        //Initialise hikari instance
        HikariDataSource hikari = new HikariDataSource();

        //Setting Hikari properties
        hikari.setMaximumPoolSize(10);
        hikari.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
        hikari.addDataSourceProperty("serverName", "192.168.178.103");
        hikari.addDataSourceProperty("port", "3306");
        hikari.addDataSourceProperty("databaseName", "dbi");
        hikari.addDataSourceProperty("user", "sam");
        hikari.addDataSourceProperty("password", "password");
        hikari.setAutoCommit(false);
        hikari.addDataSourceProperty("rewriteBatchedStatements",true);
        return hikari;
    }


    public static Connection dbConnection() throws SQLException
            // n = 10 : 18 sek
            // n = 20 : 38 sek
            // n = 50 : 92 sek
    {   //Wird verwendet um eine Verbindung zu einer MySQL-Datenbank herzustellen
        //Datenbank URL, Datenbank user und passwort werden hinterlegt
        Connection conn = null;
        String connectionUrl = "jdbc:mysql://192.168.178.103:3306/DBI?rewriteBatchedStatements=true";
        String connectionUser = "sam";
        String connectionPassword ="password";
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