package versionConnectionPooling;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {

        HikariDataSource hikari =  ConnectionPool.connectToDatabase();
        Connection connection = hikari.getConnection();
        PreparedStatement stmt = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Number of n: ");
        int number = sc.nextInt();
        long start = System.currentTimeMillis();
        try {
        Population.populateTableBranches(connection,number,stmt);
        Population.populateTableAccounts(connection,number,stmt);
        Population.populateTableTellers(connection,number,stmt);
        } catch (SQLException e) {
            //Print out any exception while trying to prepare statement
            e.printStackTrace();
        } finally {
            //After catching the statement, close connection if connection is established
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // If connection is established, close connection after query
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        long ende= System.currentTimeMillis();

        System.out.println("Dauer der Anwendung  "+(ende-start));
    }
}
