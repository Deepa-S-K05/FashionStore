package com.fashionstore.controller;
import com.fashionstore.model.CartItem;
import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.daoimpl.CartDAOImpl;
import com.fashionstore.model.Cart;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null ||
           session.getAttribute(
                   "loggedInUser") == null){

            response.sendRedirect(
                    request.getContextPath()
                    + "/login");

            return;
        }

        User user =
                (User)session.getAttribute(
                        "loggedInUser");

        CartDAO cartDAO =
                new CartDAOImpl();

        List<CartItem> cartItems =
                cartDAO.getCartDetails(
                        user.getUserId());

        request.setAttribute(
                "cartItems",
                cartItems);

        request.getRequestDispatcher(
                "cart.jsp")
                .forward(request, response);
    }
}