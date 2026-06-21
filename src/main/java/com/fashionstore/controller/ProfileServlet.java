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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null ||
           session.getAttribute("loggedInUser") == null){

            response.sendRedirect(
                    request.getContextPath()
                    + "/login");

            return;
        }

        request.getRequestDispatcher(
                "profile.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        User user =
                (User)session.getAttribute(
                        "loggedInUser");

        user.setFirstName(
                request.getParameter("firstName"));

        user.setLastName(
                request.getParameter("lastName"));

        user.setPhone(
                request.getParameter("phone"));

        user.setGender(
                request.getParameter("gender"));

        user.setAddressLine1(
                request.getParameter(
                        "addressLine1"));

        user.setAddressLine2(
                request.getParameter(
                        "addressLine2"));

        user.setCity(
                request.getParameter("city"));

        user.setState(
                request.getParameter("state"));

        user.setPincode(
                request.getParameter("pincode"));

        user.setCountry(
                request.getParameter("country"));

        UserDAO userDAO =
                new UserDAOImpl();

        boolean status =
                userDAO.updateUser(user);

        if(status){

            session.setAttribute(
                    "loggedInUser",
                    user);

            request.setAttribute(
                    "success",
                    "Profile Updated Successfully");
        }

        request.getRequestDispatcher(
                "profile.jsp")
                .forward(request, response);
    }
}