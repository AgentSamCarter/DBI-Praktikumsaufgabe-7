package versionMultithreading;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Population {

    public static void populateTableBranches(Connection connection, int number,PreparedStatement stmt) throws SQLException {

        String name= "AAAAAAAAAAAAAAAAAAAA";
        String adress72 = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        int balance = 0;
        int branchidBranches = 0;

        //Preparing statement - INSERT INTO...
        String update = "INSERT INTO branches VALUES (?, ?, ?, ?)";

        stmt = connection.prepareStatement(update);

        for ( int i = 1; i <= number ; i++)
        {
            branchidBranches = i;

            stmt.setInt(1, branchidBranches);
            stmt.setString(2, name);
            stmt.setInt(3, balance);
            stmt.setString(4, adress72);
            stmt.addBatch();
        }
        stmt.executeBatch();
        connection.commit();
    };

    public static void populateTableAccounts(Connection connection, int von, int bis, int number, PreparedStatement stmt) throws SQLException {

        String name= "AAAAAAAAAAAAAAAAAAAA";
        String adress68 = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        int balance = 0;
        int branchidAccount = 0;
        int accid = 0;

        //Preparing statement - INSERT INTO...
        String update = "INSERT INTO Accounts VALUES (?, ?, ?, ?,?)";
        stmt = connection.prepareStatement(update);

        for ( int i = 1; i <= (number * 100000); i++)
        {
            accid = i;
            branchidAccount=(int) (Math.random() * number + 1);

            stmt.setInt(1, accid);
            stmt.setString(2, name);
            stmt.setInt(3, balance);
            stmt.setInt(4, branchidAccount);
            stmt.setString(5, adress68);
            stmt.addBatch();

            if(i % 4000 == 0) {
                stmt.executeBatch();
                connection.commit();
            }
        }
        stmt.executeBatch();
        connection.commit();
    };

    public static void populateTableTellers(Connection connection, int number, PreparedStatement stmt) throws SQLException {
        String name= "AAAAAAAAAAAAAAAAAAAA";
        String adress68 = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        int balance = 0;
        int branchidAccount = 0;
        int accid = 0;

        String update = "INSERT INTO tellers VALUES (?, ?, ?, ?,?)";
        stmt = connection.prepareStatement(update);

        for ( int i = 1; i <= (number * 10); i++)
        {
            int telleridTeller = i;
            branchidAccount=(int) (Math.random() * number + 1);

            stmt.setInt(1, telleridTeller);
            stmt.setString(2, name);
            stmt.setInt(3, balance);
            stmt.setInt(4, branchidAccount);
            stmt.setString(5, adress68);
            stmt.addBatch();
        }
        stmt.executeBatch();
        connection.commit();
    };
}
