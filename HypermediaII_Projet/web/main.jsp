<% HttpSession objetSession = request.getSession(); %>
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
<div>
    <div>
                    
    </div>
    <div>
        <h1>Bonjour <%= objetSession.getAttribute("connected") %>!</h1>
        <a href="controleurFrontal?action=logout">Se déconnecter</a> <br>
        <a href="controleurFrontal?action=consulterLivre"> Recherche</a>
    </div>
    <div>
        <jsp:include page="afficherListeLivres.jsp" />
        <jsp:include page="consulterListeCours.jsp" />
    </div>
</div>