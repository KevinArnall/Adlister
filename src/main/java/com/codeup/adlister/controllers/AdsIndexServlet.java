package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.AdsIndexServlet", urlPatterns = "/ads")
public class AdsIndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Ad> ads = DaoFactory.getAdsDao().all();

        // Check if there was a search
        if (request.getParameter("search") != null) {
            // Get the search term from the parameters/URL
            String search = request.getParameter("search");

            List<Ad> newAds = new ArrayList<>();

            // Go through ads and return ads that match
            for (Ad ad : ads) {
                if (StringUtils.containsIgnoreCase(ad.getTitle(), search) || StringUtils.containsIgnoreCase(ad.getDescription(), search)) {
                    newAds.add(ad);
                }
            }

            ads = newAds;

            // Pass the search term back to the view so it can still be in the search box
            request.setAttribute("search", search);
        }

        // Check if ads were filtered
        if (request.getParameter("filter") != null) {
            // Get filtered parameters
            String filter = String.join(",", request.getParameterValues("filter"));
            List<Ad> newAds = new ArrayList<>();

            // Check if category is in the filter string
            for (Ad ad : ads) {
                for (String cat : ad.getCategories()) {
                    if (filter.contains(cat)) {
                        newAds.add(ad);

                        // Don't want duplicates if already matches
                        break;
                    }
                }
            }

            // Pass filters back to view so checkboxes can remain checked
            request.setAttribute("filter", filter);

            ads = newAds;
        }

        // Check if the view changed
        // Store it in the session so it persists
        if (session.getAttribute("view") != null) {
            if (session.getAttribute("view").equals("card")) {
                request.setAttribute("view", "card");
            } else {
                request.setAttribute("view", "list");
            }
        } else {
            // Default to list view
            request.setAttribute("view", "list");
        }

        // Pass final list of ads to view
        request.setAttribute("ads", ads);

        // Get a list of all the categories in the db so it is easy to create a list of them
        request.setAttribute("categories", DaoFactory.getCategoriesDao().all());

        // Redirect to index jsp
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        URIBuilder uri = new URIBuilder();
        uri.setPath("/ads");

        // Check if the user changed the view and change it in the session if they did
        if (request.getParameter("view") != null) {
            if (request.getParameter("view").equals("card")) {
                session.setAttribute("view", "card");
            } else if (request.getParameter("view").equals("list")) {
                session.setAttribute("view", "list");
            }
        }

        // If the user searched something, add it to the query string
        if (!request.getParameter("search").equals("")) {
            String searchTerm = request.getParameter("search");
            uri.addParameter("search", searchTerm);
        }

        // If user checked any of the filter checkboxes, add to query string
        if (request.getParameterValues("categories") != null) {
            String[] categories = request.getParameterValues("categories");

            // Join into one string for easy adding
            String str = String.join(",", categories);
            uri.addParameter("filter", str);
        }

        // Redirect with the appropriate url
        response.sendRedirect(uri.toString());
    }
}
