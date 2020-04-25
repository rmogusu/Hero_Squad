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
        Hero.clearAllHeroes();
    }
    @Test
    public void NewHeroObjectGetsCorrectlyCreated_true() throws Exception {
        Hero hero = new Hero("Rose",18,"Fight","Sleep");
        assertEquals(true, hero instanceof Hero);
    }
    @Test
    public void Hero_InstantiatesWithName_true() throws Exception {
        Hero hero = new Hero("Rose",18,"Fight","Sleep");
        assertEquals("Rose", hero.getName());
    }
    @Test
    public void Hero_InstantiatesWithAge_true() throws Exception {
        Hero hero = new Hero("Rose",18,"Fight","Sleep");
        assertEquals(18, hero.getAge());
    }
    @Test
    public void Hero_InstantiatesWithPower_true() throws Exception {
        Hero hero = new Hero("Rose",18,"Fight","Sleep");
        assertEquals("Fight", hero.getPower());
    }
    @Test
    public void Hero_InstantiatesWithWeakness_true() throws Exception {
        Hero hero = new Hero("Rose",18,"Fight","Sleep");
        assertEquals("Sleep", hero.getWeakness());
    }
    @Test
    public void AllHeroesAreCorrectlyReturned_true() {
        Hero hero = new Hero("Rose",18,"Fight","Sleep");
        Hero otherHero = new Hero("Rose",18,"Fight","Sleep");
        assertEquals(2, Hero.getAll().size());
    }

    @Test
    public void AllHeroesContainsAllHeroes_true() {
        Hero hero = new Hero("Rose",18,"Fight","Sleep");
        Hero otherHero = new Hero("Rose",18,"Fight","Sleep");
        assertTrue(Hero.getAll().contains(hero));
        assertTrue(Hero.getAll().contains(otherHero));
    }
}