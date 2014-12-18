package cz.fi.muni.pa165.legomanager.rest;

import cz.muni.fi.pa165.legomanager.services.LegoSetService;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Petr
 */
@Path("/legosets")
@Component
@Singleton
public class LegoSetResource {

    @Autowired
    private LegoSetService service;
    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LegoSetTO> getAllLegoSets() {
        return service.getAllLegoSets();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LegoSetTO getLegoSet(@PathParam("id") Long id) {
        return service.getLegoSet(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewLegoSet(LegoSetTO legoSet) {        
        legoSet.setLegoKits(new ArrayList<LegoKitTO>());
        legoSet.setCategories(new HashSet<CategoryTO>());
        service.createLegoSet(legoSet);
        return Response.created(context.getAbsolutePath()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLegoSet(@PathParam("id") Long id, LegoSetTO legoSet){
        legoSet.setId(id);
        service.updateLegoSet(legoSet);
        return Response.created(context.getAbsolutePath()).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteLegoSet(@PathParam("id") Long id){
        service.removeLegoSet(service.getLegoSet(id));
        return Response.ok().build();
    }
}
