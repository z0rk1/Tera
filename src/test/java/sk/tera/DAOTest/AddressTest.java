package sk.tera.DAOTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import sk.tera.TestDAO;
import sk.tera.db.DAO.Impl.AddressDAOImpl;
import sk.tera.db.Entity.Address;

import javax.transaction.Transactional;

/**
 * Unit test class for Address entity. Checking primary DB operations with entity.
 *
 * @author z0rk1
 */
@RunWith(MockitoJUnitRunner.class)
@Transactional
public class AddressTest {

    private Address addr1;
    private Address addr2;
    private Address addr3;

    @InjectMocks
    private AddressDAOImpl aDao;

    @InjectMocks
    private TestDAO tDAO;

    @Before
    public void setup() {
        this.addr1 = new Address();
        this.addr1.setStreet("Rovnanova 6");
        this.addr1.setTown("Zlate Moravce");
        this.addr1.setZip("95301");
        this.addr1.setState("Slovakia");

        this.addr2 = new Address();
        this.addr2.setStreet(null);
        this.addr2.setTown(null);
        this.addr2.setZip("84800");
        this.addr2.setState("Slovakia");

        this.addr3 = new Address();
        this.addr3.setStreet("Brezova 251");
        this.addr3.setTown("Nemcinany");
        this.addr3.setZip("85677");
        this.addr3.setState("Slovakia");

        aDao.setEm(tDAO.getEm());
        tDAO.getEm().getTransaction().begin();
    }

    @After
    public void tearDown() {
        tDAO.getEm().getTransaction().commit();

        if (tDAO.getEm().isOpen())
            tDAO.getEm().close();
    }

    /**
     * Test pridavania objektu {@link Address} do databaze.
     */
    @Test
    public void addTest() {
        addAddress(this.addr2, "Malo zlyhat ulozenie chybnej adresy");
        addAddress(new Address(), "Malo zlyhat ulozenie pradneho objektu adresy");

        try {
            Long id = aDao.add(this.addr1);

            Address addr = aDao.getAddressById(id);

            Assert.assertEquals(id, addr.getId());
            Assert.assertEquals(this.addr1.getStreet(), addr.getStreet());
            Assert.assertEquals(this.addr1.getTown(), addr.getTown());
            Assert.assertEquals(this.addr1.getZip(), addr.getZip());
            Assert.assertEquals(this.addr1.getState(), addr.getState());
            Assert.assertEquals(this.addr1, addr);
        } catch (Exception e) {
            e.printStackTrace();
        }

//          when(aDao.getAddressById(id)).thenReturn(this.addr1);
//          verify(aDao,times(1)).persist(addr);
    }

    /**
     * Test mazania objektu {@link Address} z databaze.
     */
    @Test
    public void delTest() {
        delAddress(null, "Malo zlyhat zmazanie null objektu adresy");
//        delAddress(new Address(), "Malo zlyhat zmazanie prazdneho objektu adresy");
//        delAddress(this.addr2, "Malo zlyhat zmazanie chybneho objektu adresy");

        try {
            Long idAddAddr = aDao.add(this.addr1);
            Long idDelAddr = aDao.delete(this.addr1);

            Assert.assertEquals(idAddAddr, idDelAddr);

            idAddAddr = aDao.add(this.addr3);
            idDelAddr = aDao.delete(idAddAddr);

            Assert.assertEquals(idAddAddr, idDelAddr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test vyhladania objektu {@link Address} z databaze pomocou find metod.
     */
    @Test
    public void findAddress() {

    }

    /**
     * Test vyhladania objektu {@link Address} z databaze pomocou get metod.
     */
    @Test
    public void getAddress() {
        try {
            aDao.getAddressById(null);
            Assert.fail("Malo zlyhat vyhladanie adresy z databaze pri null hodnote");
        } catch (Exception e) {
            // Vynimka je zelany stav
        }

        try {
            aDao.getAddressById(999999999L);
            Assert.fail("Malo zlyhat vyhladanie adresy z databaze pri neexistujucej hodnote");
        } catch (Exception e) {
            // Vynimka je zelany stav
        }

        try {
            Long id = aDao.add(this.addr1);
            aDao.getAddressById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addAddress(Address addr, String msg) {
        try {
            aDao.add(addr);
            Assert.fail(msg);
        } catch (Exception e) {
            // Vynimka je zelany stav
        }
    }

    private void delAddress(Address addr, String msg) {
        try {
            aDao.delete(addr);
            Assert.fail(msg);
        } catch (Exception e) {
            // Vynimka je zelany stav
        }
    }
}
