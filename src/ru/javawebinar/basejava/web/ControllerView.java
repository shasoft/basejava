package ru.javawebinar.basejava.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerView extends ResumeServlet {

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("resume", storage.get(request.getParameter("uuid")));
        request.getRequestDispatcher("/WEB-INF/jsp/view.jsp").forward(request, response);
    }
}
