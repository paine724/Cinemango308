<%-- 
    Document   : giftcards2
    Created on : Mar 1, 2015, 2:51:56 PM
    Author     : Andrew
--%>

<jsp:include page="header.jsp"></jsp:include>
    <div class="form-group" align="center">
        <h2>Select a card value:</h2>
    <form action="EmailGiftcard" method="post">
    <select name="cost">
        <option value="10">$10</option>
        <option value="20">$20</option>
        <option value="50">$50</option>
        <option value="100">$100</option>
        <option value="150">$150</option>
    </select>
    <BR>

        Input email:<input type="email" name="email">
    <br>
            <div class="form-group">
                <label for="To">To:</label>
                <input name="to" type="text" class="form-control" id="to" maxlength="10">
            </div>
            <div class="form-group">
                <label for="From">From:</label>
                <input name="from" type="text" class="form-control" id="from">
            </div>
                <div>
                    <label for="quantity">Select Quantity</label>
                    <select class="form-control" id="quantity" name="quantity">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                    <option>6</option>
                    <option>7</option>
                    <option>8</option>
                    <option>9</option>
                    <option>10</option>
                    </select>
                </div>
                <div>
                    <label for="message">Leave a message</label>
                    <div>
                    <textarea class="form-group" id="message" name="message"></textarea>
                    </div>
                </div>
            <input type="submit" value="Purchase">
    </form>
    </div>
<jsp:include page="footer.jsp"></jsp:include>