package dao;


import models.Hero;
import models.Squad;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.*;

import static org.junit.Assert.*;




public class Sql2oSquadDaoTest {
    private static Connection conn;
    private static Sql2oSquadDao squadDao;
    private static Sql2oHeroDao heroDao;


    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://ec2-52-44-166-58.compute-1.amazonaws.com:5432/d7bdtl932rqs4j";
        Sql2o sql2o = new Sql2o(connectionString,"stztbzqpnagyxc", "0937eb339a73bf490858fb56f9da339eef8491395d2101903f00675949ddb3c1");
        //String connectionString = "jdbc:postgresql://localhost:5432/superhero_test";
        //Sql2o sql2o = new Sql2o(connectionString, "rose", "wambua");
        System.out.println("Connection Initialized");
        squadDao = new Sql2oSquadDao(sql2o);
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }


    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        squadDao.clearAllSquads();
        heroDao.clearAllHeroes();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }
    @Test
    public void addingCourseSetsId() throws Exception {
        Squad squad = setupNewSquad();
        int originalSquadId = squad.getId();
        squadDao.add(squad);
        assertNotEquals(originalSquadId, squad.getId());
    }
    @Test
    public void existingSquadsCanBeFoundById() throws Exception {
        Squad squad = setupNewSquad();
        squadDao.add(squad);
        Squad foundSquad = squadDao.findById(squad.getId());
        assertEquals(squad, foundSquad);
    }
    @Test
    public void addedSquadsAreReturnedFromGetAll() throws Exception {
        Squad squad = setupNewSquad();
        squadDao.add(squad);
        assertEquals(1, squadDao.getAll().size());
    }
    @Test
    public void noSquadsReturnsEmptyList() throws Exception {
        assertEquals(0, squadDao.getAll().size());
    }

    @Test
    public void updateChangesSquadContent() throws Exception {
        String initialName = "Super";
        String initialCause = "Fight sexism";
        int initialMax_size = 10;
        Squad squad  = new Squad (initialName,initialCause,initialMax_size);
        squadDao.add(squad);

        squadDao.update(squad.getId(),"Millennium",16,"Fight corruption");
        Squad updatedSquad = squadDao.findById(squad.getId());
        assertNotEquals(initialName, updatedSquad.getName());
        assertNotEquals(initialCause, updatedSquad.getCause());
        assertNotEquals(initialMax_size, updatedSquad.getMax_size());

    }

    @Test
    public void deleteByIdDeletesCorrectSquad() throws Exception {
        Squad squad = setupNewSquad();
        squadDao.add(squad);
        squadDao.deleteById(squad.getId());
        assertEquals(0, squadDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Squad squad = setupNewSquad();
        Squad otherSquad = new Squad ("Millennium","Fight corruption",16);
        squadDao.add(squad);
        squadDao.add(otherSquad);
        int daoSize = squadDao.getAll().size();
        squadDao.clearAllSquads();
        assertTrue(daoSize > 0 && daoSize > squadDao.getAll().size());
    }
    @Test
    public void getAllHeroesBySquadReturnsHeroesCorrectly() throws Exception {
        Squad squad = setupNewSquad();
        squadDao.add(squad);
        int squadId = squad.getId();
        Hero newHero = new Hero("Rose",12,"Fight","Sleep", squadId);
        Hero otherHero = new Hero("Moraa",15,"Stealing","Sleep", squadId);
        Hero thirdHero = new Hero("Mogusu",17,"Corruption","Sleep", squadId);
        heroDao.add(newHero);
        heroDao.add(otherHero);
        assertEquals(2, squadDao.getAllHeroesBySquad(squadId).size());
        assertTrue(squadDao.getAllHeroesBySquad(squadId).contains(newHero));
        assertTrue(squadDao.getAllHeroesBySquad(squadId).contains(otherHero));
        assertFalse(squadDao.getAllHeroesBySquad(squadId).contains(thirdHero));
    }
    public Squad setupNewSquad(){
        return new Squad  ("Super","Fight sexism",10);
    }
}