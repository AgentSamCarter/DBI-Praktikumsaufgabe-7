package version5Multithreding;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main
{
    static Scanner scan = new Scanner(System.in);

    static void deleteTables(Connection conn) throws SQLException
    {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("Drop TABLE IF EXISTS history");
        stmt.executeUpdate("DROP TABLE IF EXISTS accounts");
        stmt.executeUpdate("DROP TABLE IF EXISTS tellers");
        stmt.executeUpdate("DROP TABLE IF EXISTS branches");
        System.out.println("Alle Tabellen wurden geloescht");
    }

    public static void main(String[] args) throws Exception
    {
        //Aufbau der Verbindung
        HikariDataSource hikari =  Connect.connectToDatabase();
        Connection conn1 = hikari.getConnection();
        Connection conn2 = hikari.getConnection();
        Connection conn3 = hikari.getConnection();
        Connection conn4 = hikari.getConnection();
        Connection conn5 = hikari.getConnection();

        //Loeschen der alten Tabellen branches, accounts, tellers und history
        deleteTables(conn1);

        //Tabellen werden erstellt
        Tables.createTables(conn1);
        //Eingabe n
        System.out.println("Geben Sie n ein: ");
        int n = scan.nextInt();
        BranchesInsertThread t1 = new BranchesInsertThread(n,conn1);
        ThreadTwo t2 = new ThreadTwo(1,n * 25000,n,conn2);
        ThreadTwo t3 = new ThreadTwo((n * 25000)+1,n * 50000,n,conn3);
        ThreadTwo t4 = new ThreadTwo((n * 50000)+1,n * 75000,n,conn4);
        ThreadTwo t5 = new ThreadTwo((n * 75000)+1,n * 100000,n,conn5);
        //Beginn Zeitmessung
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.start();
        t5.join();
        long ende = System.currentTimeMillis();
        System.out.println(ende - start);
        conn1.close();
        conn2.close();
        conn3.close();
        conn4.close();
        conn5.close();
    }
}