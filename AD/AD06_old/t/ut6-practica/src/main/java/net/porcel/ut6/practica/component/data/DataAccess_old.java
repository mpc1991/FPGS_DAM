package net.porcel.ut6.practica.component.data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.porcel.ut6.practica.component.MPCEx;
import net.porcel.ut6.practica.component.dto.Actor;
import net.porcel.ut6.practica.component.dto.Categoria;
import net.porcel.ut6.practica.component.dto.Film;
import org.bson.Document;

public class DataAccess_old {

    private static String url;
    private static String bbdd;
    private static String colection;

    public DataAccess_old() {
        this.url = "mongodb://mporcel:e43125921r@daw.paucasesnovescifp.cat:27017/?authSource=admin";
        this.bbdd = "mporceldb";
        this.colection = "sakila";
    }

    public static MongoCollection getConnection() throws MPCEx {
        MongoClient mongoClient = null;
        MongoDatabase mongoDatabase = null;
        MongoCollection<Document> mongoCollection = null;

        try {
            mongoClient = MongoClients.create(url); // Conectamos al servidor
            mongoDatabase = mongoClient.getDatabase(bbdd); // Obtenemos la bbdd
            mongoCollection = mongoDatabase.getCollection(colection); // obtenemos la colección
        } catch (Exception e) {
            throw new MPCEx("Error al conextarse al servidor: " + e.getMessage());
        }
        return mongoCollection;
    }

    public static ArrayList<Document> getDocuments() throws MPCEx {
        ArrayList<Document> documents = new ArrayList<>();
        MongoCollection<Document> mongoCollection = null;

        try {
            mongoCollection = getConnection();
            mongoCollection.find().into(documents);
        } catch (Exception e) {
            throw new MPCEx("getAllCategories: " + e.getMessage());
        } finally {
        }
        return documents;
    }

    public static Set<Categoria> getCategory() throws MPCEx {
        Set<Categoria> categorias = new HashSet<>(); // Lista de objetos Categoria sin duplicidades
        try {
            // Recorremos todos los documentos
            for (Document document : getDocuments()) {
                // Obtenemos el campo "category" con sus sub-campos
                List<Document> categoryList = (List<Document>) document.get("category");

                // Recorremos todos los sub-campos de "category" y los asignamos al POJO
                for (Document categoryDoc : categoryList) {
                    Categoria categoria = new Categoria();
                    categoria.setId(categoryDoc.getInteger("categoryId"));
                    categoria.setValor(categoryDoc.getString("value"));

                    // Añadimos el POJO a la lista
                    categorias.add(categoria);
                }
            }
        } catch (Exception e) {
            throw new MPCEx("getCategory: " + e.getMessage());
        }
        return categorias;
    }

    public static Set<Actor> getActor() throws MPCEx {
        Set<Actor> actors = new HashSet<>();
        try {
            MongoCollection<Document> mongoCollection = getConnection();

            for (Document document : getDocuments()) {
                List<Document> actorList = (List<Document>) document.get("actor");

                for (Document actorDocument : actorList) {
                    Actor actor = new Actor();
                    actor.setId(actorDocument.getInteger("actorId"));
                    actor.setNom(actorDocument.getString("firstName"));
                    actor.setLlinatge(actorDocument.getString("lastName"));
                    actors.add(actor);
                }
            }
        } catch (Exception e) {
            throw new MPCEx("getActor: " + e.getMessage());
        }
        return actors;
    }

