<%-- 
    Document   : checkbalancegiftcard
    Created on : Mar 1, 2015, 2:30:41 PM
    Author     : zeb
--%>

        <jsp:include page="header.jsp"></jsp:include>
        <div class="container">
            <h2><u>Check Your Gift Card Balance</u></h2>
            Please enter your gift card number, claim code, or promo code.
            <c:choose>
                <c:if test="${GiftCardBalance.getBalance()>0}">
                    <h2>Balance: $${GiftCardBalance.getBalance()}</h2>
                </c:if>
            </c:choose>
            <form method="post" action="CheckBalance">
                
                <label> Card Number On Code </label>
                <br>
                <div class="form-group  col-sm-4">
                <input type="number" class="form-control" name="number" min="1000000000" max="2000000000" required="required" >
                <button type="submit" name="submit" class="btn btn-primary btn-lg" required="required">Continue</button>
                </div>
            

               
            </form> 
            
            
            
        </div>
        
         <jsp:include page="footer.jsp"></jsp:include>
