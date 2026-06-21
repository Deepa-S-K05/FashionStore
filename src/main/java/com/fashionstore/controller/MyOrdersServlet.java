package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.daoimpl.OrderDAOImpl;
import com.fashionstore.model.Order;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/my-orders")
public class MyOrdersServlet extends HttpServlet {

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
                   "loggedInUser") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/login");

            return;
        }

        User user =
                (User) session.getAttribute(
                        "loggedInUser");

        OrderDAO orderDAO =
                new OrderDAOImpl();

        List<Order> orders =
                orderDAO.getOrdersByUser(
                        user.getUserId());

        request.setAttribute(
                "orders",
                orders);

        request.getRequestDispatcher(
                "my-orders.jsp")
                .forward(request, response);
    }
}