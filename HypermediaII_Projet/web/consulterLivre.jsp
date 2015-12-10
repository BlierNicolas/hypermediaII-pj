<%@page import="com.projet.enties.livre"%>
<%@page import="com.projet.dao.livreDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.projet.servlet.Connexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/consulterLivre.css" />-->
<%
    livreDAO unLivreDAO = new livreDAO(Connexion.getInstance());
    livre unLivre = unLivreDAO.read((String)session.getAttribute("ISBN"));
    List<livre> listelivre = (List<livre>)session.getAttribute("listeLivre");
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
          <form action="controleurFrontal">
              <input type="hidden" name="action" value="rechercherLivre">
              <p>
                <input type = "text" name = "Recherche">
                <select name="Element">
                    <option>ISBN</option>
                    <option>Titre</option>
                    <option>Description</option>
                    <option>Mot-cles</option>  
                </select>
                <button type="submit">Rechercher</button><br/>
              </p>
          </form>
          
          <TABLE>
            <TR>
                <TH>ISBN</TH>
                <TH>Titre</TH>
                <TH>Description</TH>
                <TH>Mot-Cle</TH>
                <TH>Evaluation</TH>
                <TH>Afficher</TH>
            </TR>
    <%  if (listelivre != null) {
            if (listelivre.size() != 0) {
                for (int i=0; i<listelivre.size(); i++) { %>
                    <TR>
                        <TD>    
                            <a href="controleurFrontal?action=afficherLivre&ISBN=<%= listelivre.get(i).getISBN()%>"><%= listelivre.get(i).getISBN()%></a>
                        </TD>
                        <TD>
                            <%= listelivre.get(i).getTitre()%>
                        </TD>
                        <TD>
                            <%= listelivre.get(i).getDescription() %>
                        </TD>
                        <TD>
                            <%= listelivre.get(i).getMotCles() %>
                        </TD>
                        <TD>
                            <a href="controleurFrontal?action=evaluerLivre&ISBN=<%= listelivre.get(i).getISBN()%>">Évaluer ce livre</a>
                        </TD>
                        <td>
                            <a href="controleurFrontal?action=afficherLivre&ISBN=<%= listelivre.get(i).getISBN()%>">Affciher le livre</a>
                            </td>
                        </TR>
            <%  }
            } 
        } %>
</TABLE>
    </body>
</html>
