package com.projet.servlet;

import com.projet.dao.evaluationDAO;
import com.projet.dao.evaluationcoursDAO;
import com.projet.dao.livreDAO;
import com.projet.dao.userDAO;
import com.projet.enties.evaluation;
import com.projet.enties.evaluationcours;
import com.projet.enties.livre;
import com.projet.enties.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String u = request.getParameter("username");
            String p = request.getParameter("password");
            try {
                Class.forName( request.getServletContext().getInitParameter("jdbcDriver"));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Connexion.setUrl(request.getServletContext().getInitParameter("databaseURL"));
            userDAO unUserDAO = new userDAO(Connexion.getInstance());
            user unUser = unUserDAO.read(u.trim());
            if (unUser==null) {
                //Utilisateur inexistant
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?vue=login");
                r.forward(request, response);
            } else if (!unUser.getPassword().equals(p)) {
                //Mot de passe incorrect
                request.setAttribute("message-warning", "Mot de passe incorrect.");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?vue=login");
                r.forward(request, response);
            } else {
                //connexion OK
                HttpSession session = request.getSession(true);
                session.setAttribute("connected", unUser.getUsername());
                
                livreDAO unLivreDAO = new livreDAO(Connexion.getInstance());
                List<livre> uneListeLivre = unLivreDAO.findAll();
                evaluationDAO uneEvaluationDAO = new evaluationDAO(Connexion.getInstance());
                List<evaluation> uneListeEvaluation = uneEvaluationDAO.findAll();
                evaluationcoursDAO uneEvaluationcoursDAO = new evaluationcoursDAO(Connexion.getInstance());
                List<evaluationcours> uneListeEvaluationcours = uneEvaluationcoursDAO.findAll();
                for (int i=0; i<uneListeLivre.size(); i++) {
                    double total = 0;
                    uneListeLivre.get(i).setNbEvaluations(0);
                    for (int j=0; j<uneListeEvaluation.size(); j++) {
                        if (uneListeEvaluation.get(j).getIdLivre().equals(uneListeLivre.get(i).getISBN())) {
                            uneListeLivre.get(i).setNbEvaluations(uneListeLivre.get(i).getNbEvaluations()+1);
                            total = total + uneListeEvaluation.get(j).getNote();
                            uneListeLivre.get(i).setNote(total/uneListeLivre.get(i).getNbEvaluations());
                            unLivreDAO.update(uneListeLivre.get(i));
                        }
                    }
                    for (int j=0; j<uneListeEvaluationcours.size(); j++) {
                        if (uneListeEvaluationcours.get(j).getIdLivre().equals(uneListeLivre.get(i).getISBN())) {
                            uneListeLivre.get(i).setNbEvaluations(uneListeLivre.get(i).getNbEvaluations()+1);
                            total = total + uneListeEvaluationcours.get(j).getNote();
                            uneListeLivre.get(i).setNote(total/uneListeLivre.get(i).getNbEvaluations());
                            unLivreDAO.update(uneListeLivre.get(i));
                        }
                    }
                }
                
                request.setAttribute("vue", "main");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?vue=main");
                r.forward(request, response);
                out.println();
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
