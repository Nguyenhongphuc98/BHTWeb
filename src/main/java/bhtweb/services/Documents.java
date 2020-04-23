/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhtweb.services;

import bhtweb.bo.DocumentBO;
import bhtweb.dto.ShortDocumentDTO;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/**
 *
 * @author NguyenHongPhuc
 */
@Path("/api/v1/docs")
@ApplicationPath("")
public class Documents extends Application{
    
    DocumentBO docBO = new DocumentBO();
    
    @GET
    public Response getDocs(@QueryParam("type") String type) {
        //tuy vao type de loc hoac tra ve tat ca dang doc rut gon
        if (type.equals("gooddoc"))
         return Response.ok(docBO.getGoodDocs()).build();
        else
            return Response.ok(docBO.getAllDocs()).build();
    }
    
    @GET
    @Path("/{id}")
    public Response getMsg(@PathParam("id") String id) {
        return Response.status(200).entity(ShortDocumentDTO.makeDoc(id)).build();
    }
}
