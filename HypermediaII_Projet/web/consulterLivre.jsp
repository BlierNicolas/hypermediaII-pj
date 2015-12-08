<%@page import="com.projet.enties.livre"%>
<%@page import="com.projet.dao.livreDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.projet.servlet.Connexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/consulterLivre.css" />-->
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
        <h1>Recherche</h1>
         <a href="index.jsp">Retour</a>
          <form action="controleurFrontal?action=rechercherLivre">
            <p>
                <select name="Element">
                    <option>ISBN</option>
                    <option>Titre</option>
                    <option>Description</option>
                    <option>Mot-cles</option>   
                </select>
            </p>
            <button type="submit">Rechercher</button><br/>
          </form>>
    </body>
</html>
