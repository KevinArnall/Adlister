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
        if (request.getParameter("search") != null) {
            String search = request.getParameter("search");
            request.setAttribute("ads", DaoFactory.getAdsDao().getAdsBySearchTerm(search));
            request.setAttribute("search", search);
        } else {
            request.setAttribute("ads", DaoFactory.getAdsDao().all());
        }

        if (request.getSession().getAttribute("view") != null) {
            if (request.getSession().getAttribute("view").equals("card")) {
                request.setAttribute("view", "card");
            } else {
                request.setAttribute("view", "list");
            }
        } else {
            request.setAttribute("view", "list");
        }

        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "/ads";

        if (request.getParameter("view") != null) {
            if (request.getParameter("view").equals("card")) {
                request.getSession().setAttribute("view", "card");
            } else if (request.getParameter("view").equals("list")) {
                request.getSession().setAttribute("view", "list");
            }
        }

        if (!request.getParameter("search").equals("")) {
            String searchTerm = request.getParameter("search");
            url += "?search=" + searchTerm;
        }

        response.sendRedirect(url);
    }
}
