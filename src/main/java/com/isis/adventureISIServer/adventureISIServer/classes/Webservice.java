/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isis.adventureISIServer.adventureISIServer.classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
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
    static ArrayList<Long> tempMoyen = new ArrayList<>();
    //ProductType product=new Gson().fromJson(data, ProductType.class);
    public Webservice(){
        services = new Services();
    }
    
@GET
@Path("world")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public Response getXml(@Context HttpServletRequest request) throws JAXBException, IOException {
        String username = request.getHeader("X-user");
        World world = services.getWorld(username);
        services.saveWorldToXml(world, username);
        return Response.ok(world).build();
    }

@PUT
@Path("product")
public void putProduct(@Context HttpServletRequest request,ProductType product) throws JAXBException, IOException{
    long before = System.currentTimeMillis();
        String username = request.getHeader("X-user");
        services.updateProduct(username, product);
        long after = System.currentTimeMillis();
        tempMoyen.add(after-before);
        if(tempMoyen.size() % 10 == 0){
            double sum = 0;
            for(int i = 0; i < tempMoyen.size(); i++) {
                sum = sum + tempMoyen.get(i);
            }
        }

}

@PUT
@Path("manager")
public void putManager(@Context HttpServletRequest request,PallierType manager) throws JAXBException, IOException{
   String username = request.getHeader("X-user");
   services.updateManager(username, manager);
}
@PUT
@Path("upgrade")
public void putUpgrade(@Context HttpServletRequest request,PallierType upgrade) throws JAXBException, IOException{
    String username = request.getHeader("X-user");
    services.updateUpgrades(username, upgrade);
}
@DELETE
@Path("world")
public void deleteWorld(@Context HttpServletRequest request)throws JAXBException, IOException{
    String username = request.getHeader("X-user");
    services.deleteWorld(username);
    
}

@PUT
@Path("world")
public void putWorld(@Context HttpServletRequest request,World world) throws JAXBException, IOException{
   String username = request.getHeader("X-user");
   services.saveWorldToXml(world, username);
}

@PUT
@Path("angelupgrade")
public void angelUpgrade(@Context HttpServletRequest request,PallierType ange)throws JAXBException, IOException{
    String username = request.getHeader("X-user");
    services.angelUpgrade(username, ange);
}
}

