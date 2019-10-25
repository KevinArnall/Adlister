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
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "controllers.AdsEditServlet", urlPatterns = "/ads/edit")
public class AdsEditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the ad from the id sent
        Ad ad = DaoFactory.getAdsDao().getAdById(Long.parseLong(request.getParameter("id")));

        // Check what categories were already selected
        if (ad.getCategories().contains("For Sale")) {
            request.setAttribute("forsale", true);
        }
        if (ad.getCategories().contains("Help Wanted")) {
            request.setAttribute("helpwanted", true);
        }

        // If the user was sent back here
        if (request.getSession().getAttribute("needcat") != null) {
            request.setAttribute("title", request.getSession().getAttribute("title"));
            request.getSession().removeAttribute("title");

            request.setAttribute("description", request.getSession().getAttribute("description"));
            request.getSession().removeAttribute("description");

            request.setAttribute("needcat", true);
            request.getSession().removeAttribute("needcat");
        }

        // Pass ad into request
        request.setAttribute("ad", ad);

        // To JSP
        request.getRequestDispatcher("/WEB-INF/ads/edit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Get the user that is currently logged in
        User user = (User) request.getSession().getAttribute("user");

        List<String> categories;

        if (request.getParameterValues("categories") != null) {
            // Get the categories that the user selected
            categories = Arrays.asList(request.getParameterValues("categories"));
        } else {
            // Otherwise, save the inputs and redirect back
            request.getSession().setAttribute("title", request.getParameter("title"));
            request.getSession().setAttribute("description", request.getParameter("description"));
            request.getSession().setAttribute("needcat", true);
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