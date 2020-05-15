package dao;

import models.Hero;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.*;

import static org.junit.Assert.*;

public class Sql2oHeroDaoTest {
    private static Connection conn;
    private static Sql2oHeroDao heroDao;


    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://ec2-18-206-84-251.compute-1.amazonaws.com:5432/d4surr9mntsa7l"; //!
        Sql2o sql2o = new Sql2o(connectionString, "wwsjnsopdbenwg", "162a7975f717db0e056753bdb08d9bc69cc3ebeb0d0d0eceadf0f3f6804bcde2"); //!
        //String connectionString = "jdbc:postgresql://localhost:5432/superhero_test";
        //Sql2o sql2o = new Sql2o(connectionString, "rose", "wambua");
        System.out.println("Connection Initialized");
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }


    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        heroDao.clearAllHeroes();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void addingCourseSetsId() throws Exception {
         Hero hero = setupNewHero();
        int originalHeroId = hero.getId();
        heroDao.add(hero);
        assertNotEquals(originalHeroId, hero.getId());
    }
    @Test
    public void existingHeroesCanBeFoundById() throws Exception {
        Hero hero = setupNewHero();
        heroDao.add(hero);
        Hero foundHero = heroDao.findById(hero.getId());
        assertEquals(hero, foundHero);
    }

    @Test
    public void addedHeroesAreReturnedFromGetAll() throws Exception {
        Hero hero = setupNewHero();
        heroDao.add(hero);
        assertEquals(1, heroDao.getAll().size());
    }

    @Test
    public void noHeroesReturnsEmptyList() throws Exception {
        assertEquals(0, heroDao.getAll().size());
    }
    @Test
    public void updateChangesHeroContent() throws Exception {
        String initialName = "Rose";
        int initialAge = 18;
        String initialPower = "Fight";
        String initialWeakness = "Sleep";
        Hero hero = new Hero (initialName,initialAge,initialPower,initialWeakness,1);
        heroDao.add(hero);

        heroDao.update(hero.getId(),"Maria",16,"Courage","Fall in love",1);
        Hero updatedHero = heroDao.findById(hero.getId());
        assertNotEquals(initialName, updatedHero.getName());
        assertNotEquals(initialAge, updatedHero.getAge());
        assertNotEquals(initialPower, updatedHero.getPower());
        assertNotEquals(initialWeakness, updatedHero.getWeakness());
    }

    @Test
    public void deleteByIdDeletesCorrectHero() throws Exception {
        Hero hero = setupNewHero();
        heroDao.add(hero);
        heroDao.deleteById(hero.getId());
        assertEquals(0, heroDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Hero hero =setupNewHero();
        Hero otherHero = new Hero("Faith",16,"Courage","Fall in love",1);
        heroDao.add(hero);
        heroDao.add(otherHero);
        int daoSize = heroDao.getAll().size();
        heroDao.clearAllHeroes();
        assertTrue(daoSize > 0 && daoSize > heroDao.getAll().size());
    }
    public Hero setupNewHero(){
        return new Hero ("Rose",18,"Fight","Sleep",1);
    }
}