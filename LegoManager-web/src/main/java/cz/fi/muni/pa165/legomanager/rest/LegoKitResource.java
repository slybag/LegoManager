package cz.fi.muni.pa165.legomanager.rest;

import cz.muni.fi.pa165.legomanager.facades.LegoFacade;
import cz.muni.fi.pa165.legomanager.services.LegoKitService;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    
    @Autowired 
    private LegoFacade facade;
    
    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LegoKitTO> getAllLegoKits() {
        Authentication auth = new UsernamePasswordAuthenticationToken("rest","rest");
        SecurityContextHolder.getContext().setAuthentication(auth);
        return service.getAllLegoKits();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LegoKitTO getLegoKit(@PathParam("id") Long id) {
        return service.getLegoKit(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewLegoKit(LegoKitTO legoKit) {
        if(legoKit.getCategories() == null) legoKit.setCategories(new HashSet<CategoryTO>());
        if(legoKit.getLegoPieces() == null) legoKit.setLegoPieces(new ArrayList<LegoPieceTO>());
        if(legoKit.getLegoSets()== null) legoKit.setLegoSets(new ArrayList<LegoSetTO>());
        facade.create(legoKit);
        return Response.created(context.getAbsolutePath()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLegoKit(@PathParam("id") Long id, LegoKitTO legoKit){
        legoKit.setId(id);
        service.updateLegoKit(legoKit);
        return Response.created(context.getAbsolutePath()).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteLegoKit(@PathParam("id") Long id){
        service.deleteLegoKit(service.getLegoKit(id));
        return Response.ok().build();
    }
}
