package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        // If not logged in
        if (session.getAttribute("user") == null) {
            // Set some attributes so they can be redirected back here when they log in
            session.setAttribute("redirected", true);

            session.setAttribute("redirectedfrom", "/ads/create");
            // Redirect to the login page
            response.sendRedirect("/login");
            return;
        }

        // If the user was sent back here
        if (session.getAttribute("title") != null) {
            request.setAttribute("title", session.getAttribute("title"));
            session.removeAttribute("title");

            request.setAttribute("description", session.getAttribute("description"));
            session.removeAttribute("description");

            request.setAttribute("needcat", true);
            session.removeAttribute("needcat");
        }

        // Get list of categories from db
        request.setAttribute("categories", DaoFactory.getCategoriesDao().all());

        // Redirect to create jsp
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        // Get the user that is currently logged in
        User user = (User) session.getAttribute("user");

        List<String> categories;

        if (request.getParameterValues("categories") != null) {
            // Get the categories that the user selected
            categories = Arrays.asList(request.getParameterValues("categories"));
        } else {
            // Otherwise, save the inputs and redirect back
            session.setAttribute("title", request.getParameter("title"));
            session.setAttribute("description", request.getParameter("description"));
            session.setAttribute("needcat", true);
            response.sendRedirect("/ads/create");
            return;
        }

        // Create a new ad from the currently logged in user, the title and description that were submitted, and the current date time
        Ad ad = new Ad(
                user.getId(),
                request.getParameter("title"),
                request.getParameter("description"),
                LocalDateTime.now(),
                categories
        );

        // Insert the ad into the db
        DaoFactory.getAdsDao().insert(ad);

        response.sendRedirect("/ads");
    }
}
