package JDBC;

import java.sql.SQLException;
import java.util.List;

public interface AccomodationDAO {

        List<Accomodation> getAll() throws Exception;

        void delete(Accomodation accomodation) throws BookingDbException, SQLException;

        void add(Accomodation accomodation) throws BookingDbException, SQLException;

        void update(Accomodation accomodation) throws BookingDbException, SQLException;

    }

