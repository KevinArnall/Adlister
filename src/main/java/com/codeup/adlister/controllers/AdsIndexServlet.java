package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.AdsIndexServlet", urlPatterns = "/ads")
public class AdsIndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check if there was a search
        if (request.getParameter("search") != null) {
            // Get the search term from the parameters/URL
            String search = request.getParameter("search");
            // Database request using search term
            request.setAttribute("ads", DaoFactory.getAdsDao().getAdsBySearchTerm(search));
            // Pass the search term back to the view so it can it still in the search box
            request.setAttribute("search", search);
        } else {
            // Otherwise just get all ads
            request.setAttribute("ads", DaoFactory.getAdsDao().all());
        }

        if (request.getParameter("filter") != null) {
            String filter = request.getParameter("filter");

            request.setAttribute("ads", DaoFactory.getAdsDao().getAdsByCategory(filter));
        }

        // Check if the view changed
        // Store it in the session so it persists
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

        // Redirect to index jsp
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "/ads";

        // Check if the user changed the view and change it in the session if they did
        if (request.getParameter("view") != null) {
            if (request.getParameter("view").equals("card")) {
                request.getSession().setAttribute("view", "card");
            } else if (request.getParameter("view").equals("list")) {
                request.getSession().setAttribute("view", "list");
            }
        }

        // If the user searched something, append it to the url so doGet can pick it up
        if (!request.getParameter("search").equals("")) {
            String searchTerm = request.getParameter("search");
            url += "?search=" + searchTerm;
        }

        // Redirect with the appropriate url
        response.sendRedirect(url);
    }
}
