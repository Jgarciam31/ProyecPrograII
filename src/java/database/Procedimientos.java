/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Jose1
 */
public class Procedimientos {
      private Conexion conn;
    private Connection cn;
    private PreparedStatement prstmt = null;
    private ResultSet result = null;
    private String[][] usuario;
    private  StringBuffer sb = new StringBuffer();
    
    public Procedimientos(){
        conn= new Conexion();
        cn = conn.conectar();

    }
   
     public String CrearLibro( String CODIGO,String ISBN,String NOMBRE,String AÑO,String AUTOR_ID,String CATEGORIA_IDCATEGORIA, String EDITORIAL_IDEDITORIAL ){
        String sql = "INSERT INTO LIBROS (CODIGO, ISBN, NOMBRE, ANIO, AUTOR_ID,CATEGORIA_IDCATEGORIA,EDITORIAL_IDEDITORIAL)   VALUES(?,?,?,?,?,?,?)";
        try{
            prstmt = cn.prepareStatement(sql); 
            
            prstmt.setString(1, CODIGO);
            prstmt.setString(2, ISBN);
            prstmt.setString(3, NOMBRE);
            prstmt.setString(4, AÑO);
            prstmt.setString(5, AUTOR_ID);
            prstmt.setString(6, CATEGORIA_IDCATEGORIA);
            prstmt.setString(7, EDITORIAL_IDEDITORIAL);
            
          
            
             int resultado = prstmt.executeUpdate(); 
                if(resultado > 0){
                    return "1";
                }else{
                    return"0";
                }
       }catch(SQLException e){
            String error = e.getMessage();  
            if(error.indexOf("ORA-00001") != -1){
                return "0";
            }else{
                return "0";
            }
        }
}
      public StringBuffer MostrarLibro(String query){   
        String sql="SELECT libros.NOMBRE as nombreLibro, libros.ISBN, libros.CODIGO, libros.ANIO , autor.NOMBRE as nombreAutor, categoria.NOMBRE as categoriaNombre, editorial.NOMBRE "
                + "as nombreEditorial FROM libros left join autor on libros.AUTOR_ID = autor.ID left join categoria on libros.CATEGORIA_IDCATEGORIA = categoria.IDCATEGORIA "
                + "left join editorial on libros.EDITORIAL_IDEDITORIAL = editorial.IDEDITORIAL "
                + "where libros.nombre like '%"+query+"%'order by libros.NOMBRE asc;";
        try{
        prstmt = cn.prepareStatement(sql);     
  
        result = prstmt.executeQuery();            
        
            if (result!=null){
                while (result.next()){
                sb.append("<tr>");
                sb.append("<td >").append(result.getString("CODIGO")).append("</td>");
                sb.append("<td >").append(result.getString("ISBN")).append("</td>");
                sb.append("<td >").append(result.getString("nombreLibro")).append("</td>");
                sb.append("<td >").append(result.getString("ANIO")).append("</td>");
                sb.append("<td >").append(result.getString("nombreAutor")).append("</td>");
                sb.append("<td >").append(result.getString("categoriaNombre")).append("</td>");
                sb.append("<td >").append(result.getString("nombreEditorial")).append("</td>");
                sb.append("</tr>");
                }
            }else{
                sb.append("error al consultar");
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
      return sb;

    }

   
}
