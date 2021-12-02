package version5Multithreding;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountInsertThread extends Thread{

        int number;  //das ist "n" in der Aufgabestellung
        Connection conn;
        int von;
        int bis;

        // Nutzliche Parameter
        AccountInsertThread(int von, int bis, int number, Connection conn){
            this.number = number;
            this.conn = conn;
            this.von = von;
            this.bis = bis;
        }

        // Die Methode wird gesplitet
        public void run(){
            try {
                TableValue.fillAccountsVonBis(this.von,this.bis,this.number,this.conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
