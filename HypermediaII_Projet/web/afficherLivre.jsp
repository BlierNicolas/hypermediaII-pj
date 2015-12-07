<%@page import="com.projet.enties.livre"%>
<%@page import="com.projet.dao.livreDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.projet.servlet.Connexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/consulterLivre.css" />
<%
    livreDAO unLivreDAO = new livreDAO(Connexion.getInstance());
    livre unLivre = unLivreDAO.read((String)session.getAttribute("ISBN"));
    List<livre> listelivre = unLivreDAO.findAll();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Affichage du livre [ <%= (String)session.getAttribute("ISBN") %> ]</h1>
         <a href="index.jsp">Retour</a>
       <p>
                ISBN: <a><%= unLivre.getISBN()%></a><br>
                Titre: <a><%= unLivre.getTitre()%></a><br>
                Nom Auteur: <a><%= unLivre.getNomAuteur() %></a><br>
                Edition: <a><%= unLivre.getEdition() %></a><br>
                Description: <a><%= unLivre.getDescription() %></a><br>              
                Mot Cles: <a><%= unLivre.getMotCles() %></a><br>          
                Annee: <a><%= unLivre.getAnnee() %></a><br>
                NB Page: <a><%= unLivre.getNbPages() %></a><br>
                Etat:<a><%= unLivre.getEtat() %></a><br>
                Note: <a><%= unLivre.getNote() %></a><br>
        </p>
    </body>
</html>
