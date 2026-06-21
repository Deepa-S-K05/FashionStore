<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.fashionstore.model.Category"%>
<%@ page import="com.fashionstore.model.Product"%>
<%@ page import="com.fashionstore.dao.ProductDAO"%>
<%@ page import="com.fashionstore.daoimpl.ProductDAOImpl"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title>Fashion Store</title>
<link rel="icon"
      type="image/png"
      href="${pageContext.request.contextPath}/assets/images/logo.png">

<link rel="stylesheet"
      href="assets/css/style.css">

</head>

<body>

    <jsp:include page="components/header.jsp"/>

    <main>
        <%
String successMessage =
        (String)session.getAttribute(
                "successMessage");

if(successMessage != null){
%>

<div class="success-alert">

    <%=successMessage%>

</div>

<%
session.removeAttribute(
        "successMessage");
}
%>

        <!-- HERO SECTION -->

        <section class="hero-slider">

            <div class="slides">

                <div class="slide active">

                    <a href="${pageContext.request.contextPath}/products">

                        <img
                        src="assets/images/hero/hero1.jpg"
                        alt="All Products">

                    </a>

                </div>

                <div class="slide">

                    <a href="${pageContext.request.contextPath}/products?category=1">

                        <img
                        src="assets/images/hero/hero2.jpg"
                        alt="Men Collection">

                    </a>

                </div>

                <div class="slide">

                    <a href="${pageContext.request.contextPath}/products?category=2">

                        <img
                        src="assets/images/hero/hero3.jpg"
                        alt="Women Collection">

                    </a>

                </div>

            </div>

        </section>

        <!-- CATEGORY SECTION -->

        <section class="categories-section">

            <div class="section-title">

                <h2>Shop By Category</h2>

                <p>
                    Discover fashion collections
                    for every style
                </p>

            </div>

            <div class="categories-grid">

                <%

                List<Category> categories =
                        (List<Category>)
                        request.getAttribute("categories");

                if(categories != null){

                    for(Category category : categories){

                %>

                <a
                href="${pageContext.request.contextPath}/products?category=<%=category.getCategoryId()%>"
                class="category-card">

                    <img
                    src="assets/images/<%=category.getCategoryImage()%>"
                    alt="<%=category.getCategoryName()%>">

                    <div class="category-overlay">

                        <h3>
                            <%=category.getCategoryName()%>
                        </h3>

                    </div>

                </a>

                <%
                    }
                }
                %>

            </div>

        </section>


<section class="about-section" id="about">

    <div class="about-container">

        <div class="about-content">

            <h2>About Fashion Store</h2>

            <p>
                Fashion Store is your one-stop destination
                for stylish clothing, footwear and accessories.
                We bring together quality products, modern
                trends and affordable prices to help you
                express your unique style.
            </p>

            <div class="about-features">

                <div class="feature">
                    ✓ Premium Quality Products
                </div>

                <div class="feature">
                    ✓ Latest Fashion Trends
                </div>

                <div class="feature">
                    ✓ Secure Shopping Experience
                </div>

                <div class="feature">
                    ✓ Fast Delivery Service
                </div>

            </div>

        </div>

    </div>


</section>
<section class="contact-section" id="contact">

    <div class="section-title">

        <h2>Contact Us</h2>

        <p>
            We'd love to hear from you
        </p>

    </div>

    <div class="contact-container">

        <div class="contact-card">

            <h3>Address</h3>

            <p>
                Bangalore, Karnataka, India
            </p>

        </div>

        <div class="contact-card">

            <h3>Email</h3>

            <p>
                support@fashionstore.com
            </p>

        </div>

        <div class="contact-card">

            <h3>Phone</h3>

            <p>
                +91 9876543210
            </p>

        </div>

    </div>

</section>
    </main>

    <jsp:include page="components/footer.jsp"/>

    <script>

        let slides =
                document.querySelectorAll(".slide");

        let currentSlide = 0;

        function showSlide(){

            slides.forEach(slide =>
                slide.classList.remove("active")
            );

            currentSlide++;

            if(currentSlide >= slides.length){

                currentSlide = 0;

            }

            slides[currentSlide]
                .classList.add("active");
        }

        setInterval(showSlide, 2000);

        const hamburger =
                document.getElementById("hamburger");

        const navContainer =
                document.getElementById("navContainer");

        hamburger.addEventListener("click", () => {

            navContainer.classList.toggle("active");

        });

    

setTimeout(function(){

    let alert =
        document.querySelector(
            ".success-alert");

    if(alert){

        alert.remove();
    }

}, 2000);

</script>

</body>

</html>