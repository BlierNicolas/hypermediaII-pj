package com.projet.servlet;

import com.projet.dao.coursDAO;
import com.projet.dao.evaluationDAO;
import com.projet.dao.evaluationcoursDAO;
import com.projet.dao.livreDAO;
import com.projet.enties.cours;
import com.projet.enties.evaluation;
import com.projet.enties.evaluationcours;
import com.projet.enties.livre;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
            livreDAO unLivreDAO = new livreDAO(Connexion.getInstance());
            livre unLivre = unLivreDAO.read((String)session.getAttribute("ISBN"));
            double total = unLivre.getNbEvaluations() * unLivre.getNote();
            unLivre.setNbEvaluations(unLivre.getNbEvaluations()+1);
            total = total + note;
            unLivre.setNote(total/unLivre.getNbEvaluations());
            unLivreDAO.update(unLivre);
            if ("Général".equals(cours)) {
                evaluationDAO uneEvaluationDAO = new evaluationDAO(Connexion.getInstance());
                evaluation uneEvaluation = new evaluation((String)session.getAttribute("connected"), (String)session.getAttribute("ISBN"), note, commentaire);
                uneEvaluationDAO.create(uneEvaluation);
            } else {
                evaluationcoursDAO uneEvaluationcoursDAO = new evaluationcoursDAO(Connexion.getInstance());
                coursDAO unCoursDAO = new coursDAO(Connexion.getInstance());
                cours unCours = unCoursDAO.readByNom(cours);
                evaluationcours uneEvaluationcours = new evaluationcours((String)session.getAttribute("ISBN"), (String)session.getAttribute("connected"), unCours.getNumero(), note, commentaire);
                uneEvaluationcoursDAO.create(uneEvaluationcours);
            }
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?vue=main");
            r.forward(request, response);
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
