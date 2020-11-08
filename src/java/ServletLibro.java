/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import database.Procedimientos;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.validator.constraints.ISBN;

/**
 *
 * @author Jose1
 */
public class ServletLibro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       Writer ajaxSalida =  response.getWriter(); 
       StringBuffer respuestaB =  new StringBuffer();
       Procedimientos bd = new Procedimientos();
        try (PrintWriter out = response.getWriter()) {
      
       switch(request.getParameter("control")){
           case "MOSTRAR":
              String query = request.getParameter("query");
             respuestaB.append(bd.MostrarLibro(query));
            ajaxSalida.write(respuestaB.toString());
            ajaxSalida.flush();
            ajaxSalida.close();
            ;break;
           case "REGISTRO":
              Writer ajaxSalida2 =  response.getWriter(); 
            String respuesta = bd.CrearLibro(request.getParameter("CODIGO"), request.getParameter("ISBN"), request.getParameter("NOMBRE"),  request.getParameter("ANIO"), request.getParameter("AUTOR_ID"), request.getParameter("CATEGORIA_IDCATEGORIA"), request.getParameter("EDITORIAL_IDEDITORIAL"));
            ajaxSalida2.write(respuesta);
            ajaxSalida2.flush();
            ajaxSalida2.close();
               
     
            break;
         
         
       }    
            
            
            
    }}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String Crearlibro(String CODIGO, String ISBN, String NOMBRE, String AÑO, String AUTOR_ID, String CATEGORIA_IDCATEGORIA, String EDITORIAL_IDEDITORIAL ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
