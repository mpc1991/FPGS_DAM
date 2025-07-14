package net.porcel.ut6.practica.component.data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Aggregates.*;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Indexes.ascending;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.porcel.ut6.practica.component.MPCEx;
import net.porcel.ut6.practica.component.dto.Actor;
import net.porcel.ut6.practica.component.dto.Categoria;
import net.porcel.ut6.practica.component.dto.Film;
import org.bson.Document;
import org.bson.conversions.Bson;

public class DataAccess {

    private static String url;
    private static String bbdd;
    private static String colection;

    public DataAccess() {
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

    public static List<Categoria> getCategory() throws MPCEx {
        List<Categoria> categorias = new ArrayList<>(); // Lista de objetos Categoria
        List<Document> documents = new ArrayList<>(); // Lista de Document que nos devuelve MongoDB

        try {
            MongoCollection mongoCollection = getConnection();

            mongoCollection.aggregate(List.of(
                    unwind("$category"), // Descompone los documentos del array "category"
                    group(new Document("categoryId", "$category.categoryId")
                            .append("value", "$category.value")
                    ), // Agrupa por category y elimina duplicados
                    sort(ascending("_id.value"))
            )).into(documents);                     // Añadimos todos los documentos a la lista de Document

            for (Document document : documents) { // por cada documento, creamos un POJO y lo añadimos a su lista
                // group genera un documento con campo ID
                // Almacenamos en "cat" cada documento generado por group
                // {
                //  "_id": {
                //    "categoryId": 5,
                //    "value": "Documentary"
                //  }
                //}
                Document cat = (Document) document.get("_id");

                Categoria categoria = new Categoria();
                categoria.setId(cat.getInteger("categoryId")); //
                categoria.setValor(cat.getString("value"));
                categorias.add(categoria);
            }
        } catch (Exception e) {
            throw new MPCEx("getCategory: " + e.getMessage());
        }
        return categorias; // devolvemos una lista de POJO
    }

    public static List<Actor> getActor() throws MPCEx {
        List<Actor> actors = new ArrayList<>();
        List<Document> documents = new ArrayList<>();

        try {
            MongoCollection<Document> mongoCollection = getConnection();
            mongoCollection.aggregate(List.of(
                    unwind("$actor"),
                    group(new Document("actorId", "$actor.actorId")
                            .append("firstName", "$actor.firstName")
                            .append("lastName", "$actor.lastName")),
                    sort(ascending("_id.firstName"))
            )).into(documents);

            for (Document document : documents) {
                Document act = (Document) document.get("_id");

                Actor actor = new Actor();
                actor.setId(act.getInteger("actorId"));
                actor.setNom(act.getString("firstName"));
                actor.setLlinatge(act.getString("lastName"));
                actors.add(actor);
            }
        } catch (Exception e) {
            throw new MPCEx("getActor: " + e.getMessage());
        }
        return actors;
    }

    public static List<Film> getFilmsByActorAndCategory(Integer idActor, Integer idCategory) throws MPCEx {
        List<Document> documents = new ArrayList<>();
        List<Film> films = new ArrayList<>();

        try {
            MongoCollection<Document> mongoCollection = getConnection();
            //for (Document document : getDocuments()) {

            // Solo Categoria
            if (idActor == null && idCategory != null) {

                // Filtro
                Bson filtro = Filters.eq("category.categoryId", idCategory);
                mongoCollection.find(filtro).into(documents);

                for (Document document : documents) {
                    Film film = new Film();
                    film.setId(document.getInteger("_id"));
                    film.setTitol(document.getString("title"));
                    film.setDescripcio(document.getString("description"));
                    film.setDurada(document.getInteger("length"));
                    films.add(film);
                }
            } // Solo Actor
            else if (idActor != null && idCategory == null) {
                // Filtro
                Bson filtro = Filters.eq("actor.actorId", idActor);
                mongoCollection.find(filtro).into(documents);

                for (Document document : documents) {
                    Film film = new Film();
                    film.setId(document.getInteger("_id"));
                    film.setTitol(document.getString("title"));
                    film.setDescripcio(document.getString("description"));
                    film.setDurada(document.getInteger("length"));
                    films.add(film);
                }
            } // Ambos
            else if (idActor != null && idCategory != null) {
                // Filtro
                Bson filtro = Filters.and(
                        Filters.eq("actor.actorId", idActor),
                        Filters.eq("category.categoryId", idCategory)
                );
                mongoCollection.find(filtro).into(documents);

                for (Document document : documents) {
                    Film film = new Film();
                    film.setId(document.getInteger("_id"));
                    film.setTitol(document.getString("title"));
                    film.setDescripcio(document.getString("description"));
                    film.setDurada(document.getInteger("length"));
                    films.add(film);
                }
            } // Ninguno
            else if (idActor == null && idCategory == null) {
                mongoCollection.find().into(documents);

                for (Document document : documents) {
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
