package br.edu.ufabc.sged.endpoint;

import java.io.File;
import java.net.URI;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import br.edu.ufabc.sged.model.Item;

@Path("/items")
@Transactional
public class ItemEndpoint {
	
	@Context UriInfo uriInfo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		
		return Response.ok(Item.listAll()).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") Long id) {
		
		return Response.ok(Item.findById(id)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addItem(Item item) {
		
		item.id = null;
//		String applicationPath = servletContext.getRealPath("");
//        String uploadFilePath  = applicationPath + "uploads" + File.separator + item.id;
		item.persist();
		String location = uriInfo.getAbsolutePath() + "/uploads/" + item.id;
		System.out.println(location);
		
		File fileSaveDir = new File(location);
        if(!fileSaveDir.exists()){
        	System.out.println(fileSaveDir.mkdirs());
//            fileSaveDir.mkdirs();
        }
		
		return Response.created(URI.create(String.format("/items/%d", item.id))).entity(location).build();
	}
}
