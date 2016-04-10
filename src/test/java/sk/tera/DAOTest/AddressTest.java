package sk.tera.DAOTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import sk.tera.db.DAO.Impl.AddressDAOImpl;
import sk.tera.db.Entity.Address;

import javax.persistence.Persistence;
import javax.transaction.Transactional;

/**
 * Unit test class for Address entity. Checking primary DB operations with entity.
 *
 * @author z0rk1
 */
@Transactional
@RunWith(MockitoJUnitRunner.class)
public class AddressTest {

    @InjectMocks
    private AddressDAOImpl aDao;

    @Before
    public void setup() {
        aDao.setEm(Persistence.createEntityManagerFactory("testDS").createEntityManager());
    }

    @After
    public void tearDown() {
        if (aDao.getEm().isOpen())
            aDao.getEm().close();
    }

    /**
     * Test pridavania objektu {@link Address} do databaze.
     */
    @Test
    public void addTest() throws Exception {
        ConsumerWithException<Address, Exception> daoFunction = (address) -> aDao.add(address);

        // Zlyhanie ulozenia null objektu
        failTestingFunc(daoFunction, null, "Malo zlyhat ulozenie null objektu");

        // Zlyhanie ulozenia prazdneho objektu
        failTestingFunc(daoFunction, new Address(), "Malo zlyhat ulozenie prazdneho objektu");

        // Zlyhanie ulozenia chybneho objektu
        Address failAddress = new Address();
        failAddress.setStreet(null);
        failAddress.setTown("Bratislava");
        failAddress.setZip("84800");
        failAddress.setState("Slovakia");
        failTestingFunc(daoFunction, failAddress, "Malo zlyhat ulozenie chybneho objektu");

        failAddress.setStreet("Pionierska 20");
        failAddress.setTown("Bratislava");
        failAddress.setZip(null);
        failAddress.setState("Slovakia");
        failTestingFunc(daoFunction, failAddress, "Malo zlyhat ulozenie chybneho objektu");

        // Zlyhanie ulozenia prazdnej hodnoty
        failAddress = new Address();
        failAddress.setStreet("Pionierska 20");
        failAddress.setTown("");
        failAddress.setZip("84800");
        failAddress.setState("Slovakia");
        failTestingFunc(daoFunction, failAddress, "Malo zlyhat ulozenie prazdnej hodnoty mesta");

        failAddress.setStreet("Pionierska 20");
        failAddress.setTown("Bratislava");
        failAddress.setZip("84800");
        failAddress.setState("");
        failTestingFunc(daoFunction, failAddress, "Malo zlyhat ulozenie prazdnej hodnoty mesta");

        // Zlyhanie ulozenia prilis dlheho ZIP kodu
        failAddress = new Address();
        failAddress.setStreet("");
        failAddress.setTown("Bratislava");
        failAddress.setZip("31415926535897932384");
        failAddress.setState("Slovakia");
        failTestingFunc(daoFunction, failAddress, "Malo zlyhat ulozenie, prilis dlhy retazec ZIP kodu");

        // Zlyhanie ulozenia prilis dlhych retazcov
        failAddress = new Address();
        failAddress.setStreet("skaljdhflkshdflkhaskldfhkalsdhfklahsdfklha asdhfaklshdfklashdfkhaskldfhaskdhfkalshdfkl halskhdfklahsdkfhak skaljdhflkshdflkhaskldfhkalsdhfklahsdfklha asdhfaklshdfklashdfkhaskldfhaskdhfkalshdfkl halskhdfklahsdkfhak skaljdhflkshdflkhaskldfhkalsdhfklahsdfklha asdhfaklshdfklashdfkhaskldfhaskdhfkalshdfkl halskhdfklahsdkfhak");
        failAddress.setTown("Bratislava");
        failAddress.setZip("84800");
        failAddress.setState("Slovakia");
        failTestingFunc(daoFunction, failAddress, "Malo zlyhat ulozenie, prilis dlhy retazec ulice");

        failAddress.setStreet("Pionierska 20");
        failAddress.setTown("skaljdhflkshdflkhaskldfhkalsdhfklahsdfklha asdhfaklshdfklashdfkhaskldfhaskdhfkalshdfkl halskhdfklahsdkfhak skaljdhflkshdflkhaskldfhkalsdhfklahsdfklha asdhfaklshdfklashdfkhaskldfhaskdhfkalshdfkl halskhdfklahsdkfhak skaljdhflkshdflkhaskldfhkalsdhfklahsdfklha asdhfaklshdfklashdfkhaskldfhaskdhfkalshdfkl halskhdfklahsdkfhak");
        failAddress.setZip("84800");
        failAddress.setState("Slovakia");
        failTestingFunc(daoFunction, failAddress, "Malo zlyhat ulozenie, prilis dlhy retazec ulice");

        // Ulozenie dobreho objektu do DB a jeho nasledne vybratie a porovnanie hodnot
        Address goodAddress = new Address();
        goodAddress.setStreet("Pionierska 20");
        goodAddress.setTown("Bratislava");
        goodAddress.setZip("84800");
        goodAddress.setState("Slovakia");

        aDao.getTransaction().begin();

        Long id = aDao.add(goodAddress);
        Address addr = aDao.getAddressById(id);

        aDao.getTransaction().rollback();

        Assert.assertEquals(id, addr.getId());
        Assert.assertEquals(goodAddress.getStreet(), addr.getStreet());
        Assert.assertEquals(goodAddress.getTown(), addr.getTown());
        Assert.assertEquals(goodAddress.getZip(), addr.getZip());
        Assert.assertEquals(goodAddress.getState(), addr.getState());
        Assert.assertEquals(goodAddress, addr);
    }

