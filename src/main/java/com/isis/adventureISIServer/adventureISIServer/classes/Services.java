/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isis.adventureISIServer.adventureISIServer.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
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
    
    

    public World readWorldFromXml(String username) throws JAXBException{
        try {
        File file = new File(username+"-world.xml");    
        JAXBContext cont = JAXBContext.newInstance(World.class);
        Unmarshaller u =cont.createUnmarshaller();
        World world =(World) u.unmarshal(file);
        return world;
        }
        catch (Exception e) {
        JAXBContext cont = JAXBContext.newInstance(World.class);
        Unmarshaller u = cont.createUnmarshaller();
        World world = (World) u.unmarshal(input);
        return world;
        }
     
    }
    
    public void saveWorldToXml(World world, String username) throws FileNotFoundException, JAXBException, IOException{
        OutputStream output = new FileOutputStream(username+"-"+"world.xml");
        JAXBContext cont = JAXBContext.newInstance(World.class);
        Marshaller m = cont.createMarshaller();
        m.marshal(world, output);
        output.close();
    }
    
    public World getWorld(String username) throws JAXBException, FileNotFoundException, IOException{
    
        World monde =  readWorldFromXml(username);
            saveWorldToXml(monde, username);
            return monde;
    }
    
    public ProductType findProductByID(World world, int id ){
        ProductType pt = null;
        List<ProductType> t= (List<ProductType>) world.getProducts();
        for (ProductType a :t){
            if (id == a.id){
                pt=a;
            }
        }
        return pt;
    }
    
    public PallierType findManagerByName(World world, String name){
        PallierType manager=null;
        List<PallierType> pt = (List<PallierType>) world.getManagers();
        for (PallierType a: pt){
            if(name.equals(a.getName())){
                manager=a;
            }
        }
        return manager;
    }
    
    public Boolean updateProduct(String username, ProductType newproduct) throws JAXBException, IOException{
        World world = getWorld(username);
        ProductType product = findProductByID(world, newproduct.getId());
        if (product == null){
            return false;
        }
        
        int qtchange =newproduct.getQuantite() - product.getQuantite();
        if (qtchange>0){
            double argent =world.getMoney();
            double q =product.getCroissance();
            //double prix= newproduct.cout*qtchange;
            double prix1 = product.getCout();
            double prix2=prix1*((1-(Math.pow(q, qtchange)))/(1-q));
            double argentRestant = argent- prix2;
            world.setMoney(argentRestant);
            product.setQuantite(newproduct.getQuantite());
            
            
        }
        else {
            product.timeleft=product.vitesse;
            
        }
        
        saveWorldToXml(world, username);
        return true;
    }
    public boolean updateManager(String username,PallierType newmanager) throws JAXBException, IOException{
        World world =getWorld(username);
        PallierType manager = findManagerByName(world, newmanager.getName());
        
        if (manager == null){
            return false;
        }
        manager.setUnlocked(true);
        
        ProductType product = findProductByID(world, manager.getIdcible());
        if (product==null){
            return false;
        }
        double argent = world.getMoney();
        double prix = manager.getSeuil();
        double cout = argent-prix;
        
        saveWorldToXml(world, username);
        return true;
        
    }
    
}
