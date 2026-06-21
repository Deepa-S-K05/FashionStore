package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.dao.OrderDAO;
import com.fashionstore.daoimpl.CartDAOImpl;
import com.fashionstore.daoimpl.OrderDAOImpl;
import com.fashionstore.model.CartItem;
import com.fashionstore.model.Order;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/place-order")
public class PlaceOrderServlet
        extends HttpServlet {

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

        CartDAO cartDAO =
                new CartDAOImpl();

        List<CartItem> cartItems =
                cartDAO.getCartDetails(
                        user.getUserId());

        double totalAmount = 0;

        for(CartItem item : cartItems){

            totalAmount +=
                    item.getSubtotal();
        }

        Order order =
                new Order();

        order.setUserId(
                user.getUserId());

        order.setTotalAmount(
                totalAmount);

        order.setOrderStatus(
                "Placed");

        OrderDAO orderDAO =
                new OrderDAOImpl();

        int orderId =
                orderDAO.placeOrder(
                        order);

        if(orderId > 0){

            for(CartItem item :
                    cartItems){

                orderDAO.addOrderItem(
                        orderId,
                        item.getVariantId(),
                        item.getQuantity(),
                        item.getPrice());

                orderDAO.updateStock(
                        item.getVariantId(),
                        item.getQuantity());
            }

            cartDAO.clearCart(
                    user.getUserId());

            response.sendRedirect(
                    request.getContextPath()
                    + "/order-success.jsp");

        } else {

            response.sendRedirect(
                    request.getContextPath()
                    + "/checkout");
        }
    }
}