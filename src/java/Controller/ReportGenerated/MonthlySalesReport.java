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
@WebServlet(name = "MonthlySalesReport", urlPatterns = {"/MonthlySalesReport"})
public class MonthlySalesReport extends HttpServlet {

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
                
                Query query = em.createQuery("SELECT m FROM Meal m", Meal.class);
                List<Meal> mealList = query.getResultList();
                
                query = em.createQuery("SELECT m FROM Meal m WHERE m.mealid = :mealid", Meal.class);
                List<Meal> MealDate = query.getResultList();
                
                query = em.createQuery("SELECT m FROM Meal m WHERE m.mealid = :mealid", Meal.class);
                List<Meal> MealID = query.getResultList();
                
                query = em.createQuery("SELECT m FROM Meal m WHERE m.mealname = :mealname", Meal.class);
                List<Meal> Product = query.getResultList();
                
                query = em.createQuery("SELECT m FROM Mealfood m WHERE m.quantity = :quantity", Mealfood.class);
                List<Mealfood> quantity = query.getResultList();
                
                query = em.createQuery("SELECT m FROM Meal m WHERE m.price = :price", Meal.class);
                List<Meal> Amount = query.getResultList();
                
                String outputMonthly = "";
  
                for (int i = 1; i <= mealList.size(); i++) {
  
                Meal meal = mealList.get(i);
    
                 outputMonthly += "<tr>"
                  + "<td>" + i + "</td>"
                  + "<td>" + "<DATE>" + "</td>"
                  + "<td>" + MealID + "</td>"
                  + "<td>" + Product + "</td>"
                  + "<td>" + quantity + "</td>"
                  + "<td>" + Amount + "</td>"
                  + "</tr>";
                 
            }
            }
            catch(Exception ex){
                request.setAttribute("outputMonthly", ex);
                request.getRequestDispatcher("monthlySalesReport.jsp");
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