<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.fashionstore.model.CartItem"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>My Cart</title>
<link rel="icon"
      type="image/png"
      href="${pageContext.request.contextPath}/assets/images/logo.png">

<link rel="stylesheet"
      href="assets/css/style.css">

<link rel="stylesheet"
      href="assets/css/cart.css">

</head>

<body>

<jsp:include page="components/header.jsp"/>

<%
List<CartItem> cartItems =
        (List<CartItem>)
        request.getAttribute(
                "cartItems");

double total = 0;
%>

<div class="cart-container">

    <div class="cart-left">

        <h2>My Shopping Cart</h2>

        <%
        if(cartItems != null &&
           !cartItems.isEmpty()){

        for(CartItem item : cartItems){

        total += item.getSubtotal();
        %>

        <div class="cart-item">

            <img
                src="assets/images/<%=item.getImageUrl()%>"
                alt="<%=item.getProductName()%>">

            <div class="item-details">

                <h3>
                    <%=item.getProductName()%>
                </h3>

                <p>
                    Brand:
                    <%=item.getBrand()%>
                </p>

                <p>
                    Size:
                    <%=item.getSize()%>
                </p>

                <p>
                    Price:
                    Rs. <%=item.getPrice()%>
                </p>

         <div class="qty-box">

    <a href="update-cart-quantity?cartId=<%=item.getCartId()%>&quantity=<%=item.getQuantity()%>&action=decrease"
       class="qty-btn">

        -

    </a>

    <span>

        <%=item.getQuantity()%>

    </span>

    <a href="update-cart-quantity?cartId=<%=item.getCartId()%>&quantity=<%=item.getQuantity()%>&action=increase"
       class="qty-btn">

        +

    </a>

</div>

                <a href="remove-cart-item?cartId=<%=item.getCartId()%>"
                   class="remove-btn">

                    Remove

                </a>

            </div>

            <div class="item-total">

                <h3>

                    Rs.
                    <%=item.getSubtotal()%>

                </h3>

            </div>

        </div>

        <%
        }
        } else {
        %>

        <div class="empty-cart">

            <h3>

                Your cart is empty

            </h3>

            <a href="products">

                Continue Shopping

            </a>

        </div>

        <%
        }
        %>

    </div>

    <div class="cart-right">

        <div class="summary-card">

            <h3>

                Order Summary

            </h3>

            <div class="summary-row">

                <span>
                    Subtotal
                </span>

                <span>
                    Rs. <%=total%>
                </span>

            </div>

            <div class="summary-row">

                <span>
                    Shipping
                </span>

                <span>
                    Free
                </span>

            </div>

            <hr>

            <div class="summary-row total">

                <span>
                    Total
                </span>

                <span>
                    Rs. <%=total%>
                </span>

            </div>

            <% if(cartItems != null &&
                  !cartItems.isEmpty()){ %>

<a href="${pageContext.request.contextPath}/checkout"
   class="checkout-btn">

    Proceed To Checkout

</a>

            <a href="products"
               class="continue-btn">

                Continue Shopping

            </a>

            <% } %>

        </div>

    </div>

</div>

<jsp:include page="components/footer.jsp"/>

</body>

</html>