<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.fashionstore.model.Order"%>
<%@ page import="com.fashionstore.model.OrderItem"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Order Details</title>
<link rel="icon"
      type="image/png"
      href="${pageContext.request.contextPath}/assets/images/logo.png">
<link rel="stylesheet"
      href="assets/css/style.css">

<link rel="stylesheet"
      href="assets/css/order-details.css">

</head>

<body>

<jsp:include page="components/header.jsp"/>

<%
Order order =
        (Order)request.getAttribute(
                "order");

List<OrderItem> items =
        (List<OrderItem>)
        request.getAttribute(
                "items");
%>

<div class="order-details-container">

    <h1>

        Order #<%=order.getOrderId()%>

    </h1>

    <div class="order-summary">

        <p>

            Status:
            <%=order.getOrderStatus()%>

        </p>

        <p>

            Total:
            Rs. <%=order.getTotalAmount()%>

        </p>

    </div>

    <h2>

        Ordered Items

    </h2>

    <%
for(OrderItem item : items){
%>

<div class="item-card">

    <img
        class="item-image"
        src="assets/images/<%=item.getImageUrl()%>"
        alt="<%=item.getProductName()%>">

    <div class="item-info">

        <h2>
            <%=item.getProductName()%>
        </h2>

        <p>
            Brand:
            <%=item.getBrand()%>
        </p>

        <p>
            Size:
            <%=item.getSize()%>
        </p>

        <p>
            Quantity:
            <%=item.getQuantity()%>
        </p>

        <h3>
            Rs. <%=item.getPrice()%>
        </h3>

    </div>

</div>

<%
}
%>

</div>

<jsp:include page="components/footer.jsp"/>

</body>

</html>