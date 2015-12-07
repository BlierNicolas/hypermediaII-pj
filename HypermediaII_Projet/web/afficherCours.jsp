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
    cours unCours = unCoursDAO.readByNom((String)session.getAttribute("cours"));
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
            <label>Affichage du cours [ <%= session.getAttribute("name") %> ]: </label><br/>
            <table>
                <tr>
                    <td>Numéro : </td>
                    <td><%= unCours.getNumero() %></td>
                </tr>
                <tr>
                    <td>Nom : </td>
                    <td><%= unCours.getNom() %></td>
                </tr>
                <tr>
                    <td>Durée : </td>
                    <td><%= unCours.getDuree() %></td>
                </tr>
                <tr>
                    <td>Évaluations : </td>
                    <td>
                        <% for (int i=0; i<listeCours.size(); i++) { %>
                            <%= listeCours.get(i).getNumero() %>
                            <%= listeCours.get(i).getNom() %>
                            <% for (int j=0; j<listeEvaluationCours.size(); j++) {
                                if (listeEvaluationCours.get(j).getIdCours().equals(listeCours.get(i).getNumero())) { %>
                                <ul>
                                    <li><%= listeEvaluationCours.get(j).getIdLivre() %> <a href="controleurFrontal?action=evaluerLivre&ISBN">Évaluer ce livre</a> : <%= listeEvaluationCours.get(i).getNote() %></li>
                                </ul>
                        <%      }
                                } %>
                        <% } %>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
