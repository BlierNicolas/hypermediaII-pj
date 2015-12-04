package com.projet.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControleurFrontal extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if (action != null) {
            if( "accueil".equals(action) ) {
                request.getServletContext().getRequestDispatcher("/main").forward(request, response);
            }
            if ("login".equals(action)) {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/login");
                r.forward(request, response);
            }
            if ("logout".equals(action)) {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/logout");
                r.forward(request, response);                
            }
            if ("afficherListeLivres".equals(action)) {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/afficherListeLivres");
                r.forward(request, response);
            }
            if ("afficherLivre".equals(action)) {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/afficherLivre");
                r.forward(request, response);
            }
            if ("confirmationEvaluationLivre".equals(action)) {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/confirmationEvaluationLivre");
                r.forward(request, response);
            }
            if ("consulterListeCours".equals(action)) {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/consulterListeCours");
                r.forward(request, response);                
            }
            if ("consulterLivre".equals(action)) {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/consulterLivre");
                r.forward(request, response);                
            }
            if ("evaluerLivre".equals(action)) {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/evaluerLivre");
                r.forward(request, response);
            }
            if ("rechercherCours".equals(action)) {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/rechercherCours");
                r.forward(request, response);
            }
            if ("rechercherLivre".equals(action)) {
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/rechercherLivre");
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