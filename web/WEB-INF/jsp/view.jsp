<%@ page import="java.util.Map" %>
<%@ page import="ru.javawebinar.basejava.model.*" %>
<%@ page import="ru.javawebinar.basejava.util.DateUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
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
            if (sectionName.equals("OrganizationSection")) {
                OrganizationSection section = (OrganizationSection) entry.getValue();
                for (Organization org : section.getOrganizations()) {
        %>
        <div class="section-wrapper">
            <div class="job-name">
                <% if (!org.getHead().getWebsite().isEmpty()) { %>
                <a class="contact-link" href="<%=org.getHead().getWebsite()%>"><%=org.getHead().getTitle()%>
                </a>
                <% } else {%>
                <%=org.getHead().getTitle()%>
                <% } %>
            </div>
            <% for (Period period : org.getPeriods()) { %>
            <div class="period-position">
                <div class="period"><%=DateUtil.toString(period.getStartDate())%>
                    - <%=DateUtil.toString(period.getEndDate())%>
                </div>
                <div class="position"><%=period.getTitle()%>
                </div>
            </div>
            <div class="description"><%=period.getDescription()%>
            </div>
            <% } %>
        </div>
        <% }
        }
        }
        %>
        <div class="footer-spacer"></div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>