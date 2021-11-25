import com.mysql.cj.xdevapi.Result;

import java.sql.*;
import java.util.Scanner;

public class test
{
    public static void main(String[] args) throws SQLException
    {
           Scanner sc =new Scanner(System.in);
        System.out.println("Wie groß ist n?");
           int n =sc.nextInt();
        Connection conn = create_Connection.createConnection();
        drop_Table.drop(conn);


       create_Table.create(conn);


        long start = System.currentTimeMillis();
       // fill_Database.datenbank(n,conn);
        loadDataLocalInfileMySQL.load_Data(conn,n);

        long ende= System.currentTimeMillis();
        //  delete_Database.delete(conn);

        System.out.println("Dauer der Anwendung  "+(ende-start));

        conn.close();
        sc.close();

    }


        }







