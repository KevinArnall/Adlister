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

@WebServlet(name = "controllers.AdsEditServlet", urlPatterns = "/ads/edit")
public class AdsEditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Get the ad from the id sent
        Ad ad = DaoFactory.getAdsDao().getAdById(Long.parseLong(request.getParameter("id")));

        // Some security checks
        // Check if the user is logged in
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            // Check if the user that is logged in created the ad
            if (user.getId() != ad.getUserId()) {
                response.sendRedirect("/ads");
                return;
            }
        } else {
            response.sendRedirect("/ads");
            return;
        }

        // Check what categories were already selected
        if (ad.getCategories().contains("For Sale")) {
            request.setAttribute("forsale", true);
        }
        if (ad.getCategories().contains("Help Wanted")) {
            request.setAttribute("helpwanted", true);
        }

        // If the user was sent back here
        if (session.getAttribute("needcat") != null) {
            request.setAttribute("title", session.getAttribute("title"));
            session.removeAttribute("title");

            request.setAttribute("description", session.getAttribute("description"));
            session.removeAttribute("description");

            request.setAttribute("needcat", true);
            session.removeAttribute("needcat");
        }

        // Pass ad into request
        request.setAttribute("ad", ad);

        // To JSP
        request.getRequestDispatcher("/WEB-INF/ads/edit.jsp").forward(request, response);
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
            response.sendRedirect("/ads/edit?id=" + request.getParameter("id"));
            return;
        }

        // Create a new ad from the currently logged in user, the title and description that were submitted, and the current date time
        Ad ad = new Ad(
                Long.parseLong(request.getParameter("id")),
                user.getId(),
                request.getParameter("title"),
                request.getParameter("description"),
                LocalDateTime.now(),
                categories
        );

        // Edit the ad in the db
        DaoFactory.getAdsDao().edit(ad);

        // Send back to ad page
        response.sendRedirect("/ads/ad?id=" + ad.getId());
    }
}