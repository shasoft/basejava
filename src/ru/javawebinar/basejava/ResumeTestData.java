package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("1", "Григорий Кислин");

        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "gkislin");
        resume.addContact(ContactType.GITHUB, "gkislin");
        resume.addContact(ContactType.STATCKOVERFLOW, "548473");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        ListSection achievement = (ListSection) resume.addSection(SectionType.ACHIEVEMENT, new ListSection());
        achievement.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        achievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");
        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievement.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        ListSection qualifications = (ListSection) resume.addSection(SectionType.QUALIFICATIONS, new ListSection());
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualifications.add("Python: Django.");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        qualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer");
        qualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualifications.add("Родной русский, английский \"upper intermediate\"");

        Organization organization;

        OrganizationSection experience = (OrganizationSection) resume.addSection(SectionType.EXPERIENCE, new OrganizationSection());
        organization = experience.add(new Organization("Java Online Projects", "http://javaops.ru/"));
        organization.addPeriod(new Period(
                "Автор проекта.",
                LocalDate.of(2013, 10, 1),
                LocalDate.now(),
                "Создание, организация и проведение Java онлайн проектов и стажировок."
        ));
        organization = experience.add(new Organization("Wrike", "https://www.wrike.com/"));
        organization.addPeriod(new Period(
                "Старший разработчик (backend)",
                LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1),
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."
        ));
        organization = experience.add(new Organization("RIT Center"));
        organization.addPeriod(new Period(
                "Java архитектор",
                LocalDate.of(2012, 4, 1),
                LocalDate.of(2014, 10, 1),
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"
        ));
        organization = experience.add(new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/"));
        organization.addPeriod(new Period(
                "Ведущий программист",
                LocalDate.of(2010, 12, 1),
                LocalDate.of(2012, 4, 1),
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."
        ));
        organization = experience.add(new Organization("Yota", "https://www.yota.ru/"));
        organization.addPeriod(new Period(
                "Ведущий специалист",
                LocalDate.of(2008, 6, 1),
                LocalDate.of(2010, 12, 1),
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"
        ));
        organization = experience.add(new Organization("Enkata", "http://enkata.com/"));
        organization.addPeriod(new Period(
                "Разработчик ПО",
                LocalDate.of(2007, 3, 1),
                LocalDate.of(2008, 6, 1),
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."
        ));
        organization = experience.add(new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html"));
        organization.addPeriod(new Period(
                "Разработчик ПО",
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2007, 2, 1),
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."
        ));
        organization = experience.add(new Organization("Alcatel", "http://www.alcatel.ru/"));
        organization.addPeriod(new Period(
                "Инженер по аппаратному и программному тестированию",
                LocalDate.of(1997, 9, 1),
                LocalDate.of(2005, 1, 1),
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."
        ));

        OrganizationSection education;
        education = (OrganizationSection) resume.addSection(SectionType.EDUCATION, new OrganizationSection());
        organization = education.add(new Organization("Coursera", "https://www.coursera.org/course/progfun"));
        organization.addPeriod(new Period(
                "'Functional Programming Principles in Scala' by Martin Odersky",
                LocalDate.of(2013, 3, 1),
                LocalDate.of(2013, 5, 1)
        ));
        organization = education.add(new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"));
        organization.addPeriod(new Period(
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'",
                LocalDate.of(2011, 3, 1),
                LocalDate.of(2011, 4, 1)
        ));
        organization = education.add(new Organization("Siemens AG", "http://www.siemens.ru/"));
        organization.addPeriod(new Period(
                "3 месяца обучения мобильным IN сетям (Берлин)",
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2005, 4, 1)
        ));
        organization = education.add(new Organization("Alcatel", "http://www.alcatel.ru/"));
        organization.addPeriod(new Period(
                "6 месяцев обучения цифровым телефонным сетям (Москва)",
                LocalDate.of(1997, 9, 1),
                LocalDate.of(1998, 3, 1)
        ));
        organization = education.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/"));
        organization.addPeriod(new Period(
                "Аспирантура (программист С, С++)",
                LocalDate.of(1993, 9, 1),
                LocalDate.of(1996, 7, 1)
        ));
        organization.addPeriod(new Period(
                "Инженер (программист Fortran, C)",
                LocalDate.of(1987, 9, 1),
                LocalDate.of(1993, 7, 1)
        ));
        organization = education.add(new Organization("Заочная физико-техническая школа при МФТИ", "https://mipt.ru/"));
        organization.addPeriod(new Period(
                "Закончил с отличием",
                LocalDate.of(1984, 9, 1),
                LocalDate.of(1987, 6, 1)
        ));

        resume.println();
    }
}
