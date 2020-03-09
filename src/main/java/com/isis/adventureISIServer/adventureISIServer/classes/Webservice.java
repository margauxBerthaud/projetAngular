/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isis.adventureISIServer.adventureISIServer.classes;

import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

/**
 *
 * @author marga
 */

public class Webservice {
    Services services;
    
    public Webservice(){
        services = new Services();
    }
    
@GET
@Path("world")
@Produces(MediaType.APPLICATION_XML)
public Response getWorld() throws JAXBException{
    return Response.ok(services.readWorldFromXml()).build();
}
    
}