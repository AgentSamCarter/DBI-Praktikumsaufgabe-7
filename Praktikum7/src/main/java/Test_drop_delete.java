import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test_drop_delete {
    public static void main(String[] args) throws SQLException
    {
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:10/dbi","sam","password");
        long start = System.currentTimeMillis();
        delete(conn);
        long ende= System.currentTimeMillis();
        System.out.println("Dauer der Anwendung  "+(ende-start));
    }

    static void delete (Connection conn)
    {
        try {

            int branchid=0;
            String branchname = "AnneAnneAnneAnneAnne";
            int balance = 0;
            String adress = "AnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnne";

            PreparedStatement queryStatement = conn.prepareStatement("DELETE FROM accounts WHERE 1=1;");
            queryStatement.executeUpdate();
            queryStatement = conn.prepareStatement("DELETE FROM tellers WHERE 1=1;");
            queryStatement.executeUpdate();
            queryStatement = conn.prepareStatement("DELETE FROM Branches WHERE 1=1;");
            queryStatement.executeUpdate();
            queryStatement.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    static void dropAndCreate (Connection conn)
    {
        try {

            int branchid=0;
            String branchname = "AnneAnneAnneAnneAnne";
            int balance = 0;
            String adress = "AnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnne";

            PreparedStatement queryStatement = conn.prepareStatement("DROP TABLE accounts;");
            queryStatement.executeUpdate();
            queryStatement = conn.prepareStatement("DROP TABLE tellers;");
            queryStatement.executeUpdate();
            queryStatement = conn.prepareStatement("DROP TABLE Branches;");
            queryStatement.executeUpdate();
            queryStatement.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
