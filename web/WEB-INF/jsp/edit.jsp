<%@ page import="ru.javawebinar.basejava.model.*" %>
<%@ page import="ru.javawebinar.basejava.util.DateUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
%>
<form action="edit" method="post">
    <div style="text-align: center"><input type="submit" name="actionSave" value="Сохранить"></div>
    <hr/>
    <input name="uuid" type="hidden" value="<%=resume.getUuid()%>">
    <input name="mode" type="hidden" value="<%=request.getAttribute("mode")%>">
    <div class="scrollable-panel">
        <div class="form-wrapper">
            <label>
                <input name="full_name" required type="text" size="120" value="<%=resume.getFullName()%>">
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
                </b>:&nbsp;<label>
                    <input name="contact<%=contactType.name()%>" type="text" size="64" value="<%=contact%>">
                </label>
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
                <input name="section<%=sectionType.name()%>" type="text" size="120"
                       value="<%=textSection.getText()%>">
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
                if (sectionName.equals("OrganizationSection")) {
                    OrganizationSection argSection = (OrganizationSection) section; %>
            <%
                String hash = Integer.toHexString(argSection.hashCode());
            %>
            <div style="text-align: center"><input type="submit" name="actionAddOrg<%=sectionType.name()%>"
                                                   value="Добавить организацию"></div>
            <input name="section<%=sectionType.name()%>" type="hidden" value="<%=hash%>">
            <%
                int numOrg = 0;
                for (Organization org : argSection.getOrganizations()) {
            %>
            <div class="section-wrapper">
                <div style="border-style:solid;padding:4px">
                    <label>
                        <input type="checkbox" name="orgDelete<%=hash%>_<%=numOrg%>">
                    </label>
                    <label for="orgDelete<%=hash%>_<%=numOrg%>">Удалить организацию</label>
                    <div><b>Имя организации</b>:&nbsp;<label>
                        <input name="orgName<%=hash%>_<%=numOrg%>" type="text" size="64" required
                               value="<%=org.getHead().getTitle()%>">
                    </label></div>
                    <div><b>Сайт организации</b>:&nbsp;<label>
                        <input name="orgSite<%=hash%>_<%=numOrg%>" type="text" size="64"
                               value="<%=org.getHead().getWebsite()%>">
                    </label></div>
                    <div style="text-align: center"><input type="submit" name="actionAddPeriod<%=hash%>"
                                                           value="Добавить период"></div>
                    <%
                        int numPeriod = 0;
                        for (Period period : org.getPeriods()) {
                    %>
                    <div style="border-style:dotted;padding:4px">
                        <label>
                            <input type="checkbox" name="periodDelete<%=hash%>_<%=numOrg%>_<%=numPeriod%>">
                        </label>
                        <label for="periodDelete<%=hash%>_<%=numOrg%>_<%=numPeriod%>">Удалить период</label>
                        <div><b>Начальная дата</b>:&nbsp;<label>
                            <input name="periodStart<%=hash%>_<%=numOrg%>_<%=numPeriod%>" type="text"
                                   size="8"
                                   value="<%=DateUtil.toString(period.getStartDate())%>">
                        </label>
                        </div>
                        <div><b>Конечная дата</b>:&nbsp;<label>
                            <input name="periodEnd<%=hash%>_<%=numOrg%>_<%=numPeriod%>" type="text"
                                   size="8"
                                   value="<%=DateUtil.toString(period.getEndDate())%>">
                        </label>
                        </div>
                        <div><b>Заголовок</b>:&nbsp;<label>
                            <input name="periodTitle<%=hash%>_<%=numOrg%>_<%=numPeriod%>" type="text"
                                   size="80"
                                   value="<%=period.getTitle()%>">
                        </label></div>
                        <div><b>Описание</b>:&nbsp;<label>
                            <input name="periodDesc<%=hash%>_<%=numOrg%>_<%=numPeriod%>" type="text"
                                   size="100"
                                   value="<%=period.getDescription()%>">
                        </label></div>
                    </div>
                    <%
                            numPeriod++;
                        } %>
                </div>
            </div>
            <% numOrg++;
            }
            }
            }
            %>
            <div class="section-wrapper"></div>
            <div class="footer-spacer"></div>
        </div>
    </div>
</form>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>