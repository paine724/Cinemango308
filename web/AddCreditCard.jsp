<%-- 
    Document   : AddCreditCard
    Created on : Apr 9, 2015, 5:20:35 PM
    Author     : Philip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <body>
        <div class="container">
            <br>
            <h2>${ErrorMsg.getErrorMsg()}</h2>
            <form class="form-horizontal" method="post" action="AddCreditCard">
        <fieldset>

        <!-- Form Name -->
        <legend>Add A Credit Card</legend>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-4 control-label" for="creditcardnumber">Credit Card Number</label>  
          <div class="col-md-6">
          <input class="inputCard" type="number" min="1000" max="9999" name="creditcard1" id="creditcard1" required/>
            -
            <input class="inputCard" type="number" min="1000" max="9999" name="creditcard2" id="creditcard2" required/>
            -
            <input class="inputCard" type="number" min="1000" max="9999" name="creditcard3" id="creditcard3" required/>
            -
            <input class="inputCard" type="number" min="1000" max="9999"  name="creditcard4" id="creditcard4" required/>
            <br />

          </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-4 control-label" for="firstname">First Name</label>  
          <div class="col-md-4">
          <input id="firstname" name="firstname" type="text" placeholder="" class="form-control input-large" required="">

          </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-4 control-label" for="middleinitial">Middle Initial</label>  
          <div class="col-md-1">
          <input id="middleinitial" name="middleinitial" type="text" placeholder="" class="form-control input-md">

          </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-4 control-label" for="lastname">Last Name</label>  
          <div class="col-md-4">
          <input id="lastname" name="lastname" type="text" placeholder="" class="form-control input-large" required="">

          </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-4 control-label" for="expdate">Expiration Date</label>  
          <div class="col-md-2">
          <input id="expdate" name="expdate" type="text" placeholder="yyyy/mm" class="form-control input-md" required="">

          </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-4 control-label" for="securitycode">Security Number</label>  
          <div class="col-md-2">
          <input id="securitynumber" name="securitycode" type="text" placeholder="" class="form-control input-md" required="">

          </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-4 control-label" for="zipcode">Zip Code</label>  
          <div class="col-md-4">
          <input id="zipcode" name="zipcode" type="text" placeholder="" class="form-control input-md" required="">

          </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-4 control-label" for="cardtype">Card Type</label>  
          <div class="col-md-4">
          <input id="cardtype" name="cardtype" type="text" placeholder="" class="form-control input-large" required="">

          </div>
        </div>
            <!-- Button -->
        <div class="form-group">
          <label class="col-md-4 control-label" for="submit"></label>
          <div class="col-md-4">
            <button id="submit" name="submit" class="btn btn-primary">Submit</button>
          </div>
        </div>
        </fieldset>
        </form>

    </body>
