<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title>Login</title>
<link rel="icon"
      type="image/png"
      href="${pageContext.request.contextPath}/assets/images/logo.png">
<link rel="stylesheet"
      href="assets/css/style.css">

<link rel="stylesheet"
      href="assets/css/login.css">

</head>

<body>

<jsp:include page="components/header.jsp"/>

<main>

<div class="login-container">

    <form class="login-form"
          action="${pageContext.request.contextPath}/login"
          method="post">

        <h2>Login</h2>
        <%
        String success =
        request.getParameter("success");

        if("registered".equals(success)){
        %>

      <p class="success-message">

       Registration Successful.
       Please Login.

</p>

<%
}
%>

        <p class="error">
            ${error}
        </p>

        <input type="email"
               name="email"
               placeholder="Email Address"
               required>

        <input type="password"
               name="password"
               placeholder="Password"
               required>

        <button type="submit">

            Login

        </button>

        <p class="register-link">

            Don't have an account?

            <a href="${pageContext.request.contextPath}/register">

                Register Here

            </a>

        </p>

    </form>

</div>

</main>

<jsp:include page="components/footer.jsp"/>

</body>

</html>