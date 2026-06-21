package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.dao.ProductDAO;
import com.fashionstore.daoimpl.CategoryDAOImpl;
import com.fashionstore.daoimpl.ProductDAOImpl;
import com.fashionstore.model.Category;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        CategoryDAO categoryDAO = new CategoryDAOImpl();
        ProductDAO productDAO = new ProductDAOImpl();

        List<Category> categories =
                categoryDAO.getAllCategories();

        List<Product> products =
                productDAO.getAllProducts();

        request.setAttribute("categories", categories);
        request.setAttribute("products", products);

        request.getRequestDispatcher("index.jsp")
                .forward(request, response);

        List<Product> featuredProducts =
                productDAO.getFeaturedProducts(8);

        request.setAttribute(
                "featuredProducts",
                featuredProducts);
    }
}