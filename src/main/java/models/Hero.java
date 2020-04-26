package models;

import java.util.Objects;

public class Hero {
    private String name;
    private int   age;
    private  String power;
    private  String weakness;
    private int id;
    private int squadId;

    public Hero(String name , int age, String power, String weakness,int squadId) {
        this.name=name;
        this.age =age;
        this.power=power;
        this.weakness=weakness;
        this.squadId = squadId;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
        return getAge() == hero.getAge() &&
                getSquadId() == hero.getSquadId() &&
                getId()== hero.getId() &&
                getName().equals(hero.getName()) &&
                getPower().equals(hero.getPower()) &&
                getWeakness().equals(hero.getWeakness());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getPower(), getWeakness(), getId(),getSquadId());
    }

    public String getName() {
        return  name;
    }
    public int getAge(){
        return age;
    }
    public String getPower(){
        return power;
    }
    public String getWeakness(){
        return weakness;
    }
    public int getId() {
        return id;
    }

    public int getSquadId() {
        return squadId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSquadId(int squadId) {
        this.squadId = squadId;
    }
}
