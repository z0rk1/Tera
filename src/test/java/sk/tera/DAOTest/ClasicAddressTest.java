package sk.tera.DAOTest;

import org.junit.*;
import sk.tera.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Logger;

/**
 * Created by leaflock on 30.3.2016.
 */
public class ClasicAddressTest {

    private static Logger log = Logger.getLogger(ClasicAddressTest.class.getName());

    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeClass
    public static void setUpClass() throws Exception {
        emf = Persistence.createEntityManagerFactory("testDS");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        emf.close();
    }

    @Before
    public void beginTransaction() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @After
    public void rollbackTransaction() {
        em.getTransaction().commit();

//        if (em.getTransaction().isActive())
//            em.getTransaction().rollback();

        if (em.isOpen())
            em.close();
    }

    @Test
    public void addTest() {
        Address addr = new Address();
        addr.setStreet("Brezova 6");
        addr.setTown("Zlate Moravce");
        addr.setZip("95301");
        addr.setState("Slovakia");

        em.persist(addr);
    }
}
