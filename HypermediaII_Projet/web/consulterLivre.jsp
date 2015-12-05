<%-- 
    Document   : consulterLivre
    Created on : 2015-12-02, 13:22:10
    Author     : Jerome
--%>

<%@page import="com.projet.enties.livre"%>
<%@page import="com.projet.dao.livreDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.projet.servlet.Connexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <h1>Consulter livre [ <%= (String)session.getAttribute("ISBN") %> ]</h1>
        
        <TABLE>
            <TR>
                <TH>ISBN</TH>
                <TH>Titre</TH>
                <TH>Nombre d'evaluation</TH>
                <TH>Moyenne</TH>
                <TH>Evaluation</TH>
            </TR>
                
                <TR>
                    <TD>    
                       <%= unLivre.getISBN()%></a>
                      
                    </TD>
                       
                </TR>
             
        </TABLE>
    </body>
</html>
