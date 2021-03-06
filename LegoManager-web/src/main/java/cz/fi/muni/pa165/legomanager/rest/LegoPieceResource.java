package cz.fi.muni.pa165.legomanager.rest;

import cz.muni.fi.pa165.legomanager.services.LegoPieceService;
import cz.muni.fi.pa165.legomanager.support.Color;
import cz.muni.fi.pa165.legomanager.support.Color.PieceColor;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import java.util.ArrayList;
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
@Path("/legopieces")
@Component
@Singleton
public class LegoPieceResource {

    @Autowired
    private LegoPieceService service;
    @Context
    private UriInfo context;

    public LegoPieceResource() {
        Authentication auth = new UsernamePasswordAuthenticationToken("rest","rest");
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LegoPieceTO> getAllLegoPieces() {
        return service.getAllLegoPieces();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LegoPieceTO getLegoPiece(@PathParam("id") Long id) {
        return service.getLegoPiece(id);
    }
    
    @GET
    @Path("/colors")
    @Produces(MediaType.APPLICATION_JSON)
    public PieceColor[] getColors(){
        return  Color.PieceColor.values();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewLegoPiece(LegoPieceTO legoPiece) {        
        legoPiece.setLegoKits(new ArrayList<LegoKitTO>());
        service.createLegoPiece(legoPiece);
        return Response.created(context.getAbsolutePath()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLegoPiece(@PathParam("id") Long id, LegoPieceTO legoPiece){
        legoPiece.setId(id);
        service.updateLegoPiece(legoPiece);
        return Response.created(context.getAbsolutePath()).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteLegoPiece(@PathParam("id") Long id){
        service.removeLegoPiece(service.getLegoPiece(id));
        return Response.ok().build();
    }
}
