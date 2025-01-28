package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("")
public class ProductsRessource {
    @Path("products/{title}")
    @GET
    public Response getProducts(@QueryParam("sort") String sort, @PathParam("title") String title, @HeaderParam("Authorisation") String auth){
        if (auth == null || auth.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        System.out.println(title);
        return Response.ok(sort).build();
    }
    @Path("products")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(@HeaderParam("Authorisation") String auth, Product product){
        if (auth == null || auth.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.created(URI.create("products/" + product.getTitle())).entity(product).build();
    }
}

