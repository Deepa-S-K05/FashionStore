<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.fashionstore.model.Product"%>
<%@ page import="com.fashionstore.model.ProductImage"%>
<%@ page import="com.fashionstore.model.ProductVariant"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<meta name="viewport"
   content="width=device-width, initial-scale=1.0">

<title>Product Details</title>
<link rel="icon"
      type="image/png"
      href="${pageContext.request.contextPath}/assets/images/logo.png">
<link rel="stylesheet"
      href="assets/css/style.css">

<link rel="stylesheet"
      href="assets/css/product-details.css">

</head>

<body>

<jsp:include page="components/header.jsp"/>

<main>

<%
Product product =
(Product) request.getAttribute(
"product");

List<ProductImage> images =
(List<ProductImage>)
request.getAttribute(
"images");

List<ProductVariant> variants =
(List<ProductVariant>)
request.getAttribute(
"variants");
%>

<section class="product-details">

```
<!-- PRODUCT GALLERY -->

<div class="product-gallery">

    <%
    if(images != null &&
       !images.isEmpty()){
    %>

    <img
        id="mainImage"
        class="main-image"
        src="assets/images/<%=images.get(0).getImageUrl()%>"
        alt="<%=product.getProductName()%>">

    <div class="thumbnail-container">

        <%
        for(ProductImage image : images){
        %>

        <img
            class="thumbnail"
            src="assets/images/<%=image.getImageUrl()%>"
            alt="Product Image"
            onclick="changeImage(this)">

        <%
        }
        %>

    </div>

    <%
    }
    %>

</div>

<!-- PRODUCT INFO -->

<div class="product-info">

    <h1>
        <%=product.getProductName()%>
    </h1>

    <h3>
        <%=product.getBrand()%>
    </h3>

    <p class="price">

        Rs. <%=product.getPrice()%>

    </p>

    <p class="description">

        <%=product.getDescription()%>

    </p>

    <form action="${pageContext.request.contextPath}/add-to-cart"
          method="post">

        <h4>Available Sizes</h4>

        <div class="sizes">

            <%
            for(ProductVariant variant :
                    variants){
            %>

            <label class="size-option">

                <input
                    type="radio"
                    name="variantId"
                    value="<%=variant.getVariantId()%>"
                    data-stock="<%=variant.getStockQuantity()%>"
                    <%= variant.getStockQuantity() == 0
                    ? "disabled"
                    : "" %>
                    onchange="updateStock(this)"
                    required>

                <span>

                    <%=variant.getSize()%>

                    <% if(variant.getStockQuantity() == 0){ %>

                        (Out Of Stock)

                    <% } %>

                </span>

            </label>

            <%
            }
            %>

        </div>

        <div id="stockInfo"
             class="stock-info">

            Select a size

        </div>

        <div class="quantity-box">

            <button type="button"
                    onclick="decreaseQty()">

                -

            </button>

            <input
                type="text"
                id="qty"
                name="quantity"
                value="1"
                readonly>

            <button type="button"
                    onclick="increaseQty()">

                +

            </button>

        </div>

        <button type="submit"
                class="add-cart-btn">

            Add To Cart

        </button>

    </form>

</div>


</section>

</main>

<jsp:include page="components/footer.jsp"/>

<script>

function changeImage(img){

    document
        .getElementById(
            "mainImage")
        .src = img.src;
}

let currentStock = 0;

function updateStock(element){

    currentStock =
        parseInt(
            element.dataset.stock
        );

    document
        .getElementById(
            "stockInfo")
        .innerHTML =
        "✓ In Stock : "
        + currentStock;

    document
        .getElementById(
            "qty")
        .value = 1;
}

function increaseQty(){

    let qty =
        document.getElementById(
            "qty");

    let currentQty =
        parseInt(
            qty.value);

    if(currentStock === 0){

        alert(
            "Please select a size first");

        return;
    }

    if(currentQty < currentStock){

        qty.value =
            currentQty + 1;
    }
}

function decreaseQty(){

    let qty =
        document.getElementById(
            "qty");

    let currentQty =
        parseInt(
            qty.value);

    if(currentQty > 1){

        qty.value =
            currentQty - 1;
    }
}

</script>

</body>

</html>
