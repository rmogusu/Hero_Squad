package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void NewHeroObjectGetsCorrectlyCreated_true() throws Exception {
        Hero hero  = setupNewHero();
        assertEquals(true, hero instanceof Hero);
    }
    @Test
    public void Hero_InstantiatesWithName_true() throws Exception {
        Hero hero  = setupNewHero();
        assertEquals("Rose", hero.getName());
    }
    @Test
    public void Hero_InstantiatesWithAge_true() throws Exception {
        Hero hero  = setupNewHero();
        assertEquals(18, hero.getAge());
    }
    @Test
    public void Hero_InstantiatesWithPower_true() throws Exception {
        Hero hero  = setupNewHero();
        assertEquals("Fight", hero.getPower());
    }
    @Test
    public void Hero_InstantiatesWithWeakness_true() throws Exception {
        Hero hero  = setupNewHero();
        assertEquals("Sleep", hero.getWeakness());
    }

    //helper methods
    public Hero setupNewHero(){
        return new Hero ("Rose",18,"Fight","Sleep",1);
    }
}