<%@page import="java.util.Iterator"%>
<%@page import="com.projet.enties.cours"%>
<%@page import="java.util.List"%>
<%@page import="com.projet.servlet.Connexion"%>
<%@page import="com.projet.dao.coursDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    coursDAO unCoursDAO = new coursDAO(Connexion.getInstance());
    List<cours> listeCours = unCoursDAO.findAll();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Evaluer un livre</title>
    </head>
    <body>
        <a href="index.jsp">Retour</a>
        <h1>Evaluer un livre</h1>
        <form  action="controleurFrontal?action=evaluerLivre" method="post">
            <label>Note : </label>
            <input type="text"><br>
            <label>Commentaire : </label><br>
            <input type="text"><br>
            <button type="submit">Envoyer</button>
            <label>Nom du cours: </label>
            <select>
                <% for (int i=0; i<listeCours.size()-1 ; i++) { %>
                    <option>
                        <% listeCours.get(i).getNom(); %>
                    </option>
                <% } %>
            </select>
        </form>
    </body>
</html>
