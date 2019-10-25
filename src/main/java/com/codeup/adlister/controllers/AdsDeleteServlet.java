package com.codeup.adlister.controllers;


import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.AdsDeleteServlet", urlPatterns = "/ads/delete")
public class AdsDeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("id") != null) {
            long id = Long.parseLong(request.getParameter("id"));

            // Have to delete categories first because of foreign key
            DaoFactory.getCategoriesDao().delete(id);
            DaoFactory.getAdsDao().delete(id);

            response.sendRedirect("/ads");
        }
    }
}