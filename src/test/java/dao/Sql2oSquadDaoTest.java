package dao;

import models.Hero;
import models.Squad;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oSquadDaoTest {

    private Sql2oSquadDao squadDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "rose", "wambua");
        squadDao = new Sql2oSquadDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    @Test
    public void addingCourseSetsId() throws Exception {
        Squad squad = new Squad ("Super","Fight sexism",10);
        int originalSquadId = squad.getId();
        squadDao.add(squad);
        assertNotEquals(originalSquadId, squad.getId());
    }
    @Test
    public void existingSquadsCanBeFoundById() throws Exception {
        Squad squad = new Squad ("Super","Fight sexism",10);
        squadDao.add(squad);
        Squad foundSquad = squadDao.findById(squad.getId());
        assertNotEquals(squad, foundSquad);
    }
    @Test
    public void addedSquadsAreReturnedFromGetAll() throws Exception {
        Squad squad = new Squad ("Super","Fight sexism",10);
        squadDao.add(squad);
        assertEquals(1, squadDao.getAll().size());
    }
    @Test
    public void noSquadsReturnsEmptyList() throws Exception {
        assertEquals(0, squadDao.getAll().size());
    }

}