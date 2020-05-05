import dao.Sql2oHeroDao;
import dao.Sql2oSquadDao;
import models.Hero;
import models.Squad;
import org.sql2o.Sql2o;
import spark.ModelAndView;

import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;
import static spark.Spark.staticFileLocation;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        //String connectionString = "jdbc:postgresql://ec2-34-233-186-251.compute-1.amazonaws.com:5432/d9odu9hmgel35e"; //!
        //Sql2o sql2o = new Sql2o(connectionString, "iwlltlzqhvfffy", "d86830a444dc458bc0389a0003ab8a24f30cd2cc653e4ed13e07f84704f942b0"); //String connectionString = "jdbc:postgresql://localhost:5432/superhero";
        String connectionString = "jdbc:postgresql://localhost:5432/superhero";
        Sql2o sql2o = new Sql2o(connectionString, "rose", "wambua");
        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);
        Sql2oSquadDao squadDao = new Sql2oSquadDao(sql2o);

        //get: show all Heroes in all squads and show all squads
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            List<Hero> heroes = heroDao.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //show new squad form
        get("/squad/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            return new ModelAndView(model, "squad-form.hbs"); //new
        }, new HandlebarsTemplateEngine());

        //post: process new squad form
        post("/squads", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String cause = req.queryParams("cause");
            String  max = req.queryParams("max_size");
            int max_size = Integer.parseInt(max);
            if(max!=null){
                try{
                    max_size = Integer.parseInt(max);
                }catch(Exception e){
                }
            }
            Squad newSquad = new Squad(name,cause,max_size);
            squadDao.add(newSquad);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        // list all heroes
        get("/squad/list", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            return new ModelAndView(model, "squad-details.hbs");
        }, new HandlebarsTemplateEngine());
        //get: delete all categories and all tasks
        get("/squads/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            squadDao.clearAllSquads();
            heroDao.clearAllHeroes();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete all heroes
        get("/heroes/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            heroDao.clearAllHeroes();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

         //get: show an individual Squad and heroes it contains
        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params("id"));
            Squad foundSquad = squadDao.findById(idOfSquadToFind);
            model.put("squad", foundSquad);
            List<Hero> allHeroesBySquad = squadDao.getAllHeroesBySquad(idOfSquadToFind);
            model.put("heroes", allHeroesBySquad);
            model.put("squads", squadDao.getAll());
            return new ModelAndView(model, "squad-detail.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show a form to update a Squad
        get("/squads/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("editSquad", true);
            Squad squads = squadDao.findById(Integer.parseInt(req.params("id")));
            model.put("squad", squads);
            model.put("squads", squadDao.getAll());
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());
        //post: process a form to update a squad
        post("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToEdit = Integer.parseInt(req.params("id"));
            String newName = req.queryParams("newSquadName");
            String max = req.queryParams("newSquadMax_size");
            int newMax_size = 0;
            if(max!=null){
                try{
                    newMax_size = Integer.parseInt(max);
                }catch(Exception e){
                }
            }
            String newCause = req.queryParams("newSquadCause");
            squadDao.update(idOfSquadToEdit, newName,newMax_size,newCause);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show new hero form
        get("/heroes/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new hero form
        post("/heroes", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            String name = req.queryParams("name");
            String  mAge = req.queryParams("age");
            int age = Integer.parseInt(mAge);
            if(mAge!=null){
                try{
                    age = Integer.parseInt(mAge);
                }catch(Exception e){
                }
            }
            String power = req.queryParams("power");
            String weakness = req.queryParams("weakness");
            int squadId = Integer.parseInt(req.queryParams("squadId"));
            Hero newHero = new Hero(name,age,power,weakness,squadId);
            heroDao .add(newHero);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        // list all heroes
        get("/heroes/list", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Hero> heroes = heroDao.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "hero-detail.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show an individual hero
        get("/heroes/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(req.params("id"));
            Hero foundHero= heroDao.findById(idOfHeroToFind);
            model.put("heroes", foundHero);
            return new ModelAndView(model, "hero-detail.hbs");
        }, new HandlebarsTemplateEngine());


    }
}


