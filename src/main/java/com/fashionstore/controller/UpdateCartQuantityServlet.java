package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.daoimpl.CartDAOImpl;
import com.fashionstore.model.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update-cart-quantity")
public class UpdateCartQuantityServlet
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

    String action =
            request.getParameter(
                    "action");

    int currentQuantity =
            Integer.parseInt(
                    request.getParameter(
                            "quantity"));

    CartDAO cartDAO =
            new CartDAOImpl();

    Cart cart =
            cartDAO.getCartById(
                    cartId);

    int stock =
            cartDAO.getStockByVariantId(
                    cart.getVariantId());

    int newQuantity =
            currentQuantity;

    if("increase".equals(action)) {

        if(currentQuantity < stock) {

            newQuantity =
                    currentQuantity + 1;
        }

    } else if("decrease".equals(action)) {

        if(currentQuantity > 1) {

            newQuantity =
                    currentQuantity - 1;
        }
    }

    cartDAO.updateCartQuantity(
            cartId,
            newQuantity);

    response.sendRedirect(
            request.getContextPath()
            + "/cart");
}


}
