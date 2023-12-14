<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!doctype html>
        <html lang="en">

        <head>
            <!-- Required meta tags -->
            <meta charset="utf-8">
            <meta ̦name="viewport" content="width=device-width, initial-scale=1">

            <link
              href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css"
              rel="stylesheet"
            />


            <title>ATM Client</title>
        </head>

        <body>

            <%@ include file="navbar.jsp" %>

                <div class="container">
                    <h1 class="mt-2">Fast Withdraw Cash</h1>

                    <div class="row">
                        <div class="col-md-4 mt-4">
                            <form action="#" method="post">
                            <c:if test="${showAlert}">
                                <div class="mx-5 mt-2">
                                    <div class="alert alert-warning" role="alert">
                                        <div>${message}</div>
                                    </div>
                                </div>
                            </c:if>

                                <div>
                                    <label>Amount</label><br>
                                    <select name="balance" class="w-100 form-control" required>
                                        <option value="500">500</option>
                                        <option value="1000">1000</option>
                                        <option value="1500">1500</option>
                                        <option value="2000">2000</option>
                                        <option value="2500">2500</option>
                                        <option value="3000">3000</option>
                                        <option value="4000">4000</option>
                                    </select>

                                </div>
                                <div>
                                    <label>PIN</label><br>
                                    <input class="w-100 form-control" type="password" minlength="4" maxlength="4" name="atmPin" required>
                                </div>

                                <button class="mt-4 btn btn-primary">Fast Withdraw Cash</button>
                            </form>
                        </div>
                    </div>
                </div>


        </body>

        </html>