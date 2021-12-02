import java.sql.*;
public class Version02_Sihem
{
    // LENEVO Sihem: n = 1 --> 331067 ms
    public static void main(String[] args) throws SQLException
    {
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.122.88:3306","sam","password");
        long start = System.currentTimeMillis();
        datenbank(1,conn);
        long ende= System.currentTimeMillis();
        System.out.println("Dauer der Anwendung  "+(ende-start));
    }

    static void datenbank (int n, Connection conn)
    {
        String name= "AAAAAAAAAAAAAAAAAAAA";
        String adress68 = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        String adress72 = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        int balance = 0;
        int branchidBranches, accid, telleridTeller, branchidAccount, BranchidTeller = 0;

        try {
            PreparedStatement queryStatement;
            //Branches
            queryStatement = conn.prepareStatement("INSERT INTO dbi.branches VALUES (?, ?, ?, ?);");

            for ( int i = 1; i <= n ; i++)
            {
                branchidBranches=i;

                queryStatement.setInt(1, branchidBranches);
                queryStatement.setString(2, name);
                queryStatement.setInt(3, balance);
                queryStatement.setString(4, adress72);
                queryStatement.executeUpdate();
             }

            //Account
            queryStatement = conn.prepareStatement("INSERT INTO dbi.accounts VALUES (?, ?, ?, ?,?);");

            for ( int i = 1; i <= (n * 100000); i++)
            {
                accid = i;
                branchidAccount=(int) (Math.random() * n + 1);

                queryStatement.setInt(1, accid);
                queryStatement.setString(2, name);
                queryStatement.setInt(3, balance);
                queryStatement.setInt(4, branchidAccount);
                queryStatement.setString(5, adress68);
                queryStatement.executeUpdate();
            }

            //Teller
            queryStatement = conn.prepareStatement("INSERT INTO dbi.tellers VALUES (?, ?, ?, ?,?);");

            for ( int i = 1; i <= (n * 10); i++)
            {
                telleridTeller=i;
                branchidAccount=(int) (Math.random() * n + 1);

                queryStatement.setInt(1, telleridTeller);
                queryStatement.setString(2, name);
                queryStatement.setInt(3, balance);
                queryStatement.setInt(4, branchidAccount);
                queryStatement.setString(5, adress68);
                queryStatement.executeUpdate();
            }
            // History
            //0 Tupel
            queryStatement.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
           }
    }
}
