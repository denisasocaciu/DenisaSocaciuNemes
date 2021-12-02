package JDBC;

import org.junit.*;

import java.sql.SQLException;
import java.util.List;

public class SQLAccomodationDAOTest extends BookingDB {


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
    public void whenNewClientsInsertedIntoDB_getReturnsThem() throws BookingDbException, SQLException {
        Accomodation accomodation1 = new Accomodation();
        accomodation1.setType("b&b");
        accomodation1.setBedType("kingsize");
        accomodation1.setMaxGuests(12);
        accomodation1.setDescription("my lovely cottage");

        Accomodation accomodation2 = new Accomodation();
        accomodation2.setType("hotel");
        accomodation2.setBedType("hostel 6 beds");
        accomodation2.setMaxGuests(100);
        accomodation2.setDescription("party place");

        accomodationsDAO.add(accomodation1);
        accomodationsDAO.add(accomodation2);

        List<Accomodation> all = accomodationsDAO.getAll();

        Assert.assertArrayEquals(new Accomodation[]{accomodation1, accomodation2}, all.toArray());
    }

    @Test
    public void afterClientDeleted_dbDoesNotContainIt() throws BookingDbException, SQLException {
        Accomodation accomodation1 = new Accomodation();
        accomodation1.setType("b&b");
        accomodation1.setBedType("kingsize");
        accomodation1.setMaxGuests(12);
        accomodation1.setDescription("my lovely cottage");
        accomodationsDAO.add(accomodation1);
        accomodationsDAO.delete(accomodation1);
        List<Accomodation> all = accomodationsDAO.getAll();
        Assert.assertEquals(0, all.size());
    }

    @Test
    public void afterClientUpdated_dbReflectsTheChange() throws BookingDbException, SQLException {
        Accomodation accomodation1 = new Accomodation();
        accomodation1.setType("b&b");
        accomodation1.setBedType("kingsize");
        accomodation1.setMaxGuests(12);
        accomodation1.setDescription("my lovely cottage");
        accomodationsDAO.add(accomodation1);

        accomodation1.setType("new bedtypeX");
        accomodationsDAO.update(accomodation1);
        List<Accomodation> all = accomodationsDAO.getAll();
        Assert.assertEquals(accomodation1, all.get(0));
    }

}