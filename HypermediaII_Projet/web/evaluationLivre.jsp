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
        <h1>Evaluer livre [ <%= (String)session.getAttribute("ISBN") %> ]</h1>
        <form  action="controleurFrontal" method="post">
            <input type="hidden" name="action" value="confirmationEvaluationLivre">
            <table>
                <tr>
                    <td><label>Note : </label></td>
                    <td><input type="number" min="0" max="10" name="note"></td>
                </tr>
                <tr>
                    <td><label>Commentaire : </label></td>
                    <td><textarea rows="4" name="commentaire"></textarea></td>
                </tr>
                <tr>
                    <td><label>Nom du cours: </label></td>
                    <td>
                        <select name="cours">
                            <option selected="selected">
                                Général
                            </option>
                            <% for (int i=0; i<listeCours.size()-1 ; i++) { %>
                                <option>
                                    <%= listeCours.get(i).getNom() %>
                                </option>
                            <% } %>
                        </select>
                    </td>
                </tr>
            </table>
            <button type="submit">Envoyer</button>
        </form>
    </body>
</html>
