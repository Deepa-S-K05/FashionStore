<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.fashionstore.model.Product"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title>Products | Fashion Store</title>
<link rel="icon"
      type="image/png"
      href="${pageContext.request.contextPath}/assets/images/logo.png">

<link rel="stylesheet"
      href="assets/css/style.css">

<link rel="stylesheet"
      href="assets/css/products.css">

</head>

<body>

    <jsp:include page="components/header.jsp"/>

    <main>

        <section class="products-banner">

    

</section>
<section class="products-section">

    <div class="filters-bar">

        <a href="${pageContext.request.contextPath}/products"
           class="filter-btn">
            All
        </a>

        <a href="${pageContext.request.contextPath}/products?category=1"
           class="filter-btn">
            Men
        </a>

        <a href="${pageContext.request.contextPath}/products?category=2"
           class="filter-btn">
            Women
        </a>

        <a href="${pageContext.request.contextPath}/products?category=3"
           class="filter-btn">
            Footwear
        </a>

        <a href="${pageContext.request.contextPath}/products?category=4"
           class="filter-btn">
            Accessories
        </a>

    </div>
     <div class="products-count">

    Showing
    <strong>
        ${productCount}
    </strong>
    products

</div>
 
     <div class="products-grid">

                <%

                List<Product> products =
                        (List<Product>)
                        request.getAttribute("products");

                if(products != null &&
                   !products.isEmpty()){

                    for(Product product : products){

                %>

                <div class="product-card">

                    <div class="product-image">

                        
                       <img src="assets/images/<%=product.getImageUrl()%>">

                    </div>

                    <div class="product-info">

                        <h3>
                            <%=product.getProductName()%>
                        </h3>

                        <p class="brand">
                            <%=product.getBrand()%>
                        </p>

                        <p class="price">
                            Rs. <%=product.getPrice()%>
                        </p>

                        <a href="${pageContext.request.contextPath}/product?id=<%=product.getProductId()%>"
                            class="view-btn">

                         View Details

                        </a>
                    </div>

                </div>

                <%
                    }
                }else{
                %>

                <h2>No Products Found</h2>

                <%
                }
                %>

            </div>

        </section>

    </main>

    <jsp:include page="components/footer.jsp"/>

</body>

</html>