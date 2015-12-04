<% HttpSession objetSession = request.getSession(); %>
<div>
    <div>
                    
    </div>
    <div>
         <h1>Bonjour <%= objetSession.getAttribute("connected") %>!</h1>
        <a href="controleurFrontal?action=logout">Se déconnecter</a>
    </div>
    <div>
        <a href="controleurFrontal?action=evaluerLivre&ISBN=978-1-4302-2889-9">Evaluer un livre</a> <!-- Envoie temporairement vers l'ISBN du premier livre à cause du test -->
        <a href="controleurFrontal?action=consulter">Consulter ces evaluations</a>
         <jsp:include page="afficherListeLivres.jsp" />
    </div>
</div>