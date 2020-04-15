package br.edu.ufabc.sged.endpoint;

import java.io.File;
import java.io.InputStream;
import java.net.URI;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.errorprone.annotations.FormatMethod;

import javax.ws.rs.core.UriInfo;

import br.edu.ufabc.sged.model.Item;
import io.quarkus.runtime.configuration.FastCachedConfigSource;
import io.vertx.core.http.HttpServerRequest;

@Path("/items")
@Transactional
public class ItemEndpoint {
	
	@Context 
	UriInfo uriInfo;

    @Context
    HttpServerRequest request;
	
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
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response putFile(@PathParam("id") @FormParam("id") Long id) {
		
		Item item = Item.findById(id);
		
		System.out.println(id);
//		System.out.println(file);
		
		String path = System.getProperty("user.dir");
		String location = path + "target/uploads/" + item.id;
		System.out.println(location);
		
		File fileSaveDir = new File(location);
        if(!fileSaveDir.exists()){
        	System.out.println(fileSaveDir.mkdirs());
//            fileSaveDir.mkdirs();
        }
		
		return Response.ok(Item.findById(id)).build();
	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addItem(Item item) {
		
		item.id = null;
		item.persist();
		String path = uriInfo.getAbsolutePath().toString();
		String aPath = path.replace("items", "");
		
		return Response.created(URI.create(String.format("/items/%d", item.id))).entity(String.format(aPath + "items/%d", item.id)).build();
	}
}
