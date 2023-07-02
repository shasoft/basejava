<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="ru.javawebinar.basejava.model.*" %>
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
<form action="edit" method="post">
    <center><input type="submit" value="Сохранить"></center>
    <hr/>
    <input name="uuid" type="hidden" value="<%=resume.getUuid()%>">
    <input name="mode" type="hidden" value="<%=request.getAttribute("mode")%>">
    <div class="scrollable-panel">
        <div class="form-wrapper">
            <label>
                <input name="full_name" type="text" size="120" value="<%=resume.getFullName()%>">
            </label>
            <div class="contacts">
                <%
                    for (ContactType contactType : ContactType.values()) {
                        String contact = resume.getContact(contactType);
                        if (contact == null) {
                            contact = "";
                        }
                %>
                <div><b title="<%=contactType.name()%>"><%=contactType.getTitle()%>
                </b>:&nbsp;<input name="contact<%=contactType.name()%>" type="text" size="64" value="<%=contact%>">
                </div>
                <%
                    }
                %>
            </div>

            <div class="spacer"></div>

            <%
                for (SectionType sectionType : SectionType.values()) {
                    AbstractSection section = resume.getSection(sectionType);
                    if (section == null) {
                        switch (sectionType) {
                            case PERSONAL:
                            case OBJECTIVE: {
                                section = new TextSection("");
                            }
                            break;
                            case ACHIEVEMENT:
                            case QUALIFICATIONS: {
                                section = new ListSection();
                            }
                            break;
                            case EXPERIENCE:
                            case EDUCATION: {
                                section = new OrganizationSection();
                            }
                            break;
                        }
                    }
                    String sectionName = section.getClass().getSimpleName();
            %>
            <div class="section" title="<%=sectionType.name()%>"><%=sectionType.getTitle()%>
            </div>
            <% if (sectionName.equals("TextSection")) {
                TextSection textSection = (TextSection) section; %>
            <div style="padding:32px"><label>
                <input name="section<%=sectionType.name()%>" type="text" size="120" value="<%=textSection.getText()%>">
            </label>
            </div>
            <% }
                if (sectionName.equals("ListSection")) {
                    ListSection listSection = (ListSection) section;
                    String value = String.join("\n", listSection.getStrings());
            %>
            <label>
                <textarea name="section<%=sectionType.name()%>" rows="16" cols="120"><%=value%></textarea>
            </label>
            <%
                }
                if (sectionName.equals("OrganizationSection")) { %>
            <!--
            <div class="section-wrapper">
                <div class="job-name"><a class="contact-link"
                                         href="http://javaops.ru/">Java Online Projects</a></div>
                <div class="period-position">
                    <div class="period">10/2013 - Сейчас
                    </div>
                    <div class="position">Автор проекта.</div>
                </div>
                <div class="description">Создание, организация и проведение Java онлайн проектов и стажировок.</div>
            </div>-->
            <% } %>
            <%
                }
            %>
            <div class="footer-spacer"></div>
        </div>
    </div>
</form>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>