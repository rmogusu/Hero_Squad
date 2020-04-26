package dao;

import models.Hero;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oHeroDaoTest {
    private Sql2oHeroDao heroDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "rose", "wambua");
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
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