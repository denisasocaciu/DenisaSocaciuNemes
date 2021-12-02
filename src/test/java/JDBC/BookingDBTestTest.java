package JDBC;

import org.junit.*;

import java.sql.*;

public class BookingDBTestTest extends BookingDBTest {

    private BookingDBTest db;
    private SQLAccomodationDAO accomodationsDAO;

    @BeforeClass
    public static void initTests() throws BookingDbException, SQLException {

        BookingDBTest.setUpTestDB();
    }

    @AfterClass
    public static void discardTests() throws BookingDbException, SQLException {
        BookingDBTest.dropTestDB();
    }

    @Before
    public void setUp() {
        db = new BookingDBTest();
        accomodationsDAO = new SQLAccomodationDAO(db);
    }

    @After
    public void tearDown() throws BookingDbException, SQLException {
        db.dropDataFromTables();
    }


    @Test
    public void insertDataIntoTables() throws BookingDbException, SQLException, ClassNotFoundException {


        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/booking", "postgres", "admin");


        Accomodation accomodation1 = new Accomodation();
        accomodation1.setType("apart");
        accomodation1.setBedType("double");
        accomodation1.setMaxGuests(2);
        accomodation1.setDescription("apartment");

        Accomodation accomodation2 = new Accomodation();
        accomodation2.setType("hotel");
        accomodation2.setBedType("single");
        accomodation2.setMaxGuests(5);
        accomodation2.setDescription("accomodate 5");

        accomodationsDAO.add(accomodation1);
        accomodationsDAO.add(accomodation2);

        String sql = new StringBuilder()
                .append("insert into room_fair(value, season) values(20,'offseason'); ")
                .append("insert into room_fair(value, season) values(40,'peak'); ")
                .append("insert into accomodation_fair_relation(id_accomodation, id_room_fair) values(1,1); ")
                .append("insert into accomodation_fair_relation(id_accomodation, id_room_fair) values(2,2); ").toString();

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.executeUpdate();

        String retrieveDataQuery = new StringBuilder()
                .append("select acc.type, fair.value from accomodation acc ")
                .append("inner join accomodation_fair_relation relation on acc.id = relation.id_accomodation ")
                .append("inner join room_fair fair on fair.id = relation.id_room_fair ").toString();

        PreparedStatement stmt2 = con.prepareStatement(retrieveDataQuery);
        ResultSet resultSet = stmt2.executeQuery();

        while (resultSet.next()) {
            System.out.println("Roomy type: " + resultSet.getString("type") + "  " + "Price: " + resultSet.getString("value"));
            System.out.println();
        }

        con.close();


    }

}

