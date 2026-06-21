<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Order Success</title>
<link rel="icon"
      type="image/png"
      href="${pageContext.request.contextPath}/assets/images/logo.png">
<link rel="stylesheet"
      href="assets/css/style.css">

<style>

.success-container{

    max-width:700px;

    margin:100px auto;

    text-align:center;

    padding:50px;

    background:white;

    border-radius:12px;

    box-shadow:
    0 2px 10px rgba(0,0,0,0.08);
}

.success-icon{

    font-size:70px;

    color:green;
}

.success-container h1{

    margin:20px 0;
}

.success-container a{

    display:inline-block;

    margin-top:20px;

    padding:12px 25px;

    background:#111;

    color:white;

    text-decoration:none;

    border-radius:8px;
}

</style>

</head>

<body>

<jsp:include page="components/header.jsp"/>

<div class="success-container">

    <div class="success-icon">

        ✓

    </div>

    <h1>

        Order Placed Successfully

    </h1>

    <p>

        Thank you for shopping with us.

    </p>

    <a href="products">

        Continue Shopping

    </a>

</div>

<jsp:include page="components/footer.jsp"/>

</body>

</html>