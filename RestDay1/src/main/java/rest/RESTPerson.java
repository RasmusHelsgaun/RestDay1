/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entity.Person;
import entity.PersonDTO;
import facade.Facade;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("person")
public class RESTPerson {

    @Context
    private UriInfo context;
    private Gson gson;
    private Facade facade;
    
    public RESTPerson() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.facade = new Facade(Persistence.createEntityManagerFactory("pu"));
    }
    
    @GET
    @Path("person")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersons() {
        String json = gson.toJson(facade.getAllPersons());
        return Response.ok().entity(json).build();
    }
    
    @GET
    @Path("person/{firstName}/{lastName}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName, @PathParam("id") long id) {
        JsonObject person = new JsonObject();
        return null;
    }
    
    @POST
    @Path("person")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(String json) {
        Person p = gson.fromJson(json, Person.class);
        p = facade.addPerson(p);
        String jsonBack = gson.toJson(p);
        return Response.ok().entity(jsonBack).build();
    }
    
    @DELETE
    @Path("person")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(String json) {
        Person p = gson.fromJson(json, Person.class);
        p = facade.deletePerson(p.getId());
        String jsonBack = gson.toJson(p);
        return Response.ok().entity(jsonBack).build();
    }
}
