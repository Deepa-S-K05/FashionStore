package com.fashionstore.controller;

import java.io.IOException;

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

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
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

        int variantId =
                Integer.parseInt(
                        request.getParameter(
                                "variantId"));

        int quantity =
                Integer.parseInt(
                        request.getParameter(
                                "quantity"));

        CartDAO cartDAO =
                new CartDAOImpl();

        Cart existingCartItem =
                cartDAO.getCartItem(
                        user.getUserId(),
                        variantId);

        if(existingCartItem != null){

            int newQuantity =
                    existingCartItem.getQuantity()
                    + quantity;

            cartDAO.updateCartQuantity(
                    existingCartItem.getCartId(),
                    newQuantity);

        } else {

            Cart cart =
                    new Cart();

            cart.setUserId(
                    user.getUserId());

            cart.setVariantId(
                    variantId);

            cart.setQuantity(
                    quantity);

            cartDAO.addToCart(cart);
        }

        response.sendRedirect(
                request.getContextPath()
                + "/cart");
    }
}