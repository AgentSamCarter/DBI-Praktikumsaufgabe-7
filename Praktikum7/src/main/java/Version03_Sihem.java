import java.sql.*;
public class Version03_Sihem
{
    // LENEVO Sihem Batch 1000 ohne rewriteBatchedStatements: n = 1 --> 524820 ms  / n = 50 ==> geschätz 6 Stunden
    // LENEVO Sihem Batch 1000 mit rewriteBatchedStatements: n = 1 -->  8254 ms    / n = 50 ==> 668481 ms Kommentar: der Delete von n = 50 hat 446620 gedauert
    // LENEVO Sihem Batch 2000 mit rewriteBatchedStatements: n = 1 -->  5632  ms   / n = 50 ==> 307932 ms Kommentar: used drop and create as sql-script
    // LENEVO Sihem Batch 4000 mit rewriteBatchedStatements: n = 1 -->  4711 ms    / n = 50 ==> 293492 ms Kommentar: used drop and create as sql-script
    // LENEVO Sihem Batch 10000 mit rewriteBatchedStatements: n = 1 -->  4646 ms   / n = 50 ==> 300191/313047/297213(closed all other apps) ms Kommentar: used drop and create as sql-script
    // LENEVO Sihem Batch 4000 mit rewriteBatchedStatements and commit: n = 1 --> 4861   ms   / n = 50 ==> 288519 (closed all other apps) ms Kommentar: used drop and create as sql-script
    public static void main(String[] args) throws SQLException
    {
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.178.103:3306/dbi?rewriteBatchedStatements=true","sam","password");
        conn.setAutoCommit(false);
        long start = System.currentTimeMillis();
        datenbank(50,conn);
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
            queryStatement = conn.prepareStatement("INSERT INTO branches VALUES (?, ?, ?, ?)");

            for ( int i = 1; i <= n ; i++)
            {
                branchidBranches=i;

                queryStatement.setInt(1, branchidBranches);
                queryStatement.setString(2, name);
                queryStatement.setInt(3, balance);
                queryStatement.setString(4, adress72);
                queryStatement.addBatch();
            }
            queryStatement.executeBatch();
            conn.commit();
            //Account
            queryStatement = conn.prepareStatement("INSERT INTO accounts VALUES (?, ?, ?, ?,?)");

            for ( int i = 1; i <= (n * 100000); i++)
            {
                accid = i;
                branchidAccount=(int) (Math.random() * n + 1);

                queryStatement.setInt(1, accid);
                queryStatement.setString(2, name);
                queryStatement.setInt(3, balance);
                queryStatement.setInt(4, branchidAccount);
                queryStatement.setString(5, adress68);
                queryStatement.addBatch();

                if(i % 1000 == 0) {
                    queryStatement.executeBatch();
                    conn.commit();
                }
            }
            queryStatement.executeBatch();
            conn.setAutoCommit(true);
            //Teller
            queryStatement = conn.prepareStatement("INSERT INTO tellers VALUES (?, ?, ?, ?,?)");

            for ( int i = 1; i <= (n * 10); i++)
            {
                telleridTeller=i;
                branchidAccount=(int) (Math.random() * n + 1);

                queryStatement.setInt(1, telleridTeller);
                queryStatement.setString(2, name);
                queryStatement.setInt(3, balance);
                queryStatement.setInt(4, branchidAccount);
                queryStatement.setString(5, adress68);
                queryStatement.addBatch();
            }
            queryStatement.executeBatch();
            // History
            //0 Tupel

            queryStatement.close();
            conn.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}