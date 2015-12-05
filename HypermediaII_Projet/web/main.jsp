<% HttpSession objetSession = request.getSession(); %>
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
<div>
    <div>
                    
    </div>
    <div>
        <h1>Bonjour <%= objetSession.getAttribute("connected") %>!</h1>
        <a href="controleurFrontal?action=logout">Se déconnecter</a>
    </div>
    <div>
        <a href="controleurFrontal?action=consulter">Consulter ces evaluations</a>
        <jsp:include page="afficherListeLivres.jsp" />
    </div>
</div>