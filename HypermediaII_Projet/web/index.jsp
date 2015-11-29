<%  
    HttpSession objetSession = request.getSession();
    String vue;
    String css = request.getParameter("vue");
    if (request.getParameter("vue") != null) {
        vue = request.getParameter("vue") + ".jsp";
    } 
    else {
        vue = "main.jsp";
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Projet de session</title>	
    </head>
    <body>
        <div>
        <%  
            if( objetSession.getAttribute("connected") == null ) {
        %>      <jsp:include page="login.jsp" /> <%
            } else if(vue == null) {
        %>      <jsp:include page="main.jsp"/> 
        <%  } else {
        %>      <jsp:include page="<%=vue%>"/>      
        <%  }
        %>
        </div>
    </body>
</html>