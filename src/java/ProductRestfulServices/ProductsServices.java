/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ProductRestfulServices;

import Assignment4.DB_Work;
import Assignment4.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.ws.rs.core.UriInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * REST Web Service
 *
 * @author harsha
 */
@Path("products")
public class ProductsServices {
    DB_Work db=new DB_Work();
    Connection con;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProductsServices
     */
    public ProductsServices() {
        con=db.getConnection();
    }

      @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProductsById(@PathParam("id") int id){
        JSONObject obj=new JSONObject();
        try {
            String query="SELECT * FROM product WHERE productid=?";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                obj.put("productId", rs.getInt("productid"));
                obj.put("productName", rs.getString("name"));
                obj.put("description", rs.getString("description"));
                obj.put("quantity", rs.getInt("quantity"));
            }
        } catch (SQLException e) {
        }
        return obj.toJSONString();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllProducts(){
        JSONArray array=new JSONArray();
        try {
            String query="SELECT * FROM product";
            PreparedStatement stmt=con.prepareStatement(query);
            ResultSet rs=stmt.executeQuery();            
            while(rs.next()){
                JSONObject obj=new JSONObject();
                obj.put("productId", rs.getInt("productid"));
                obj.put("productName", rs.getString("name"));
                obj.put("description", rs.getString("description"));
                obj.put("quantity", rs.getInt("quantity"));
                array.add(obj);
            }
        } catch (SQLException e) {
        }
        return array.toJSONString();
    }
    
    
  
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProductsByName(@PathParam("name") String productName){
        JSONObject obj=new JSONObject();
        try {
            String query="SELECT * FROM product WHERE name=?";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setString(1, productName);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                obj.put("productId", rs.getInt("productid"));
                obj.put("productName", rs.getString("name"));
                obj.put("description", rs.getString("description"));
                obj.put("quantity", rs.getInt("quantity"));
            }
        } catch (SQLException e) {
        }
        return obj.toJSONString();
    }
    
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteProductById(@PathParam("id") int id){
        try {
            String query="DELETE FROM product WHERE productid=?";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeQuery();
        } catch (SQLException e) {
        }
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertProducts(Product product){
        try {
            String query="INSERT INTO product VALUES(?,?,?,?)";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setInt(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setInt(4, product.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    @PUT
    @Path("{id")
    public void updateProductsById(@PathParam("id") int id){
        try {
            Product p=new Product();
            String query="UPDATE product SET (productid,name,description,quantity)=(?,?,?,?)  WHERE productid=?";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getDescription());
            stmt.setInt(4, p.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    
    
    
}
