package models;

public class Squad {
    private String name;
    private String cause;
    private int max_size;
    private int id;
    public Squad (String name ,  String cause ,int max_size) {
        this.name=name;
        this.cause =cause;
        this.max_size=max_size;
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
