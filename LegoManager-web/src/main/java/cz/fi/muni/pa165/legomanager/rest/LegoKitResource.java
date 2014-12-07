/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.legomanager.rest;

import cz.muni.fi.pa165.legomanager.services.LegoKitService;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import java.util.List;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Petr
 */

@Path("/legokits")
@Component
@Singleton
public class LegoKitResource {
    
    @Autowired
    private LegoKitService service;
        
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LegoKitTO> getAllLegoKits() {  
         return service.getAllLegoKits();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LegoKitTO getLegoKit (@PathParam("id") Long id) {
        return service.getLegoKit(id);
    }
    
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String itWorks () {
        return "it works";
    }
}
