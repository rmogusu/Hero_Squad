package models;

import java.util.Objects;

public class Hero {
    private String name;
    private int   age;
    private  String power;
    private  String weakness;
    private int id;

    public Hero(String name , int age, String power, String weakness) {
        this.name=name;
        this.age =age;
        this.power=power;
        this.weakness=weakness;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
        return getAge() == hero.getAge() &&
                getId()== hero.getId() &&
                getName().equals(hero.getName()) &&
                getPower().equals(hero.getPower()) &&
                getWeakness().equals(hero.getWeakness());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getPower(), getWeakness(), getId());
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
}
