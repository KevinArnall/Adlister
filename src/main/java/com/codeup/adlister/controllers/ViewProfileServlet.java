package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.ViewProfileServlet", urlPatterns = "/profile")
public class ViewProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // If the user is not logged in, redirect to the login page
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        // Get the user that is currently logged in from the session
        User user = (User) request.getSession().getAttribute("user");

        // Get all the ads made by the user
        request.setAttribute("ads", DaoFactory.getAdsDao().getAdsByUserId(user.getId()));

        // Get the current view option
        if (request.getSession().getAttribute("view") != null) {
            if (request.getSession().getAttribute("view").equals("card")) {
                request.setAttribute("view", "card");
            } else {
                request.setAttribute("view", "list");
            }
        } else {
            // Default to list view
            request.setAttribute("view", "list");
        }

        // Send jsp
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
}
