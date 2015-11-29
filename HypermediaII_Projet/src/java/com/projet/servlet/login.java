package com.projet.servlet;

import com.projet.dao.userDAO;
import com.projet.enties.user;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String  u = request.getParameter("username");
            String p = request.getParameter("password");  
            if (u == null || u.trim().equalsIgnoreCase("")) {
                request.setAttribute("message-warning", "Username obligatoire");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?thePage=login");
                r.forward(request, response);
                return;
            }
            if( p == null || p.trim().equals("") ) {
                request.setAttribute("message-warning", "Mot de passe obligatoire");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?thePage=login");
                r.forward(request, response);
                return;
            }
            
            Connexion.setUrl(this.getServletContext().getInitParameter("databaseURL"));
            userDAO unUserDAO = new userDAO(Connexion.getInstance());
            user unUser = unUserDAO.read(u.trim());

            if (unUser==null) {
                //Utilisateur inexistant
                request.setAttribute("message-warning", "Utilisateur { " + u + " } inexistant.");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?thePage=login");
                r.forward(request, response);
            } else if (!unUser.getPassword().equals(p)) {
                //Mot de passe incorrect
                request.setAttribute("message-warning", "Mot de passe incorrect.");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?thePage=login");
                r.forward(request, response);
            } else {
                //connexion OK
                HttpSession session = request.getSession(true);
                session.setAttribute("connected", unUser.getUsername());
                request.setAttribute("vue", "main");
                RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp?thePage=main");
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
