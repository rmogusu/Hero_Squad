package dao;

import models.Hero;
import models.Squad;

import java.util.List;

public interface SquadDao {

    // LIST
    List<Squad> getAll();

    // CREATE
    void add(Squad squad);

    // READ
    Squad findById(int id);

    // UPDATE
    void update(int id, String name,int max_size,String cause);

    // DELETE

    void deleteById(int id);
    void clearAllSquads();
}
