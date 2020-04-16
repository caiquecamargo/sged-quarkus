package br.edu.ufabc.sged.model;

import java.io.InputStream;

import javax.servlet.http.Part;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class MultipartBodyItem {
	
	@FormParam("file")
	@PartType(MediaType.APPLICATION_OCTET_STREAM)
	public InputStream file;
	
	@FormParam("restricoes")
	@PartType(MediaType.TEXT_PLAIN)
	public String restricoes;
	
	@FormParam("id")
	@PartType(MediaType.TEXT_PLAIN)
	public String id;

}
