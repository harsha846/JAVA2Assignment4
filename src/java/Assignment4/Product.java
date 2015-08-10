/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Assignment4;

import org.json.simple.JSONObject;

/**
 *
 * @author harsha
 */
public class Product {
    int id;
    String name;
    String description;
    int quantity;
    
    public Product(){
        
    }
    
    public Product(int ppid,String pname,String pdes,int pquant){
        this.id=ppid;
        this.name=pname;
        this.description=pdes;
        this.quantity=pquant;
    }
    
     public void setName(String name){
        this.name=name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    public int getId(){
        return this.id;
    }
    
   
    
   
    
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    
    public int getQuantity(){
        return this.quantity;
    }
     public void setDescription(String description){
        this.description=description;
    }
    
    public String getDescription(){
        return this.description;
    }
    public String toJSON(){
        JSONObject obj=new JSONObject();
        
        obj.put("productId", this.id);
        obj.put("productname", this.name);
        obj.put("description", this.description);
        obj.put("quantity", this.quantity);
        
        return obj.toJSONString();
    }
}
