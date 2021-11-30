package versionMultithreading;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class App extends Thread {
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
        Population.populateTableAccounts(connection,1,(number*100000)/4,number,stmt);
        Population.populateTableAccounts(connection,(number*100000/4)+1,number*100000/2,number,stmt);
        Population.populateTableAccounts(connection,(number*100000/2)+1,number*100000*3/4,number,stmt);
        Population.populateTableAccounts(connection,number*100000*3/4+1,number*100000,number,stmt);
        Population.populateTableTellers(connection,number,stmt);
        } catch (SQLException e) {
            //Print out any exception while trying to prepare statement
            e.printStackTrace();
        } finally {
            //After catching the statement, close connection if connection is established
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // If connection is established, close connection after query
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        long ende= System.currentTimeMillis();
        System.out.println("Dauer der Anwendung  "+(ende-start));
    }
}