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
import java.util.HashMap;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        // If there were any errors
        if (session.getAttribute("errors") != null) {
            HashMap<String, String> errors = (HashMap<String, String>) session.getAttribute("errors");

            // Loop through errors and add them to the view
            for (String key : errors.keySet()) {
                request.setAttribute(key, errors.get(key));
            }

            // Clear the errors
            session.removeAttribute("errors");
        }

        // Send to register jsp
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        // Get the users information from the form
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

        boolean inputHasErrors = false;
        HashMap<String, String> errors = new HashMap<>();

        // If passwords do not match
        if (!password.equals(passwordConfirmation)) {
            inputHasErrors = true;
            errors.put("passwordmatch", "Passwords do not match");
        }

        // If the length is too short
        if (password.length() < 8) {
            inputHasErrors = true;
            errors.put("passwordlength", "Password is not long enough");
        }
        if (username.length() < 4) {
            inputHasErrors = true;
            errors.put("usernamelength", "Username is not long enough");
        }

        // If email is invalid
        if (!email.contains("@")) {
            inputHasErrors = true;
            errors.put("email", "Please provide a valid email address");
        }

        // If user already exists
        if (DaoFactory.getUsersDao().findByUsername(username) != null) {
            inputHasErrors = true;
            errors.put("usernameexists", "Username already exists");
        }

        // If anything is invalid
        if (inputHasErrors) {
            session.setAttribute("errors", errors);
            response.sendRedirect("/register");
            return;
        }

        // At this point everything should be valid
        User user = new User(username, email, Password.hash(password));
        DaoFactory.getUsersDao().insert(user);

        // Send them to the login page to login
        response.sendRedirect("/login");
    }
}
