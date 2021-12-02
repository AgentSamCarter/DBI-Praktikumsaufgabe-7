package version5Multithreding;

import java.sql.Connection;
import java.sql.SQLException;

public class BranchesTellersInsertThread extends Thread{

    int number; // n aus der Aufgabestellung
    Connection conn;

    BranchesTellersInsertThread(int number, Connection conn){
        this.number = number;
        this.conn = conn;
    }

    public void run(){
        try {
            TableValue.fillBranches(number,conn);
            TableValue.fillTellers(number,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
