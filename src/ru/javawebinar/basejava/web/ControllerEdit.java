package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

public class ControllerEdit extends ResumeServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String mode = request.getParameter("mode");
        boolean hasAdd = mode.equals("add");
        String full_name = request.getParameter("full_name").trim();
        Resume resume;
        if (hasAdd) {
            resume = new Resume(UUID.randomUUID().toString(), full_name);
        } else {
            resume = new Resume(uuid, full_name);
        }
        Enumeration vars = request.getParameterNames();
        while (vars.hasMoreElements()) {
            String param = (String) vars.nextElement();
            if (param.startsWith("contact")) {
                String typeStr = param.substring(7);
                ContactType type = ContactType.valueOf(typeStr);
                String value = request.getParameter(param);
                if (value.trim().length() > 0) {
                    resume.addContact(type, value.trim());
                }
            } else if (param.startsWith("section")) {
                String typeStr = param.substring(7);
                SectionType type = SectionType.valueOf(typeStr);
                String value = request.getParameter(param);
                AbstractSection section = null;
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE: {
                        if (value.trim().length() > 0) {
                            section = new TextSection(value);
                        }
                    }
                    break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        ListSection listSection = new ListSection();
                        for (String line : value.split("\n")) {
                            line = line.trim();
                            if (line.length() > 0) {
                                listSection.getStrings().add(line);
                            }
                        }
                        if (listSection.getStrings().size() > 0) {
                            section = listSection;
                        }
                    }
                    break;
                    case EXPERIENCE:
                    case EDUCATION: {
                        section = new OrganizationSection();
                    }
                    break;
                }
                if (section != null) {
                    resume.addSection(type, section);
                }
            }
        }
        if (full_name.isEmpty()) {
            request.setAttribute("resume", resume);
            request.setAttribute("mode", mode);
            request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
        } else {
            if (hasAdd) {
                storage.save(resume);
            } else {
                storage.update(resume);
            }
            response.sendRedirect("/resume/");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String mode = request.getParameter("mode");
        if (mode.equals("delete")) {
            storage.delete(uuid);
            response.sendRedirect("/resume/");
        } else {
            Resume resume;
            if (mode.equals("edit")) {
                resume = storage.get(uuid);
            } else {
                resume = new Resume("", "Новый раб");
            }
            request.setAttribute("resume", resume);
            request.setAttribute("mode", mode);
            request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
        }
    }
}
