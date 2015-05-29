<%-- 
    Document   : ticketcheckout
    Created on : Feb 27, 2015, 3:50:13 PM
    Author     : Konstantinos Pagonis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ticket Checkout</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <form name="initialPurchase" action="${pageContext.request.contextPath}/PurchaseTicket1" method="post" onsubmit="return checkIfNonZero();">
            <input type="hidden" name="showtime" value="${sessionScope.showtime.getId()}">
        <div class="jumbotron" align="center">
            <h1 style="color:#000">Buy your tickets here!</h1>
            <p>How many tickets?</p>
            <br>
            <div class="container" align="center">
                <div class="col-sm-10">
                    <table class="table" align="center">
                        <tr>
                            <td>
                                <p>Adult Tickets:</p>
                            </td>
                            <td class="ticketNumber">
                                <input name="adultTickets" type="number" id="adultTickets" class="input_txt" min="0" max="10" value="0">
                            </td>
                            <td class="times">x</td>
                            <td class="pricePerTicketAdult" id="adultTicketPrice">
                                10.00
                            </td>
                            <td class="equals">=</td>
                            <td class="total" id="adultTicketTotal">
                                0.00
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>Senior Tickets:</p>
                            </td>
                            <td>
                                <input name="seniorTickets" type="number" id="seniorTickets" class="input_txt" min="0" max="10" value="0">
                            </td>
                            <td class="times">x</td>
                            <td class="pricePerTicketSenior" id="seniorTicketPrice">
                                5.00
                            </td>
                            <td class="equals">=</td>
                            <td class="total" id="seniorTicketTotal">
                                0.00
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>Child Tickets:</p>
                            </td>
                            <td>
                                <input name="childTickets" type="number" id="childTickets" class="input_txt"  min="0" max="10" value="0">
                            </td>
                            <td class="times">x</td>
                            <td class="pricePerTicketChild" id="childTicketPrice">
                                6.00
                            </td>
                            <td class="equals">=</td>
                            <td class="total" id="childTicketTotal">
                                0.00
                            </td>
                        </tr>
                    </table>
                    <button> Next step</button>
                </div>
            </div>
        </div>
        </form>
        <jsp:include page="footer.jsp"></jsp:include>
        <script type="text/javascript">
            function calculatePrice(num, source){
                if(source === "adult"){
                    document.getElementById('adultTicketTotal').innerHTML = (num.value * parseFloat(document.getElementById('adultTicketPrice').innerHTML)).toFixed(2);
                }
                if(source === "senior"){
                    document.getElementById('seniorTicketTotal').innerHTML = (num.value * parseFloat(document.getElementById('seniorTicketPrice').innerHTML)).toFixed(2);
                }
                if(source === "child"){
                    document.getElementById('childTicketTotal').innerHTML = (num.value * parseFloat(document.getElementById('childTicketPrice').innerHTML)).toFixed(2);
                }
            }
        </script>
    </body>
</html>
