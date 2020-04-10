/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.sged.endpoint;

import java.net.URI;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.ufabc.sged.model.User;

/**
 *
 * @author Caique
 */

@Path("/users")
@Transactional
public class UserEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {

        return Response.ok(User.listAll()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {

        return Response.ok(User.findById(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {

        user.id = null;
        user.persist();

        return Response.created(URI.create(String.format("/users/%d", user.id))).build();
    }
}
