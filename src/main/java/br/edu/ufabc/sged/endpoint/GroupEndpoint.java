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

import br.edu.ufabc.sged.model.Group;

@Path("/groups")
@Transactional
public class GroupEndpoint {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		
		return Response.ok(Group.listAll()).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") Long id) {
		
		return Response.ok(Group.findById(id)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addItem(Group group) {
		
		group.id = null;
		group.persist();
		
		return Response.created(URI.create(String.format("/groups/%d", group.id))).build();
	}
}
