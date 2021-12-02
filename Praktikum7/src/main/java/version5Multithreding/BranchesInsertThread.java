package version5Multithreding;

import java.sql.Connection;
import java.sql.SQLException;

public class BranchesInsertThread extends Thread{

    int number;
    Connection conn;

    BranchesInsertThread(int number, Connection conn){
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
