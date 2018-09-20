/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Address;
import entity.Person;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Rasmus
 */
@Path("resource")
public class Resource {

    @Context
    private UriInfo uriInfo;
    @Context
    private HttpHeaders httpHeaders;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser jsonParser = new JsonParser();

    /**
     * Creates a new instance of Resource
     */
    public Resource() {
    }

    /**
     * Retrieves representation of an instance of rest.Resource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Du kollar konstigt på mig";
    }

    @POST
    @Path("{username}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String json, @PathParam("username") String username, @DefaultValue("333") @QueryParam("id") int id) {
        System.out.println(json);
        System.out.println(username);
        System.out.println(id);

        System.out.println("" + uriInfo.getPathParameters().toString());
        System.out.println("" + uriInfo.getQueryParameters().toString());
        System.out.println("" + uriInfo.getQueryParameters().get("id").toString());

//        return Response.ok("{\"status\":\"ok\"}").build();
        return Response.status(Response.Status.ACCEPTED).entity("{\"status\":\"ok\"}").build();
    }

    /**
     * PUT method for updating or creating an instance of Resource
     *
     * @param json representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJson(String json) {
        
        User user = gson.fromJson(json, User.class);
        System.out.println(user);

        String jsonBack = gson.toJson(user);
        
        JsonObject jo1 = new JsonObject();
        jo1.addProperty("street", "First Road 1");
        jo1.addProperty("city", "Berlin");
        Address a = gson.fromJson(jo1, Address.class);
        System.out.println("AddressJson: " + a);
        
        JsonObject jo2 = jsonParser.parse(json).getAsJsonObject();
        System.out.println("street " + jo2.has("street"));
        System.out.println("STREET " + jo2.has("STREET"));
        System.out.println("street value " + jo2.get("street").getAsString());
        
        JsonArray ja = new JsonArray();
        ja.add(jo1);
        ja.add(jo2);
        String json2 = gson.toJson(ja);
        System.out.println("jsonarray: " + json2);
        
        ArrayList<Address> addresses = new ArrayList();
        addresses.add(a);
        addresses.add(a);
        
        String json3 = gson.toJson(addresses);
        System.out.println("ArrayList to json: " + json3);
        
        Person p = new Person("Jesper", "Rusbjerg", 1337);
        p.addAddress(a);
        a.addPerson(p);
        String Json4 = gson.toJson(p);
        System.out.println("Bidirectional: " + Json4);
        
//        List<Person> persons = new ArrayList();
        
        return Response.ok().entity(jsonBack).build();

    }

    @DELETE
    @Path("{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteJson(String json, @PathParam("username") String username, @DefaultValue("333") @QueryParam("deletiondate") int deletiondate) {
        System.out.println(username);
        System.out.println(deletiondate);

        if (username.equals("joe")) {
            return Response.status(Response.Status.ACCEPTED).entity("{\"username\":\"" + username + "\", \"deletiondate\":\"" + deletiondate + "\"}").build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
