package models;

import java.util.Objects;

public class Squad {
    private String name;
    private String cause;
    private int max_size;
    private int id;



    public Squad (String name ,  String cause ,int max_size ) {
        this.name=name;
        this.cause =cause;
        this.max_size = max_size;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Squad)) return false;
        Squad squad = (Squad) o;
        return getMax_size() == squad.getMax_size() &&
                getId()== squad.getId() &&
                getName().equals(squad.getName()) &&
                getCause().equals(squad.getCause()) ;

    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMax_size(), getCause(),  getId());
    }
    public String getName() {
        return name;
    }

    public String getCause() {
        return cause;
    }

    public int getMax_size() {
        return max_size;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setMax_size(int max_size) {
        this.max_size = max_size;
    }

    public void setId(int id) {
        this.id = id;
    }
}
