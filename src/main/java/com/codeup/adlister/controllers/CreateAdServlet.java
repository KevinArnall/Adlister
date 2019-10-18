package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // If not logged in
        if (request.getSession().getAttribute("user") == null) {
            // Set some attributes so they can be redirected back here when they log in
            request.getSession().setAttribute("redirected", true);

            request.getSession().setAttribute("redirectedfrom", "/ads/create");
            // Redirect to the login page
            response.sendRedirect("/login");
            return;
        }

        // Redirect to create jsp
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Get the user that is currently logged in
        User user = (User) request.getSession().getAttribute("user");

        // Create a new ad from the currently logged in user, the title and description that were submitted, and the current date time
        Ad ad = new Ad(
                user.getId(),
                request.getParameter("title"),
                request.getParameter("description"),
                LocalDateTime.now()
        );

        // Insert the ad into the db
        DaoFactory.getAdsDao().insert(ad);

        response.sendRedirect("/ads");
    }
}
