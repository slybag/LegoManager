package cz.fi.muni.pa165.legomanager.rest;

import cz.muni.fi.pa165.legomanager.services.CategoryService;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Petr
 */
@Path("/categories")
@Component
@Singleton
public class CategoryResource {

    @Autowired
    private CategoryService service;
    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryTO> getAllLegoCategorys() {
        return service.getAllCategories();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryTO getCategory(@PathParam("id") Long id) {
        return service.getCategory(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewCategory(CategoryTO legoCategory) {

        if (legoCategory.getLegoKits() == null) {
            legoCategory.setLegoKits(new ArrayList<LegoKitTO>());
        }
        if (legoCategory.getLegoSets() == null) {
            legoCategory.setLegoSets(new ArrayList<LegoSetTO>());
        }
        service.createCategory(legoCategory);
        return Response.created(context.getAbsolutePath()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLegoCategory(@PathParam("id") Long id, CategoryTO legoCategory) {
        legoCategory.setId(id);
        service.updateCategory(legoCategory);
        return Response.created(context.getAbsolutePath()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@PathParam("id") Long id) {
        service.deleteCategory(service.getCategory(id));
        return Response.ok().build();
    }
}
