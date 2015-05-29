<%-- 
    Document   : giftcards-p.jsp
    Created on : Mar 1, 2015, 3:32:02 PM
    Author     : Andrew
--%>

<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <h3>You have sent a gift card of $
        <%= request.getParameter("cost") %>
        to
        <% if(request.getParameter("email")!=null){%>
        <%= request.getParameter("email") %>
        <% }else if(request.getParameter("address")!=null){%>
        <%= request.getParameter("address") %>
        <%}%>
    </h3>
</div>
<jsp:include page="footer.jsp"></jsp:include>