<%-- 
    Document   : ticketcheckout2
    Created on : Feb 27, 2015, 4:55:33 PM
    Author     : Konstantinos Pagonis
--%>
<jsp:include page="header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form name="secondaryPurchase" method="post" action="TicketPurchase">

    <div class="container" align="center">
        <h1 style="color:#000">Almost There</h1>
        <div class="col-sm-12 col-md-12 col-lg-12">
            <div class = "col-sm-6 col-sm-offset-3">
                <h2>Payment</h2>
                <label><input type="radio" name="purchaseRadio" id="cardOption" onclick="switchToCard()" checked value="creditcard">Purchase with credit/debit card.</label>
                    <c:choose>
                        <c:when test="${userinfobean.getUserId() > 0}">
                        <BR><select name="preselectedCC" onchange="disableCard(this.selectedIndex)" id="preselctedCC">
                            <option value="0" selected>Select a card</option>
                            <c:forEach var="cc" items="${cclist}">
                                <option value="${cc.getCreditcardID()}">${cc.getCardNumber()}</option>
                            </c:forEach>
                        </select>
                    </c:when>
                </c:choose>
                <br>
                <label>Card Number:</label>
                <input name="cardNumber" type="number" id="cardNumber" class="form-control" required="required" enabled>
                <label>Expiration Date:</label>
                <select class="form-control" id="monthSelect" enabled name="month">
                    <option value="01">January</option>
                    <option value="02">February</option>
                    <option value="03">March</option>
                    <option value="04">April</option>
                    <option value="05">May</option>
                    <option value="06">June</option>
                    <option value="07">July</option>
                    <option value="08">August</option>
                    <option value="09">September</option>
                    <option value="10">October</option>
                    <option value="11">November</option>
                    <option value="12">December</option>
                </select>
                <select class="form-control" id="yearSelect" enabled name="year">
                    <option value="2015">2015</option>
                    <option value="2016">2016</option>
                    <option value="2017">2017</option>
                    <option value="2018">2018</option>
                    <option value="2019">2019</option>
                    <option value="2020">2020</option>
                </select>
                <label>Security Code:</label>
                <input type="number" id="seccode" name="seccode" class="form-control" required="required" enabled>
                <label>First Name:</label>
                <input type="text" id="firstName" name="firstname" class="form-control" required="required" enabled>
                <label>Last Name:</label>
                <input type="text" name="lastname" id="lastName" class="form-control" required="required" enabled>
                <label>Billing Zip Code:</label>
                <input type="number" name="zipcode" id="zipCode" class="form-control" required="required" enabled>    
                <br>
                <label><input type="radio" name="purchaseRadio" id="giftCardOption" onclick="switchToGiftcard()" value="giftcard">Purchase with giftcard.</label>
                <br>
                <label>Gift Card Number:</label>
                <input type="number" id="giftcardNumber" class="form-control" required="required" disabled name="giftcardnumber">
                <br>
                <button align="center" id="purchaseButton">Purchase My Ticket!</button>
            </div>
        </div>
    </div>
</form>
<script>
    function disableCard(x) {
        if (x == 0) {
            switchToCard();
        } else {
            document.getElementById("cardNumber").disabled = true;
            document.getElementById("cardNumber").value = "";
            document.getElementById("monthSelect").disabled = true;
            document.getElementById("monthSelect").value = "";
            document.getElementById("yearSelect").disabled = true;
            document.getElementById("yearSelect").value = "";
            document.getElementById("firstName").disabled = true;
            document.getElementById("firstName").value = "";
            document.getElementById("lastName").disabled = true;
            document.getElementById("lastName").value = "";
            document.getElementById("zipCode").disabled = true;
            document.getElementById("zipCode").value = "";
            document.getElementById("seccode").disabled = true;
            document.getElementById("seccode").value = "";
        }
    }
    function switchToCard() {
        document.getElementById("cardNumber").disabled = false;
        document.getElementById("monthSelect").disabled = false;
        document.getElementById("yearSelect").disabled = false;
        document.getElementById("firstName").disabled = false;
        document.getElementById("lastName").disabled = false;
        document.getElementById("zipCode").disabled = false;
        document.getElementById("seccode").disabled = false;
        document.getElementById("preselctedCC").disabled = false;

        document.getElementById("giftcardNumber").disabled = true;
        document.getElementById("giftcardNumber").value = "";
    }
    function switchToGiftcard() {
        document.getElementById("cardNumber").disabled = true;
        document.getElementById("cardNumber").value = "";
        document.getElementById("monthSelect").disabled = true;
        document.getElementById("monthSelect").value = "";
        document.getElementById("yearSelect").disabled = true;
        document.getElementById("yearSelect").value = "";
        document.getElementById("firstName").disabled = true;
        document.getElementById("firstName").value = "";
        document.getElementById("lastName").disabled = true;
        document.getElementById("lastName").value = "";
        document.getElementById("zipCode").disabled = true;
        document.getElementById("zipCode").value = "";
        document.getElementById("seccode").disabled = true;
        document.getElementById("seccode").value = "";
        document.getElementById("preselctedCC").disabled = true;

        document.getElementById("giftcardNumber").disabled = false;
    }
</script>
<jsp:include page="footer.jsp"></jsp:include>

