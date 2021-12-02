package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookingDB {

    public Connection connect() throws BookingDbException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            DriverManager.setLoginTimeout(60);
            String url = "jdbc:" +
                    "postgresql" +       // “mysql” / “db2” / “mssql” / “oracle” / ...
                    "://" +
                    "localhost" +
                    ":" +
                    "5432" +
                    "/" +
                    "booking" +
                    "?user=" +
                    "postgres" +
                    "&password=" +
                    "admin";
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            throw new BookingDbException("Could not load from DB.");
        }
    }


}