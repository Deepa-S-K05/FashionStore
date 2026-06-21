package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.daoimpl.ProductDAOImpl;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        ProductDAO productDAO =
                new ProductDAOImpl();

        List<Product> products;

        String category =
                request.getParameter("category");
        String keyword =
                request.getParameter("keyword");

        if(keyword != null &&
        		   !keyword.trim().isEmpty()) {

        		    products =
        		            productDAO.searchProducts(
        		                    keyword);

        		}
        		else if(category != null &&
        		        !category.isEmpty()) {

        		    int categoryId =
        		            Integer.parseInt(category);

        		    products =
        		            productDAO
        		            .getProductsByCategoryWithImages(
        		                    categoryId);

        		}
        		else {

        		    products =
        		            productDAO
        		            .getAllProductsWithImages();
        		}
        System.out.println("Products Count = " + products.size());

        request.setAttribute(
                "products",
                products);
        request.setAttribute(
        	    "productCount",
        	    products.size());
      

        request.getRequestDispatcher(
                "products.jsp")
                .forward(request, response);
    }
}