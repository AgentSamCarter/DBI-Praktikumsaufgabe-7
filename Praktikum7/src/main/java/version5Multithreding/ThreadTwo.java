package version5Multithreding;

import java.sql.Connection;
import java.sql.SQLException;

public class ThreadTwo extends Thread{

        int number;
        Connection conn;
        int von;
        int bis;

        ThreadTwo (int von, int bis,int number, Connection conn){
            this.number = number;
            this.conn = conn;
            this.von = von;
            this.bis = bis;
        }

        public void run(){
            try {
                TableValue.fillAccountsVonBis(this.von,this.bis,this.number,this.conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
