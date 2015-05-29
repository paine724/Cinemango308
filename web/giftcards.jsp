<%-- 
    Document   : giftcards
    Created on : Feb 25, 2015, 7:13:14 PM
    Author     : zeb
--%>

<jsp:include page="header.jsp"></jsp:include>


     <div class="container center_div" align="center">
            <h1 style="color:black">Buy a giftcard today!</h1>
            <div class="btn-group btn-group-vertical center-block">
                <form action="giftcards2.jsp">
                    <button class="btn btn-default" style="width: 50%" name="type" value="email">Email a gift card</button><BR>
                    
                    <!--
                    OR<BR>
                    <button class="btn btn-default" style="width: 50%" name="type" value="mail">Mail a gift card</button>
                    -->
                </form>
            </div>
            <br>
            <div>
                Already have a gift card? <a href="${pageContext.request.contextPath}/CheckBalanceForward">
                    <br>CHECK YOUR BALANCE 
                </a>
            
                    
     </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
