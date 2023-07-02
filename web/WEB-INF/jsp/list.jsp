<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/theme/light.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/resume-list-styles.css">
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="scrollable-panel">
    <div class="table-wrapper">
        <div class="add-resume">
            <a class="no-underline-anchor" href="edit?mode=add">
                <img src="img/add.png" alt="">
            </a>
            <a class="text-anchor" href="edit?mode=add">
                <p class="add-resume-title">Добавить резюме</p>
            </a>
        </div>
        <div class="resumes-list">
            <table style="width: 100%">
                <tr class="t-header">
                    <th class="name-column">Имя</th>
                    <th class="info-column">Эл.почта</th>
                    <th class="img-column">#</th>
                </tr>
                <%
                    for (Resume resume : (List<Resume>) request.getAttribute("resumes")) {
                %>
                <tr class="t-body">
                    <td class="name-column">
                        <a class="contact-link"
                           href="view?uuid=<%=resume.getUuid()%>"><%=resume.getFullName()%>
                        </a>
                    </td>
                    <td class="info-column">
                        <% String email = resume.getContact(ContactType.EMAIL);
                            if (email != null) { %>
                        <img src="img/email.png"
                             alt="">&nbsp;<a class="contact-link"
                                             href='mailto:<%=email%>'><%=email%>
                    </a>
                        <% } %>
                    </td>
                    <td class="img-column">
                        <a class="no-underline-anchor" href="edit?uuid=<%=resume.getUuid()%>&mode=edit">
                            <img src="img/pencil.png" title="Изменить" alt="Изменить">
                        </a>
                        &nbsp
                        <a class="no-underline-anchor" href="edit?delete&uuid=<%=resume.getUuid()%>&mode=delete">
                            <img src="img/delete.png" title="Удалить" alt="Удалить">
                        </a>

                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
