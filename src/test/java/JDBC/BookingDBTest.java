package JDBC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BookingDBTest extends BookingDB {




        public Connection connectToPostgreSQL() throws SQLException, BookingDbException {
            try {
                Class.forName("org.postgresql.Driver").newInstance();
                Connection connection = null;
                DriverManager.setLoginTimeout(60);
                String url = new StringBuilder()
                        .append("jdbc:")
                        .append("postgresql")       // “mysql” / “db2” / “mssql” / “oracle” / ...
                        .append("://")
                        .append("localhost")
                        .append(":")
                        .append(5432)
                        .append("/")
                        .append("?user=")
                        .append("postgres")
                        .append("&password=")
                        .append("admin").toString();
                return DriverManager.getConnection(url);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                throw new BookingDbException("Could not load DB driver.");
            }
        }

        @Override
        public Connection connect() throws BookingDbException, SQLException {
            try {
                Class.forName("org.postgresql.Driver").newInstance();
                Connection connection = null;
                DriverManager.setLoginTimeout(60);
                String url = new StringBuilder()
                        .append("jdbc:")
                        .append("postgresql")       // “mysql” / “db2” / “mssql” / “oracle” / ...
                        .append("://")
                        .append("localhost")
                        .append(":")
                        .append(5432)
                        .append("/")
                        .append("booking")
                        .append("?user=")
                        .append("postgres")
                        .append("&password=")
                        .append("admin").toString();
                return DriverManager.getConnection(url);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                throw new BookingDbException("Could not load DB driver.");
            }
        }

        public static void setUpTestDB() throws BookingDbException, SQLException {
            BookingDBTest tdb = new BookingDBTest();
            try (Connection connection = tdb.connectToPostgreSQL()) {
                Statement statement = connection.createStatement();
                statement.execute("CREATE DATABASE booking;");
            }

            // connect to newly created tests database and create tables.
            try (Connection connection = tdb.connect()) {
                StringBuilder builder = new StringBuilder();
                builder.append("CREATE SEQUENCE accomodation_ids;");
                builder.append("CREATE TABLE accomodation(id INT PRIMARY KEY DEFAULT NEXTVAL('accomodation_ids'), type VARCHAR(32) NOT NULL, bed_type VARCHAR(32) NOT NULL, max_guests INT, description VARCHAR(512));");
                builder.append("CREATE SEQUENCE room_fair_ids;");
                builder.append("CREATE TABLE room_fair(id INT PRIMARY KEY DEFAULT NEXTVAL('room_fair_ids'), value DOUBLE PRECISION, season VARCHAR(32));");
                builder.append("CREATE SEQUENCE accomodation_fair_relation_ids;");
                builder.append("CREATE TABLE accomodation_fair_relation(id INT PRIMARY KEY DEFAULT NEXTVAL('accomodation_fair_relation_ids'), id_accomodation INT REFERENCES accomodation(id), id_room_fair INT REFERENCES room_fair(id));");
                Statement statement;
                statement = connection.createStatement();
                statement.execute(builder.toString());
            }
        }


        public static void dropTestDB() throws BookingDbException, SQLException {
            BookingDBTest tdb = new BookingDBTest();
            try (Connection connection = tdb.connectToPostgreSQL()) {
                Statement statement = connection.createStatement();
                statement.execute("DROP DATABASE booking;");
            }
        }

        public void dropDataFromTables() throws BookingDbException, SQLException {
            try (Connection connection = connect()) {
                StringBuilder builder = new StringBuilder();
                builder.append("ALTER TABLE accomodation DISABLE TRIGGER ALL;\n DELETE FROM accomodation;");
                builder.append("ALTER TABLE room_fair DISABLE TRIGGER ALL; \nDELETE FROM room_fair;");
                builder.append("ALTER TABLE accomodation_fair_relation DISABLE TRIGGER ALL; \n DELETE FROM accomodation_fair_relation;");

                Statement statement;
                statement = connection.createStatement();
                statement.execute(builder.toString());
            }
        }

    }
