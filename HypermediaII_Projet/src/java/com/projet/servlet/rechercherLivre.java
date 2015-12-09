package com.projet.servlet;

import com.projet.dao.livreDAO;
import com.projet.enties.livre;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class rechercherLivre extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            HttpSession session = request.getSession(true);
            session.setAttribute("Element", request.getParameter("Element"));
            String Element = request.getParameter("Element");
            String Recherche = request.getParameter("Recherche");
            if(Element.equals("ISBN"))
            {
                livreDAO unLivreDAO = new livreDAO(Connexion.getInstance());
                List<livre> listeLivre = unLivreDAO.findByISBN(Recherche);
                session.setAttribute("listeLivre", listeLivre);
            }else if(Element.equals("Titre"))
            {
                livreDAO unLivreDAO = new livreDAO(Connexion.getInstance());
                List<livre> listeLivre = unLivreDAO.findByTitre(Recherche);
                session.setAttribute("listeLivre", listeLivre);
            }else if(Element.equals("Description"))
            {
                livreDAO unLivreDAO = new livreDAO(Connexion.getInstance());
                List<livre> listeLivre = unLivreDAO.findByDesc(Recherche);
                session.setAttribute("listeLivre", listeLivre);
            }else if(Element.equals("Mot-cles"))
            {
                livreDAO unLivreDAO = new livreDAO(Connexion.getInstance());
                List<livre> listeLivre = unLivreDAO.findByMotsCles(Recherche);
                session.setAttribute("listeLivre", listeLivre);
            }
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?vue=consulterLivre");
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
