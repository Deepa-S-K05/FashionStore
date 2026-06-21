<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.fashionstore.model.Order"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>My Orders</title>
<link rel="icon"
      type="image/png"
      href="${pageContext.request.contextPath}/assets/images/logo.png">
<link rel="stylesheet"
      href="assets/css/style.css">

<link rel="stylesheet"
      href="assets/css/my-orders.css">

</head>

<body>

<jsp:include page="components/header.jsp"/>

<%
List<Order> orders =
        (List<Order>)
        request.getAttribute(
                "orders");
%>

<div class="orders-container">

    <h1>

        My Orders

    </h1>

    <%
    if(orders != null &&
       !orders.isEmpty()) {

        for(Order order : orders) {
    %>

    <div class="order-card">

        <div class="order-header">

            <div>

                <strong>

                    Order #<%=order.getOrderId()%>

                </strong>

            </div>

            <div class="status">

                <%=order.getOrderStatus()%>

            </div>

        </div>

        <div class="order-body">

            <p>

                Order Date:
                <%=order.getOrderDate()%>

            </p>

            <p>

                Total Amount:
                Rs. <%=order.getTotalAmount()%>

            </p>

        </div>
        <a href="${pageContext.request.contextPath}/order-details?orderId=<%=order.getOrderId()%>"
         class="view-order-btn">

        View Details

</a>

    </div>

    <%
        }
    } else {
    %>

    <div class="empty-orders">

        <h2>

            No Orders Found

        </h2>

        <a href="products">

            Start Shopping

        </a>

    </div>

    <%
    }
    %>

</div>

<jsp:include page="components/footer.jsp"/>

</body>

</html>