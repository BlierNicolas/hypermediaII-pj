package com.projet.servlet;

import com.projet.dao.evaluationDAO;
import com.projet.dao.evaluationcoursDAO;
import com.projet.enties.evaluation;
import com.projet.enties.evaluationcours;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class confirmationEvaluationLivre extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            int note = Integer.parseInt(request.getParameter("note"));
            String commentaire = request.getParameter("commentaire");
            String cours = request.getParameter("cours");
            if ((note < 0) || (note > 10)) {
                //retourne au formulaire
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?vue=evaluationLivre");
                r.forward(request, response);
            }
            if ("Général".equals(cours)) {
                evaluationDAO uneEvaluationDAO = new evaluationDAO(Connexion.getInstance());
                evaluation uneEvaluation = new evaluation((String)session.getAttribute("connection"), (String)session.getAttribute("ISBN"), note, commentaire);
                uneEvaluationDAO.create(uneEvaluation);
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?vue=main");
                r.forward(request, response);
            } else {
                evaluationcoursDAO uneEvaluationcoursDAO = new evaluationcoursDAO(Connexion.getInstance());
                evaluationcours uneEvaluationcours = new evaluationcours((String)session.getAttribute("connection"), (String)session.getAttribute("ISBN"), cours, note, commentaire);
                uneEvaluationcoursDAO.create(uneEvaluationcours);
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?vue=main");
                r.forward(request, response);
            }
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
