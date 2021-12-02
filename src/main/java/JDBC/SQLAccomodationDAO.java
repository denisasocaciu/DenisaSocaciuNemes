package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLAccomodationDAO implements AccomodationDAO {


        private BookingDB db;

        public SQLAccomodationDAO(BookingDB db) {
            this.db = db;
        }

        @Override
        public List<Accomodation> getAll() throws BookingDbException, SQLException {
            try (Connection conn = db.connect()) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * from accomodation;");
                ArrayList<Accomodation> accomodations = new ArrayList<>();
                while (resultSet.next()) {
                    Accomodation accomodation = mapResultSetToAccomodation(resultSet);
                    accomodations.add(accomodation);
                }
                return accomodations;
            }
        }

        private Accomodation mapResultSetToAccomodation(ResultSet resultSet) throws SQLException {
            Accomodation accomodation = new Accomodation();
            accomodation.setId(resultSet.getInt("id"));
            accomodation.setType(resultSet.getString("type"));
            accomodation.setBedType(resultSet.getString("bed_type"));
            accomodation.setMaxGuests(resultSet.getInt("max_guests"));
            accomodation.setDescription(resultSet.getString("description"));
            return accomodation;
        }

        @Override
        public void delete(Accomodation accomodation) throws BookingDbException, SQLException {
            try (Connection connection = db.connect()) {
                Statement statement = connection.createStatement();
                statement.execute("DELETE FROM accomodation WHERE id=" + accomodation.getId() + ";");
            }
        }

        @Override
        public void add(Accomodation accomodation) throws BookingDbException, SQLException {
            try (Connection connection = db.connect()) {
                Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO accomodation(type, bed_type, max_guests, description) values('" + accomodation.getType() + "', '" + accomodation.getBedType() + "', " + accomodation.getMaxGuests() + ", '" + accomodation.getDescription() + "');");
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT CURRVAL('accomodation_ids')");
                resultSet.next();
                accomodation.setId(resultSet.getInt(1));
            }
        }

        @Override
        public void update(Accomodation accomodation) throws BookingDbException, SQLException {
            try (Connection connection = db.connect()) {
                Statement statement = connection.createStatement();
                statement.executeUpdate("UPDATE accomodation SET type = '" + accomodation.getType() + "', bed_type = '" + accomodation.getBedType() + "', max_guests = " + accomodation.getMaxGuests() + " WHERE id = " + accomodation.getId() + ";");
            }
        }

    }
