import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FillTableStatement {

        public static void main(String[] args) throws SQLException
        {
            long start = System.currentTimeMillis();
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:10","sam","password");
            datenbank(50,conn);
            long ende= System.currentTimeMillis();
            System.out.println("Dauer der Anwendung  "+(ende-start));
        }

        static void datenbank (int n, Connection conn)
        {
            try {
                for ( int i = 1; i <=n ; i++)
                {
                    int test = i;
                    PreparedStatement queryStatement = conn.prepareStatement("INSERT INTO dbi.branches VALUES ("+test+", 'AnneAnneAnneAnneAnne', 0, 'AnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnneAnne');");
                    test++;
                    queryStatement.executeUpdate();
                    queryStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
}