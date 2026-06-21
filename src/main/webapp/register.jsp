<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<meta name="viewport"
   content="width=device-width, initial-scale=1.0">

<title>Register | Fashion Store</title>
<link rel="icon"
      type="image/png"
      href="${pageContext.request.contextPath}/assets/images/logo.png">
<link rel="stylesheet"
      href="assets/css/style.css">

<link rel="stylesheet"
      href="assets/css/register.css">

</head>

<body>

<jsp:include page="components/header.jsp"/>

<main>

<div class="register-container">

```
<form class="register-form"
      action="${pageContext.request.contextPath}/register"
      method="post">

    <h2>Create Account</h2>

    <%
    String error =
            (String)request.getAttribute(
                    "error");

    if(error != null){
    %>

    <div class="error-box">

        <%=error%>

    </div>

    <%
    }
    %>

    <div class="form-row">

        <input type="text"
               name="firstName"
               value="${param.firstName}"
               placeholder="First Name"
               required>

        <input type="text"
               name="lastName"
               value="${param.lastName}"
               placeholder="Last Name"
               >

    </div>

    <input type="email"
           name="email"
           value="${param.email}"
           placeholder="Email Address"
           required>

    <input type="tel"
           name="phone"
           value="${param.phone}"
           placeholder="Phone Number"
           pattern="[0-9]{10}"
           maxlength="10"
           required>

    <select name="gender"
            required>

        <option value="">
            Select Gender
        </option>

        <option value="Male"
            ${param.gender == 'Male' ? 'selected' : ''}>
            Male
        </option>

        <option value="Female"
            ${param.gender == 'Female' ? 'selected' : ''}>
            Female
        </option>

        <option value="Other"
            ${param.gender == 'Other' ? 'selected' : ''}>
            Other
        </option>

    </select>

    <input type="password"
           name="password"
           placeholder="Password"
           minlength="6"
           required>

    <input type="password"
           name="confirmPassword"
           placeholder="Confirm Password"
           minlength="6"
           required>

    <input type="text"
           name="addressLine1"
           value="${param.addressLine1}"
           placeholder="Address Line 1"
           required>

    <input type="text"
           name="addressLine2"
           value="${param.addressLine2}"
           placeholder="Address Line 2 (Optional)">

    <div class="form-row">

        <input type="text"
               name="city"
               value="${param.city}"
               placeholder="City"
               required>

        <input type="text"
               name="state"
               value="${param.state}"
               placeholder="State"
               required>

    </div>

    <div class="form-row">

        <input type="text"
               name="pincode"
               value="${param.pincode}"
               placeholder="Pincode"
               pattern="[0-9]{6}"
               maxlength="6"
               required>

        <input type="text"
               name="country"
               value="${param.country}"
               placeholder="Country"
               required>

    </div>

    <button type="submit">

        Register

    </button>

    <p class="login-link">

        Already have an account?

        <a href="${pageContext.request.contextPath}/login">

            Login

        </a>

    </p>

</form>
```

</div>

</main>

<jsp:include page="components/footer.jsp"/>

</body>

</html>
