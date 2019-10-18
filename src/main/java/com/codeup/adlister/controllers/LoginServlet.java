package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // If they are already logged in, send them to the profile
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }

        // Otherwise, send to the login jsp
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Get the username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = DaoFactory.getUsersDao().findByUsername(username);

        // If the user was not found in the db, redirect back to the login page
        if (user == null) {
            response.sendRedirect("/login");
            return;
        }

        // If the user was found, check if their password matches
        boolean validAttempt = Password.check(password, user.getPassword());

        if (validAttempt) {
            // Set the user to logged in in the session
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("loggedin", true);

            if (request.getSession().getAttribute("redirected") != null) {
                // If user was redirected, send them back to where they were
                request.getSession().removeAttribute("redirected");
                response.sendRedirect((String) request.getSession().getAttribute("redirectedfrom"));
                request.getSession().removeAttribute("redirectedfrom");
            } else {
                // Otherwise default to the profile page
                response.sendRedirect("/profile");
            }
        } else {
            // Else redirect to the login page
            response.sendRedirect("/login");
        }
    }
}
