package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.UserDAO;
import com.fashionstore.daoimpl.UserDAOImpl;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        UserDAO userDAO =
                new UserDAOImpl();

        User user =
                userDAO.loginUser(
                        email,
                        password);

        if(user != null) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "loggedInUser",
                    user);

            session.setAttribute(
                    "successMessage",
                    "Successfully Logged In");

            response.sendRedirect(
                    request.getContextPath()
                    + "/home");
        } else {

            request.setAttribute(
                    "error",
                    "Invalid Email or Password");

            request.getRequestDispatcher(
                    "login.jsp")
                    .forward(request, response);
        }
    }
}