    /**
     * Test updatu objektu {@link Address} z databaze.
     */
//    @Test
    public void updateTest() throws Exception {
        ConsumerWithException<Address, Exception> daoFunction = (address) -> aDao.update(address);

        // Zlyhanie updatnutie null objektu
        failTestingFunc(daoFunction, null, "Malo zlyhat updatnutie null objektu adresy");

        // Zlyhanie prazdneho objektu
        failTestingFunc(daoFunction, new Address(), "Malo zlyhat updatnutie prazdneho objektu adresy");

        Address address = new Address();
        address.setStreet("Pionierska 20");
        address.setTown("Bratislava");
        address.setZip("84800");
        address.setState("Slovakia");

        aDao.getTransaction().begin();
        Long id = aDao.add(address);
        aDao.getTransaction().commit();

        Address address1 = new Address();
        address1.setId(id);
        address1.setStreet("Masarikova 15");
        address1.setState("Czech");
        address1.setTown("Brno");
        address1.setZip("55500");

        // Zlyhanie updatu necontextovej adresy s prazdnou hodnotou ulice
//        failTestingFunc(() -> { aDao.getTransaction().commit() }, address1, "Malo zlyhat updatnutie adresy pre prazdnu hodnotu ulice");

        aDao.getTransaction().begin();
        Long updateId = aDao.update(address1);
        aDao.getTransaction().commit();
        address1 = aDao.getAddressById(updateId);


        Assert.assertEquals(address.getId(), address1.getId());
        Assert.assertNotEquals(address.getStreet(), address1.getStreet());
        Assert.assertNotEquals(address.getTown(), address1.getTown());
        Assert.assertNotEquals(address.getState(), address1.getState());
        Assert.assertNotEquals(address.getZip(), address1.getZip());

        // Zlyhanie neperzistnuteho objektu
//        aDao.getEm().detach(address);
//        failTestingFunc(daoFunction, address, "Malo zlyhat updatnutie neulozenej adresy");

        // Zlyhanie updatnutia ID
//        address.setId(666L);
//        failTestingFunc(daoFunction, address, "Malo zlyhat updatnutie ID adresy");
    }

    /**
     * Test mazania objektu {@link Address} z databaze.
     */
    @Test
    public void delTest() throws Exception {
        ConsumerWithException<Address, Exception> daoFunction = (address) -> aDao.delete(address);

        failTestingFunc(daoFunction, null, "Malo zlyhat zmazanie null objektu adresy");
//        delAddress(new Address(), "Malo zlyhat zmazanie prazdneho objektu adresy");
//        delAddress(this.addr2, "Malo zlyhat zmazanie chybneho objektu adresy");

        Address address = new Address();
        address.setStreet("Pionierska 20");
        address.setTown("Bratislava");
        address.setZip("84800");
        address.setState("Slovakia");

        aDao.getTransaction().begin();
        Long idAddAddr = aDao.add(address);
        Long idDelAddr = aDao.delete(address);

        Assert.assertEquals(idAddAddr, idDelAddr);

        idAddAddr = aDao.add(address);
        idDelAddr = aDao.delete(idAddAddr);
        aDao.getTransaction().rollback();

        Assert.assertEquals(idAddAddr, idDelAddr);
    }

    /**
     * Test vyhladania objektu {@link Address} z databaze pomocou find metod.
     */
//    @Test
    public void findAddress() {

    }

    /**
     * Test vyhladania objektu {@link Address} z databaze pomocou get metod.
     */
    @Test
    public void getTest() throws Exception {
        ConsumerWithException<Long, Exception> daoFunction = (id) -> aDao.getAddressById(id);

        failTestingFunc(daoFunction, null, "Malo zlyhat vyhladanie adresy z databaze pri null hodnote");

        failTestingFunc(daoFunction, -1L, "Malo zlyhat vyhladanie adresy z databaze pri zapornej hodnote");

        failTestingFunc(daoFunction, 999999999L, "Malo zlyhat vyhladanie adresy z databaze pri neexistujucej hodnote");

        Address address = new Address();
        address.setStreet("Pionierska 20");
        address.setTown("Bratislava");
        address.setZip("84800");
        address.setState("Slovakia");

        aDao.getTransaction().begin();
        Long id = aDao.add(address);
        aDao.getAddressById(id);
        aDao.getTransaction().rollback();
    }

    private void failTestingFunc(ConsumerWithException function, Object o, String msg) {
        try {
            aDao.getTransaction().begin();
            function.accept(o);
            Assert.fail(msg);
        } catch (Exception e) {
            // Vynimka je zelany stav
        }
        finally {
            aDao.getTransaction().rollback();
        }
    }
}
