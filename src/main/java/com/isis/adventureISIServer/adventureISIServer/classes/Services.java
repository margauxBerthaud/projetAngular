/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isis.adventureISIServer.adventureISIServer.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author marga
 */
public class Services {
    InputStream input = getClass().getClassLoader().getResourceAsStream("world.xml");
    
    

    World readWorldFromXml() throws JAXBException{
        JAXBContext cont = JAXBContext.newInstance(World.class);
        Unmarshaller u = cont.createUnmarshaller();
        World world = (World) u.unmarshal(input);
        return world;
    }
    
    void saveWorldToXml(World world) throws FileNotFoundException, JAXBException{
        OutputStream output = new FileOutputStream("world.xml");
        JAXBContext cont = JAXBContext.newInstance(World.class);
        Marshaller m = cont.createMarshaller();
        m.marshal(world, output);
    }
}