    public static Set<Film> getFilmsByActorAndCategory(Integer idActor, Integer idCategory) throws MPCEx {
        Set<Film> films = new HashSet<>();
        Set<Actor> actors = new HashSet<>();
        Set<Categoria> categorias = new HashSet<>();

        try {
            MongoCollection<Document> mongoCollection = getConnection();
            for (Document document : getDocuments()) {
                
                // Solo Categoria
                if (idActor == null && idCategory != null) {
                    List<Document> categoryList = (List<Document>) document.get("category");
                    for (Document categoryDocument : categoryList) {
                        if (categoryDocument.getInteger("categoryId").equals(idCategory)) {
                            Film film = new Film();
                            film.setId(document.getInteger("_id"));
                            film.setTitol(document.getString("title"));
                            film.setDescripcio(document.getString("description"));
                            film.setDurada(document.getInteger("length"));
                            films.add(film);
                        }
                    }
                } 
                
                // Solo Actor
                else if (idActor != null && idCategory == null) {
                    List<Document> actorList = (List<Document>) document.get("actor");
                    for (Document actorDocument : actorList) {
                        if (actorDocument.getInteger("actorId").equals(idActor)) {
                            Film film = new Film();
                            film.setId(document.getInteger("_id"));
                            film.setTitol(document.getString("title"));
                            film.setDescripcio(document.getString("description"));
                            film.setDurada(document.getInteger("length"));
                            films.add(film);
                        }

                    }
                } 

                // Ambos
                else if (idActor != null && idCategory != null) {
                    List<Document> actorList = (List<Document>) document.get("actor");
                    List<Document> categoryList = (List<Document>) document.get("category");

                    for (Document actorDocument : actorList) {
                        for (Document categoryDocument : categoryList) {
                            if (categoryDocument.getInteger("categoryId").equals(idCategory) && actorDocument.getInteger("actorId").equals(idActor)) {
                                Film film = new Film();
                                film.setId(document.getInteger("_id"));
                                film.setTitol(document.getString("title"));
                                film.setDescripcio(document.getString("description"));
                                film.setDurada(document.getInteger("length"));
                                films.add(film);
                            }
                        }
                    }
                } 
                
                // Ninguno
                else if (idActor == null && idCategory == null) {
                    Film film = new Film();
                    film.setId(document.getInteger("_id"));
                    film.setTitol(document.getString("title"));
                    film.setDescripcio(document.getString("description"));
                    film.setDurada(document.getInteger("length"));
                    films.add(film);
                }
            }
        } catch (Exception e) {
            throw new MPCEx("getFilmsByActorAndCategory: " + e.getMessage());
        }
        return films;
    }

    //--------------------------------------------------------------------------------------------------------
    // Formas para consultar con el profesor
    public static void getCategoryNOT2() throws MPCEx {
        Set<Categoria> categorias = new HashSet<>(); // Lista de objetos Categoria sin duplicidades
        try {
            MongoCollection<Document> mongoCollection = getConnection();

            for (Document document : getDocuments()) {
                Categoria categoria = new Categoria();
                categoria.setId(document.getInteger("category.categoryId"));
                categoria.setValor(document.getString("category.value"));
                categorias.add(categoria);
            }
        } catch (Exception e) {
            throw new MPCEx("getCategory: " + e.getMessage());
        }
    }

    public static void getCategoryvNOT1() throws MPCEx {
        ArrayList<Categoria> documents = new ArrayList<>(); // Objetos "categoria" finales
        MongoCollection<Document> mongoCollection = null;
        List<Document> pipeline = null; // pipeline de aggregation
        List<Document> results = null; // Resultado

        try {
            mongoCollection = getConnection();
            pipeline = List.of(
                    new Document("$unwind", "$category"), // Descomponemos arrays de category
                    new Document("$group", new Document("_id", "$category")) // Agrupamos por valor
            );

            // Ejecutamos la consulta con .aggregate(pipeline) y guardamos los resultados
            results = mongoCollection.aggregate(pipeline).into(new ArrayList<>());

            for (Document document : results) {
                // Cada "document" representa un grupo único de categoría
                // El campo "_id" contiene el objeto original del array "category"
                Document categoryDoc = (Document) document.get("_id"); // extraemos el objeto de categoría

                // Extraemos los valores que nos interesan: el ID y el nombre de la categoría
                int id = categoryDoc.getInteger("categoryId");
                String valor = categoryDoc.getString("value");

                // Creamos el objeto Categoria con esos valores
                Categoria categoria = new Categoria(id, valor);
                documents.add(categoria);
            }
        } catch (Exception e) {
            throw new MPCEx("getAllCategories: " + e.getMessage());
        } finally {
        }
    }
}
