package com.projet.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControleurFrontal extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if (action != null) {
            if( "accueil".equals(action) ) {
                request.getServletContext().getRequestDispatcher("/main").forward(request, response);
                out.println("Vous êtes dans l'action « accueil »...");
            }
            if ("login".equals(action)) {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/signin");
                r.forward(request, response);     
                return;
            }            
            if ("logout".equals(action)) {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/signout");
                r.forward(request, response);                
            }            
            if("evaluer".equals(action)) {                
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/evaluer");
                r.forward(request, response);
            }
            if ("consulter".equals(action)) {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/consulter");
                r.forward(request, response);                
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    public String getServletInfo() {
        return "Short description";
    }
}