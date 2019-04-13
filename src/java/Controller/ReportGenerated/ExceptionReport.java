/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ReportGenerated;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.persistence.*;
import javax.annotation.*;
import javax.transaction.*;
import Model.*;
import java.util.List;
import Controller.MealManagement.*;

/**
 *
 * @author Richard Khoo
 */
@WebServlet(name = "ExceptionReport", urlPatterns = {"/ExceptionReport"})
public class ExceptionReport extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try{
                Query query = em.createQuery("SELECT o FROM Ordermeal o WHERE o.ordermealid = :ordermealid", Ordermeal.class);
                List<Ordermeal> mealID = query.getResultList();
                
                query = em.createQuery("SELECT o FROM Ordermeal o WHERE o.quantity = :quantity", Ordermeal.class);
                List<Ordermeal> quantity = query.getResultList();
                
                query = em.createQuery("SELECT o FROM Ordermeal o WHERE o.iscanceled = :iscanceled", Ordermeal.class);
                List<Ordermeal> cashRefund = query.getResultList();
                
                
                String output = "";
                
                output += "<tr>" + "<td>" + mealID + "</td>" + "<td>" + quantity + "</td>" +
                        "<td>" + cashRefund + "</td>" + "</tr>";
                
            }
            catch(Exception ex){
                request.setAttribute("output", ex);
                request.getRequestDispatcher("exceptionReport.jsp");
            }
    }

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

}