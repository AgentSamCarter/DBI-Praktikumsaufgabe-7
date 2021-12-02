package version5Multithreding;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.util.Scanner;

public class Main
{
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception
    {
        //Aufbau der Verbindung
        HikariDataSource hikari =  Connect.connectToDatabase();
        Connection conn1 = hikari.getConnection();
        Connection conn2 = hikari.getConnection();
        Connection conn3 = hikari.getConnection();
        Connection conn4 = hikari.getConnection();
        Connection conn5 = hikari.getConnection();

        //Eingabe n
        System.out.println("Geben Sie n ein: ");
        int n = scan.nextInt();

        BranchesTellersInsertThread t1 = new BranchesTellersInsertThread(n,conn1);

        AccountInsertThread t2 = new AccountInsertThread(1,n * 25000,n,conn2);
        AccountInsertThread t3 = new AccountInsertThread((n * 25000)+1,n * 50000,n,conn3);
        AccountInsertThread t4 = new AccountInsertThread((n * 50000)+1,n * 75000,n,conn4);
        AccountInsertThread t5 = new AccountInsertThread((n * 75000)+1,n * 100000,n,conn5);

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