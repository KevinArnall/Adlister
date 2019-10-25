package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        // If they are already logged in, send them to the profile
        if (session.getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }

        // If they tried to log in and the attempt was unsuccessful
        if (session.getAttribute("usernotfound") != null) {
            request.setAttribute("usernotfound", true);
            session.removeAttribute("usernotfound");
        }
        if (session.getAttribute("invalidpassword") != null) {
            request.setAttribute("invalidpassword", true);
            session.removeAttribute("invalidpassword");
        }

        // Send to the login jsp
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        // Get the username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = DaoFactory.getUsersDao().findByUsername(username);

        // If the user was not found in the db, redirect back to the login page
        if (user == null) {
            session.setAttribute("usernotfound", true);
            response.sendRedirect("/login");
            return;
        }

        // If the user was found, check if their password matches
        boolean validAttempt = Password.check(password, user.getPassword());

        if (validAttempt) {
            // Set the user to logged in in the session
            session.setAttribute("user", user);
            session.setAttribute("loggedin", true);

            if (session.getAttribute("redirected") != null) {
                // If user was redirected, send them back to where they were
                session.removeAttribute("redirected");
                response.sendRedirect((String) session.getAttribute("redirectedfrom"));
                session.removeAttribute("redirectedfrom");
            } else {
                // Otherwise default to the profile page
                response.sendRedirect("/profile");
            }
        } else {
            // Invalid password error message
            session.setAttribute("invalidpassword", true);
            // Redirect to the login page
            response.sendRedirect("/login");
        }
    }
}
