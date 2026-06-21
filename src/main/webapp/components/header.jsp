<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header class="navbar">

    <div class="logo">
        <a href="${pageContext.request.contextPath}/home">
            Fashion Store
        </a>
    </div>

    <div class="hamburger" id="hamburger">
        ☰
    </div>

    <div class="nav-container" id="navContainer">

       <div class="search-box">

    <form action="${pageContext.request.contextPath}/products"
          method="get">

        <input type="text"
               name="keyword"
               placeholder="Search products..."
               required>

        <button type="submit">
            Search
        </button>

    </form>

</div>

        <nav class="nav-links">

            <a href="${pageContext.request.contextPath}/home">
                Home
            </a>

            <a href="${pageContext.request.contextPath}/products">
              Products
            </a>

            <a href="${pageContext.request.contextPath}/home#about">
              About
            </a>

          <a href="${pageContext.request.contextPath}/home#contact">
              Contact
          </a>

        </nav>

        <div class="nav-actions">

            <% if(session.getAttribute("loggedInUser") == null){ %>


             <a href="${pageContext.request.contextPath}/login"
              class="btn-login">
              Login
              </a>

            <% } else { %>
            
                 <a href="${pageContext.request.contextPath}/cart">
               Cart
              </a>
                
              <div class="profile-dropdown">

    <button class="profile-btn">

        Profile ▼

    </button>

    <div class="dropdown-content">

        <a href="${pageContext.request.contextPath}/profile">

            My Information

        </a>

        <a href="${pageContext.request.contextPath}/my-orders">

            My Orders

        </a>

        <a href="${pageContext.request.contextPath}/logout">

            Logout

        </a>

    </div>

</div>

            <% } %>

        </div>

    </div>

</header>