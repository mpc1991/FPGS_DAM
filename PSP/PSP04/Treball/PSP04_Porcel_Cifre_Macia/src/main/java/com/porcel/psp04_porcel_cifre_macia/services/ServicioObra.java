package com.porcel.psp04_porcel_cifre_macia.services;

import java.util.ArrayList;
import java.util.List;

import com.porcel.psp04_porcel_cifre_macia.dto.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import com.porcel.psp04_porcel_cifre_macia.dto.Obra;

//import static com.porcel.psp04_porcel_cifre_macia.services.ServicioUsers.listaUsuarios;

@Path("/obras")
public class ServicioObra {

    private static List<Obra> listaObras = new ArrayList<>() {
        {
            add(new Obra(1,"Excelencia", "1991", "obscura","Macia" ));
            add(new Obra(2,"Perfección", "1996", "lúmica", "Laura" ));
        }
    };

    /**
     * URL: http://localhost:8080/PSP04_Porcel_Cifre_Macia_war_exploded/api/obras
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObras() {
        return Response.ok(listaObras).build();
    }

    /**
     * URL: http://localhost:8080/PSP04_Porcel_Cifre_Macia_war_exploded/api/1
     *
     * @param idObra int
     * @return Response
     */
    @GET
    @Path("/id/{idObra}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObraById(@PathParam("idObra") int idObra) {
        Obra found = null;
        for (Obra obra : listaObras) {
            if(obra.getIdObra() == idObra) {
                found = obra;
            }
        }
        if (found == null) {
            return Response.status(Status.NOT_FOUND).entity("User not found").build();
        } else {
            return Response.ok(found).build();
        }
    }

    /**
     * URL: http://localhost:8080/PSP04_Porcel_Cifre_Macia_war_exploded/api/Macia
     *
     * @param autor String
     * @return Response
     */
    @GET
    @Path("/autor/{autor}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObraById(@PathParam("autor") String autor) {
        Obra found = null;
        for (Obra obra : listaObras) {
            if(obra.getAutor().equals(autor)) {
                found = obra;
            }
        }
        if (found == null) {
            return Response.status(Status.NOT_FOUND).entity("User not found").build();
        } else {
            return Response.ok(found).build();
        }
    }

    /** REVISAR CON EL TUTOR
     * URL: http://localhost:8080/PSP04_Porcel_Cifre_Macia_war_exploded/api/obras/createObra
     * BODY --> RAW
     * {
     *     "idObra": 4,
     *     "titol": "Evasion",
     *     "any": "2025",
     *     "modalidad": "comic",
     *     "autor": "Jaume"
     * }
     * @param obraRequest Objeto de tipo Obra recibido en formato JSON.
     * @return Response list NOTA: Si no existe el constructor vacío de User, da un
     *         error y el userRequest viene null.
     */
    @POST
    @Path("/createObra")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createObra(Obra obraRequest) {
        if (obraRequest == null) {
            return Response.status(Status.BAD_REQUEST).entity("Obra request is null").build();
        }

        listaObras.add(obraRequest);
        return Response.status(Status.CREATED).entity(obraRequest).build();
    }


    /**
     * URL: http://localhost:8080/PSP04_Porcel_Cifre_Macia_war_exploded/api/obras/updateObra
     * Parameters in
     * Postman:
     * {
     *     "idObra":1,
     *     "titol": "Evasion",
     *     "any": "2025",
     *     "modalidad": "comic",
     *     "autor": "Jaume"
     * }
     * // @param User
     * @return user modified NOTA: Si no existe el constructor vacío de User, da un
     *         error y el userRequest viene null.
     */
    @PUT
    @Path("/updateObra")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateObra(Obra obraUpdate) {
        Obra found = null;
        for (Obra obra : listaObras) {
            if (obra.getIdObra() == obraUpdate.getIdObra()) {
                found = obra;
            }
        }

        if (found == null) {
            return Response.status(Status.BAD_REQUEST).entity("User not found").build();
        } else {
            found.setTitol(obraUpdate.getTitol());
            found.setAny(obraUpdate.getAny());
            found.setModalidad(obraUpdate.getModalidad());
            found.setAutor(obraUpdate.getAutor());
            return Response.ok(found).build();
        }
    }

    /**
     * URL: http://localhost:8080/API_REST_WS-RS/api/users/deleteUser/Rosa
     *
     * //@param User
     * @return Response
     */
    @DELETE
    @Path("/deleteObra/{idObra}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteObra(@PathParam("idObra") int idObra) {
        Obra found = null;
        for (Obra obra : listaObras) {
            if (obra.getIdObra() == idObra) {
                found = obra;
                listaObras.remove(found);
                break;
            }
        }

        if (found == null) {
            return Response.status(Status.BAD_REQUEST).entity("User not found").build();
        } else {
            return Response.ok(listaObras).build();
        }
    }
}
