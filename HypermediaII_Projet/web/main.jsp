<% HttpSession objetSession = request.getSession(); %>
<div>
    <div>
        <div>
            <h1>Bonjour <%= objetSession.getAttribute("connected") %>!</h1>
        </div>               
    </div>
    <div>
        <a href="controleurFrontal?action=logout">Se déconnecter</a>
    </div>
    <div>
        <a href="controleurFrontal?action=evaluer">Evaluer un livre</a>
        <a href="controleurFrontal?action=consulter">Consulter ces evaluations</a>
    </div>
</div>