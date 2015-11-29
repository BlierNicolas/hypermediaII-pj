<% HttpSession objetSession = request.getSession(); %>
<div>
    <div>
        <div>
            <h1>Bonjour <%= objetSession.getAttribute("connected") %>!</h1>
        </div>               
    </div>
    <div>
        <a href="">Se déconnecter</a>
    </div>
    <div>
        <a href="">Evaluer un livre</a>
        <a href="">Consulter ces evaluations</a>
    </div>
</div>