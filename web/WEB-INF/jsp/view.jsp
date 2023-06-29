<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page import="ru.javawebinar.basejava.model.AbstractSection" %>
<%@ page import="ru.javawebinar.basejava.model.TextSection" %>
<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/theme/light.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/view-resume-styles.css">

    <title>Резюме Григорий Кислин</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<%
    Resume resume = (Resume) request.getAttribute("resume");
    Map<ContactType, String> contacts = resume.getContacts();
    Map<SectionType, AbstractSection> sections = resume.getSections();
%>
<div class="scrollable-panel">
    <div class="form-wrapper">
        <h3 class="full-name"><%=resume.getFullName()%>
        </h3>
        <div class="contacts">
            <%
                for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
            %>
            <div><b title="<%=entry.getKey().name()%>"><%=entry.getKey().getTitle()%>
            </b>:&nbsp;<%=entry.getValue()%>
            </div>
            <%
                }
            %>
        </div>

        <div class="spacer"></div>

        <%
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                String sectionName = entry.getValue().getClass().getSimpleName();
        %>
        <div class="section" title="<%=entry.getKey().name()%>"><%=entry.getKey().getTitle()%>
        </div>
        <% if (sectionName.equals("TextSection")) {
            TextSection section = (TextSection) entry.getValue(); %>
        <div style="padding:32px"><%=section.getText()%>
        </div>
        <% }
            if (sectionName.equals("ListSection")) {
                ListSection section = (ListSection) entry.getValue();
                if (section.getStrings().size() > 0) {
        %>
        <ul>
            <%
                for (String text : section.getStrings()) {
            %>
            <li><%=text%>
            </li>
            <%
                }
            %>
        </ul>
        <% }
        }
            if (sectionName.equals("OrganizationSection")) { %>
        <div class="section-wrapper">
            <div class="job-name"><a class="contact-link"
                                     href="http://javaops.ru/">Java Online Projects</a></div>
            <div class="period-position">
                <div class="period">10/2013 - Сейчас
                </div>
                <div class="position">Автор проекта.</div>
            </div>
            <div class="description">Создание, организация и проведение Java онлайн проектов и стажировок.</div>
        </div>
        <% } %>
        <%
            }
        %>
        <div class="footer-spacer"></div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>