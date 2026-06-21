<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.fashionstore.model.User"%>

<%
User user =
        (User)session.getAttribute(
                "loggedInUser");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title>My Profile</title>
<link rel="icon"
      type="image/png"
      href="${pageContext.request.contextPath}/assets/images/logo.png">
<link rel="stylesheet"
      href="assets/css/style.css">

<link rel="stylesheet"
      href="assets/css/profile.css">

</head>

<body>

<jsp:include page="components/header.jsp"/>

<main>

<div class="profile-container">

<h2>My Information</h2>

<%
String success =
        (String)request.getAttribute(
                "success");

if(success != null){
%>

<div class="success-box">

    <%=success%>

</div>

<%
}
%>

<form action="${pageContext.request.contextPath}/profile"
      method="post"
      class="profile-form">

<div class="form-row">

<input type="text"
       name="firstName"
       value="<%=user.getFirstName()%>"
       required>

<input type="text"
       name="lastName"
       value="<%=user.getLastName()%>"
       required>

</div>

<input type="email"
       value="<%=user.getEmail()%>"
       readonly>

<input type="tel"
       name="phone"
       value="<%=user.getPhone()%>"
       required>

<select name="gender">

<option value="Male"
<%= "Male".equals(user.getGender())
? "selected" : "" %>>

Male

</option>

<option value="Female"
<%= "Female".equals(user.getGender())
? "selected" : "" %>>

Female

</option>

<option value="Other"
<%= "Other".equals(user.getGender())
? "selected" : "" %>>

Other

</option>

</select>

<input type="text"
       name="addressLine1"
       value="<%=user.getAddressLine1()%>"
       required>

<input type="text"
       name="addressLine2"
       value="<%=user.getAddressLine2()%>">

<div class="form-row">

<input type="text"
       name="city"
       value="<%=user.getCity()%>"
       required>

<input type="text"
       name="state"
       value="<%=user.getState()%>"
       required>

</div>

<div class="form-row">

<input type="text"
       name="pincode"
       value="<%=user.getPincode()%>"
       required>

<input type="text"
       name="country"
       value="<%=user.getCountry()%>"
       required>

</div>

<button type="submit">

Save Changes

</button>

</form>

<hr>



<a href="${pageContext.request.contextPath}/logout"
   class="logout-btn">

Logout

</a>

</div>

</main>

<jsp:include page="components/footer.jsp"/>

</body>

</html>