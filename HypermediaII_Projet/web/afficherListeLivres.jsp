<%@page import="com.projet.dao.livreDAO"%>
<%@page import="com.projet.enties.livre"%>
<%@page import="java.util.List"%>
<%@page import="com.projet.servlet.Connexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%
    livreDAO unlivreDAO = new livreDAO(Connexion.getInstance());
    List<livre> listeLivre = unlivreDAO.findAll();
%>
<HTML>
    <HEAD>
        <TITLE>Liste de Livres </TITLE>
    </HEAD>
    <BODY>
        <H1>Liste de Livres </H1>
        <TABLE>
            <TR>
                <TH>ISBN</TH>
                <TH>Titre</TH>
                <TH>Nombre d'evaluation</TH>
                <TH>Moyenne</TH>
                <TH>Evaluation</TH>
            </TR>
                <% for (int i=0; i<listeLivre.size(); i++) { %>
                <TR>
                    <TD>
                        <%= listeLivre.get(i).getISBN()%>
                    </TD>
                    <TD>
                        <%= listeLivre.get(i).getTitre()%>
                    </TD>
                    <TD>
                        <%= listeLivre.get(i).getNbEvaluations()%>
                    </TD>
                    <TD>
                        <%= listeLivre.get(i).getNote()%>
                    </TD>
                    <TD>
                        <a href="controleurFrontal?action=evaluerLivre&ISBN=<%= listeLivre.get(i).getISBN()%>">Évaluer ce livre</a>
                    </TD>
                  
                </TR>
                <% } %>
        </TABLE>
    </BODY>
</HTML>

          