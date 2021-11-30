package versionConnectionPooling;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionPool {
        public static HikariDataSource connectToDatabase() {

            //Initialise hikari instance
            HikariDataSource hikari = new HikariDataSource();

            //Setting Hikari properties
            hikari.setMaximumPoolSize(10);
            hikari.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
            hikari.addDataSourceProperty("serverName", "192.168.122.88");
            hikari.addDataSourceProperty("port", "3306");
            hikari.addDataSourceProperty("databaseName", "dbi");
            hikari.addDataSourceProperty("user", "dbi");
            hikari.addDataSourceProperty("password", "dbi");
            hikari.setAutoCommit(false);
            hikari.addDataSourceProperty("rewriteBatchedStatements",true);
            return hikari;
        }
}