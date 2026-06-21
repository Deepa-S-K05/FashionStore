package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.daoimpl.ProductDAOImpl;
import com.fashionstore.model.Product;
import com.fashionstore.model.ProductImage;
import com.fashionstore.model.ProductVariant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product")
public class ProductDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int productId =
                Integer.parseInt(
                        request.getParameter("id"));

        ProductDAO productDAO =
                new ProductDAOImpl();

        Product product =
                productDAO.getProductById(
                        productId);

        List<ProductImage> images =
                productDAO.getProductImages(
                        productId);

        List<ProductVariant> variants =
                productDAO.getProductVariants(
                        productId);

        request.setAttribute(
                "product",
                product);

        request.setAttribute(
                "images",
                images);

        request.setAttribute(
                "variants",
                variants);

        request.getRequestDispatcher(
                "product-details.jsp")
                .forward(request, response);
    }
}