import com.mysql.cj.xdevapi.Result;

import java.sql.*;

public class test
{
    public static void main(String[] args) throws SQLException
    {
        long start = System.currentTimeMillis();
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:10","dbineu","123");

        Statement st = conn.createStatement();

        datenbank(10,conn);


        long ende= System.currentTimeMillis();

        System.out.println("Dauer der Anwendung  "+(ende-start));

    }

    static void datenbank (int i, Connection conn)
    {
        for ( i = 1; i <=1000 ; i++)
        {
            int test =i;

            try {
                PreparedStatement queryStatement = conn.prepareStatement("INSERT INTO dbi.branches VALUES ("+test+", 'Anne', 1000, 'abcdefghijklmnopqrstuvwxyz');");

                queryStatement.executeUpdate(); //Executes the query
                queryStatement.close();


            } catch (SQLException e) {
                e.printStackTrace();
            }



     }
    }
}