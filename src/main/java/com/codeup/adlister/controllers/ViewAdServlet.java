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

@WebServlet(name = "controllers.ViewAdServlet", urlPatterns = "/ads/ad")
public class ViewAdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the id of the ad from the request
        long id = Long.parseLong(request.getParameter("id"));

        // Find the ad in the db
        Ad ad = DaoFactory.getAdsDao().getAdById(id);
        // Get the user who created the ad
        User user = DaoFactory.getUsersDao().findUserById(ad.getUserId());

        // Pass both to the jsp
        request.setAttribute("ad", ad);
        request.setAttribute("user", user);

        // Send to jsp
        request.getRequestDispatcher("/WEB-INF/ads/ad.jsp").forward(request, response);
    }
}
