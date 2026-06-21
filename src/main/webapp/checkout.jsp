<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.fashionstore.model.CartItem"%>
<%@ page import="com.fashionstore.model.User"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Checkout</title>
<link rel="icon"
      type="image/png"
      href="${pageContext.request.contextPath}/assets/images/logo.png">

<link rel="stylesheet"
      href="assets/css/style.css">

<link rel="stylesheet"
      href="assets/css/checkout.css">

</head>

<body>

<jsp:include page="components/header.jsp"/>

<%
User user =
        (User)session.getAttribute(
                "loggedInUser");

List<CartItem> cartItems =
        (List<CartItem>)
        request.getAttribute(
                "cartItems");

double total = 0;
%>

<div class="checkout-container">

    <div class="checkout-left">

        <div class="address-card">

            <h2>

                Delivery Address

            </h2>

            <p>

                <strong>
                <%=user.getFirstName()%>
                <%=user.getLastName()%>
                </strong>

            </p>

            <p>
                <%=user.getPhone()%>
            </p>

            <p>
                <%=user.getAddressLine1()%>
            </p>

            <p>
                <%=user.getAddressLine2()%>
            </p>

            <p>
                <%=user.getCity()%>,
                <%=user.getState()%>
            </p>

            <p>
                <%=user.getPincode()%>
            </p>

            <p>
                <%=user.getCountry()%>
            </p>

        </div>

        <div class="payment-card">

            <h2>

                Payment Method

            </h2>

            <label>

                <input type="radio"
                       checked>

                Cash On Delivery

            </label>

        </div>

    </div>

    <div class="checkout-right">

        <div class="summary-card">

            <h2>

                Order Summary

            </h2>

            <%
            for(CartItem item :
                    cartItems){

                total +=
                    item.getSubtotal();
            %>

            <div class="summary-item">

                <span>

                    <%=item.getProductName()%>

                    ( <%=item.getSize()%> )

                    ×

                    <%=item.getQuantity()%>

                </span>

                <span>

                    Rs.
                    <%=item.getSubtotal()%>

                </span>

            </div>

            <%
            }
            %>

            <hr>

            <div class="summary-total">

                <span>

                    Total

                </span>

                <span>

                    Rs.
                    <%=total%>

                </span>

            </div>

            <form action="place-order"
                  method="post">

                <button
                    class="place-order-btn">

                    Place Order

                </button>

            </form>

        </div>

    </div>

</div>

<jsp:include page="components/footer.jsp"/>

</body>

</html>