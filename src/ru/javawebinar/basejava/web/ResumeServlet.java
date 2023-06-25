package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    protected Storage storage;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        //
        String template = """
                <!DOCTYPE html>
                <html>
                <style>
                    table {
                    font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
                    font-size: 14px;
                    border-radius: 10px;
                    border-spacing: 0;
                    text-align: center;
                    }
                    th {
                    background: #BCEBDD;
                    color: black;
                    text-shadow: 0 1px 1px red;
                    padding: 10px 20px;
                    }
                    th, td {
                    border-style: solid;
                    border-width: 0 1px 1px 0;
                    border-color: white;
                    }
                    th:first-child, td:first-child {
                    text-align: left;
                    }
                    th:first-child {
                    border-top-left-radius: 10px;
                    }
                    th:last-child {
                    border-top-right-radius: 10px;
                    border-right: none;
                    }
                    td {
                    padding: 10px 20px;
                    background: #F8E391;
                    }
                    tr:last-child td:first-child {
                    border-radius: 0 0 0 10px;
                    }
                    tr:last-child td:last-child {
                    border-radius: 0 0 10px 0;
                    }
                    tr td:last-child {
                    border-right: none;
                    }
                </style>
                <body>

                <h2>Список резюме</h2>

                <table style="width:100%">
                  <tr>
                    <th>uuid</th>
                    <th>full_name</th>
                  </tr>
                  {{ rows }}
                </table>

                <p>Ура, работает!</p>

                </body>
                </html>
                """;

        StringBuilder rows = new StringBuilder();
        List<Resume> resumes = Config.get().getStorage().getAllSorted();
        for (Resume resume : resumes) {
            rows.append("<tr>\n" + " <td>").append(resume.getUuid()).append("<td>").append(resume.getFullName()).append("</td>\n").append("</tr>\n");
        }

        response.getWriter().write(template.replace("{{ rows }}", rows.toString()));
    }
}
