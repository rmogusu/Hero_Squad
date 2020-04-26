package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquadTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void NewSquadObjectGetsCorrectlyCreated_true() throws Exception {
        Squad squad  = setupNewSquad();
        assertEquals(true, squad instanceof Squad);
    }
    @Test
    public void Squad_InstantiatesWithName_true() throws Exception {
        Squad squad  = setupNewSquad();
        assertEquals("Super", squad.getName());
    }

    @Test
    public void Squad_InstantiatesWithCause_true() throws Exception {
        Squad squad  = setupNewSquad();
        assertEquals("Fight sexism", squad.getCause());
    }

    @Test
    public void Squad_InstantiatesWithMax_Size_true() throws Exception {
        Squad squad  = setupNewSquad();
        assertEquals(10, squad.getMax_size());
    }
    //helper methods
    public Squad setupNewSquad(){
        return new Squad  ("Super","Fight sexism",10);
    }
}