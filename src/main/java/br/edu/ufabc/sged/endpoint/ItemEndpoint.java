package br.edu.ufabc.sged.endpoint;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.core.UriInfo;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.edu.ufabc.sged.model.Item;
import br.edu.ufabc.sged.model.MultipartBodyItem;
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
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response putFile(@MultipartForm MultipartBodyItem form) {
		
		Item item = Item.findById(Long.parseLong(form.id));
		
		String path = System.getProperty("user.dir");
		String location = path + File.separator + "uploads" + File.separator + form.id;
		
		File fileSaveDir = new File(location);
        if(!fileSaveDir.exists()){
        	fileSaveDir.mkdirs();
        }
        
        String filePath = location + File.separator + item.nome;
		
		try{
			Files.copy(form.file, Paths.get(filePath));
			item.src = filePath;
			item.persist();
			return Response.ok().build();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		return Response.ok().entity("Erro ao subir arquivo").build();
	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addItem(Item item) {
		
		System.out.println(item.nome);
		
		item.id = null;
		item.persist();
		String path = uriInfo.getAbsolutePath().toString();
		String aPath = path.replace("items", "");
		
		return Response.created(URI.create(String.format("/items/%d", item.id))).entity(String.format(aPath + "items/%d", item.id)).build();
	}
}
