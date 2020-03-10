/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isis.adventureISIServer.adventureISIServer.classes;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

/**
 *
 * @author marga
 */
@Path("generic")
public class Webservice {
    Services services;
    
    //ProductType product=new Gson().fromJson(data, ProductType.class);
    public Webservice(){
        services = new Services();
    }
    
@GET
@Path("world")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public Response getXml(@Context HttpServletRequest request) throws JAXBException, IOException{
    String username = request.getHeader("X-user");
    return Response.ok(services.getWorld(username)).build();
}

@PUT
@Path("product")
public void putProduct(ProductType product){
   
}

    
}
