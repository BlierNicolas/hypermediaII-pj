<%
    HttpSession objetSession = request.getSession();
    if ( objetSession.getAttribute("connected") != null ) {  //d�j� connect� 
    %>  <jsp:forward page="index.jsp" />    <%  
    }
    String  username = request.getParameter("username");
    if (username == null) username = "";
    else username = username.trim();
%>  
<div>
    <!-- LOGIN -->
    <form action="controleurFrontal?action=login" method="post">
        <h2>Connectez-vous</h2>
        <input type="text" id="inputEmail" name="username" value="<%=username%>" placeholder="Username" required autofocus><br>
        <input type="password" id="inputPassword" name="password" placeholder="Password" required><br>
        <button type="submit">Connexion</button>  
    </form>
</div>