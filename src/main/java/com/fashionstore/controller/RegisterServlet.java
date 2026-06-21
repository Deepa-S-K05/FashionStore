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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("register.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        UserDAO userDAO = new UserDAOImpl();

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        String confirmPassword =
                request.getParameter("confirmPassword");

        // Email already exists
        if(userDAO.emailExists(email)) {

            request.setAttribute(
                    "error",
                    "Email already registered");

            request.getRequestDispatcher("register.jsp")
                   .forward(request, response);

            return;
        }

        // Password mismatch
        if(!password.equals(confirmPassword)) {

            request.setAttribute(
                    "error",
                    "Passwords do not match");

            request.getRequestDispatcher("register.jsp")
                   .forward(request, response);

            return;
        }

        User user = new User();

        user.setFirstName(
                request.getParameter("firstName"));

        user.setLastName(
                request.getParameter("lastName"));

        user.setEmail(email);

        user.setPassword(password);

        user.setPhone(
                request.getParameter("phone"));

        user.setGender(
                request.getParameter("gender"));

        user.setAddressLine1(
                request.getParameter("addressLine1"));

        user.setAddressLine2(
                request.getParameter("addressLine2"));

        user.setCity(
                request.getParameter("city"));

        user.setState(
                request.getParameter("state"));

        user.setPincode(
                request.getParameter("pincode"));

        user.setCountry(
                request.getParameter("country"));

        boolean status =
                userDAO.registerUser(user);

        if(status) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/login?success=registered");

        } else {

            request.setAttribute(
                    "error",
                    "Registration failed. Please try again.");

            request.getRequestDispatcher("register.jsp")
                   .forward(request, response);
        }
    }
}