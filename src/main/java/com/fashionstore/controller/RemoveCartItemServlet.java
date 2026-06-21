package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.daoimpl.CartDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/remove-cart-item")
public class RemoveCartItemServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int cartId =
                Integer.parseInt(
                        request.getParameter(
                                "cartId"));

        CartDAO cartDAO =
                new CartDAOImpl();

        cartDAO.removeCartItem(
                cartId);

        response.sendRedirect(
                request.getContextPath()
                + "/cart");
    }
}