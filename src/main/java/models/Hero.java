package models;

public class Hero {
    private String name;
    private int   age;
    private  String power;
    private  String weakness;
    public Hero(String name , int age, String power, String weakness) {
        this.name=name;
        this.age =age;
        this.power=power;
        this.weakness=weakness;
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

}
