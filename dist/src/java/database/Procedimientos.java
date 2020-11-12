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
          public String CrearEditorial( String NOMBRE){
        String sql = "INSERT INTO EDITORIAL (NOMBRE)   VALUES(?)";
        try{
            prstmt = cn.prepareStatement(sql); 
            prstmt.setString(1, NOMBRE);
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
                    public String CrearAutor( String NOMBRE,String NACIONALIDAD){
        String sql = "INSERT INTO AUTOR (NOMBRE, NACIONALIDAD)   VALUES(?,?)";
        try{
            prstmt = cn.prepareStatement(sql); 
            prstmt.setString(1, NOMBRE);
            prstmt.setString(2, NACIONALIDAD);
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
     public String CrearCategoria( String NOMBRE,String DESCRIPCION){
        String sql = "INSERT INTO CATEGORIA (NOMBRE, DESCRIPCION)   VALUES(?,?)";
        try{
            prstmt = cn.prepareStatement(sql); 
            prstmt.setString(1, NOMBRE);
            prstmt.setString(2, DESCRIPCION);
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
           public StringBuffer getEditorial(){   
        String sql="SELECT * FROM editorial;";
        try{
        prstmt = cn.prepareStatement(sql);     
  
        result = prstmt.executeQuery();            
        
            if (result!=null){
                while (result.next()){
                sb.append("<option value =").append('"').append(result.getString("IDEDITORIAL")).append('"').append('>').append(result.getString("NOMBRE")).append("</option>");
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
     public StringBuffer getAutor(){   
        String sql="SELECT * FROM autor;";
        try{
        prstmt = cn.prepareStatement(sql);     
  
        result = prstmt.executeQuery();            
        
            if (result!=null){
                while (result.next()){
                sb.append("<option value =").append('"').append(result.getString("ID")).append('"').append('>').append(result.getString("NOMBRE")).append("</option>");
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
          public StringBuffer getCategoria(){   
        String sql="SELECT * FROM categoria;";
        try{
        prstmt = cn.prepareStatement(sql);     
  
        result = prstmt.executeQuery();            
        
            if (result!=null){
                while (result.next()){
                sb.append("<option value =").append('"').append(result.getString("IDCATEGORIA")).append('"').append('>').append(result.getString("NOMBRE")).append("</option>");
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
    public String deleteCategoria(String id){   
        String sql="delete from categoria where idcategoria ="+id;
        try{
        prstmt = cn.prepareStatement(sql);     
  
            int resultado = prstmt.executeUpdate(); 

              if(resultado > 0){
                    return "1";
                }else{
                    return"0";
                }                
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
                    return "1";

    }
        public String deleteLibro(String id){   
        String sql="delete from libros where codigo ='"+id+"'";
        try{
        prstmt = cn.prepareStatement(sql);     
        
         int resultado = prstmt.executeUpdate(); 

              if(resultado > 0){
                    return "1";
                }else{
                    return"0";
                }
        }
        catch(SQLException ex){ 
            ex.printStackTrace();
        }
                    return "1";

    }
        
                public String deleteAutor(String id){   
        String sql="delete from autor where id ="+id;
        try{
        prstmt = cn.prepareStatement(sql);     
  
           int resultado = prstmt.executeUpdate(); 

              if(resultado > 0){
                    return "1";
                }else{
                    return"0";
                }         
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
                    return "1";

    }
         public String deleteEditorial(String id){   
        String sql="delete from editorial where ideditorial ="+id;
        try{
        prstmt = cn.prepareStatement(sql);     
  
            int resultado = prstmt.executeUpdate(); 

              if(resultado > 0){
                    return "1";
                }else{                    return"0";

                }         
        }
        catch(SQLException ex){
            ex.printStackTrace();
                    return"0";
        }

    }
      public StringBuffer MostrarEditorial(){   
        String sql="SELECT * FROM editorial;";

        try{
        prstmt = cn.prepareStatement(sql);     
  
        result = prstmt.executeQuery();            
        
            if (result!=null){
                while (result.next()){
                sb.append("<tr>");
                sb.append("<td >").append(result.getString("IDEDITORIAL")).append("</td>");
                sb.append("<td >").append(result.getString("NOMBRE")).append("</td>");
                sb.append("<td class='btn btn-danger' onclick ='").append("deleteFuntion(").append(result.getString("IDEDITORIAL")).append(")'").append('>').append("Eliminar").append("</td>");

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
            public StringBuffer MostrarAutores(){   
        String sql="SELECT * FROM autor;";
         

        try{
        prstmt = cn.prepareStatement(sql);     
  
        result = prstmt.executeQuery();            
        
            if (result!=null){
                while (result.next()){
                sb.append("<tr>");
                sb.append("<td >").append(result.getString("ID")).append("</td>");
                sb.append("<td >").append(result.getString("NOMBRE")).append("</td>");
                sb.append("<td >").append(result.getString("NACIONALIDAD")).append("</td>");
                sb.append("<td class='btn btn-danger' onclick ='").append("deleteFuntion(").append(result.getString("ID")).append(")'").append('>').append("Eliminar").append("</td>");

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
  public StringBuffer MostrarCategoria(){   
        String sql="SELECT * FROM CATEGORIA;";
       
        try{
        prstmt = cn.prepareStatement(sql);     
  
        result = prstmt.executeQuery();            
        
            if (result!=null){
                while (result.next()){
                sb.append("<tr>");
                sb.append("<td >").append(result.getString("IDCATEGORIA")).append("</td>");
                sb.append("<td >").append(result.getString("NOMBRE")).append("</td>");
                sb.append("<td >").append(result.getString("DESCRIPCION")).append("</td>");
                sb.append("<td class='btn btn-danger' onclick ='").append("deleteFuntion(").append(result.getString("IDCATEGORIA")).append(")'").append('>').append("Eliminar").append("</td>");

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
                sb.append("<td class='btn btn-danger'  onclick ='").append("deleteFuntion(").append(result.getString("CODIGO")).append(")'").append('>').append("Eliminar").append("</td>");

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
