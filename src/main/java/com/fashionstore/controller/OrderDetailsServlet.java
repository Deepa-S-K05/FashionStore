package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.daoimpl.OrderDAOImpl;
import com.fashionstore.model.Order;
import com.fashionstore.model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/order-details")
public class OrderDetailsServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(
                        request.getParameter(
                                "orderId"));

        OrderDAO orderDAO =
                new OrderDAOImpl();

        Order order =
                orderDAO.getOrderById(
                        orderId);

        List<OrderItem> items =
                orderDAO.getOrderItemsByOrderId(
                        orderId);

        request.setAttribute(
                "order",
                order);

        request.setAttribute(
                "items",
                items);

        request.getRequestDispatcher(
                "order-details.jsp")
                .forward(request,
                         response);
    }
}