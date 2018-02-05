package com.alacriti.hrm.resteasy.resource;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alacriti.hrm.biz.delegate.SampleDelegate;
import com.alacriti.hrm.model.vo.SampleVO;

@Path("/message")
public class SampleResource {

	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.TEXT_XML)
	public Response sampleResourceGet(@PathParam("param") String msg) {

		//SampleDelegate delegate = new SampleDelegate();
		
		System.out.println("msg:::: "+msg);
		
		//String result = "<RestfulExample><H1>" + msg + "</H1></RestfulExample>";

			
		return Response.status(200).entity(msg).build();

	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sampleResourcePost(SampleVO sampleVO) throws FileNotFoundException, IOException {
		System.out.println("sampleVO::: "+sampleVO);
		
		SampleDelegate delegate = new SampleDelegate();
		delegate.getMessage(sampleVO);
		

		return Response.status(200).entity(sampleVO).build();

	}
	
	
	
	
	
	
}
