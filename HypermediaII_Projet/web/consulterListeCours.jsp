<%@page import="com.projet.enties.evaluationcours"%>
<%@page import="com.projet.dao.evaluationcoursDAO"%>
<%@page import="com.projet.enties.cours"%>
<%@page import="java.util.List"%>
<%@page import="com.projet.dao.coursDAO"%>
<%@page import="com.projet.servlet.Connexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    coursDAO unCoursDAO = new coursDAO(Connexion.getInstance());
    List<cours> listeCours = unCoursDAO.findAll();
    evaluationcoursDAO uneEvaluationcoursDAO = new evaluationcoursDAO(Connexion.getInstance());
    List<evaluationcours> listeEvaluationCours = uneEvaluationcoursDAO.findNoteDesc();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="index.jsp">Retour</a>
        <h1>Liste des cours</h1>
        <form action="controleurFrontal?action=rechercherCours">
            <label>Chercher un cours: </label>
            <select name="cours">
                <% for (int i=0; i<listeCours.size()-1 ; i++) { %>
                    <option>
                        <%= listeCours.get(i).getNom() %>
                    </option>
                <% } %>
            </select>
            <!--<a href="controleurFrontal?action=rechercherCours">Rechercher</a>-->
            <button type="submit">Rechercher</button><br/>
        </form>
    <table>
        <tr>
            <th>Numéro</th>
            <th>Nom</th>
            <th>Livres du cours</th>
        </tr>
        <% for (int i=0; i<listeCours.size(); i++) { %>
        <tr>
            <td>
                <%= listeCours.get(i).getNumero() %>
            </td>
            <td>
                <%= listeCours.get(i).getNom() %>
            </td>
            <td>
                <% for (int j=0; j<listeEvaluationCours.size(); j++) {
                    if (listeEvaluationCours.get(j).getIdCours().equals(listeCours.get(i).getNumero())) { %>
                <ul>
                    <li><%= listeEvaluationCours.get(j).getIdLivre() %> <a href="controleurFrontal?action=evaluerLivre&ISBN=<%= listeEvaluationCours.get(j).getIdLivre() %>">Évaluer ce livre</a></li>
                </ul>
                <%      }
                    } %>
            </td>
        </tr>
        <% } %>
    </table>
    </body>
</html>
