package versionMultithreading;

import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPool {
        public static HikariDataSource connectToDatabase() {

            //Initialise hikari instance
            HikariDataSource hikari = new HikariDataSource();

            //Setting Hikari properties
            hikari.setMaximumPoolSize(10);
            hikari.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
            hikari.addDataSourceProperty("serverName", "10.1.143.18");
            hikari.addDataSourceProperty("port", "3306");
            hikari.addDataSourceProperty("databaseName", "dbi");
            hikari.addDataSourceProperty("user", "sam");
            hikari.addDataSourceProperty("password", "password");
            hikari.setAutoCommit(false);
            hikari.addDataSourceProperty("rewriteBatchedStatements",true);
            return hikari;
        }
}