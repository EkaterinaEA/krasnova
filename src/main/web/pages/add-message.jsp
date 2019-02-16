<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Message</title>
     <link rel=stylesheet"type="text/css" href = "/static/styles/main.css">
    <style type="text/css">
        .error{
            color: red;
        }
    </style>
</head>
<body>
<%--@elvariable id="message" type="Message"--%>
<form:form method = "post"
           action = "/add-message"
           enctype = "application/x-www-form-urlencoded"
           modelAttribute = "message"
           >
    <p>
        <label>
            Тема:
            <form: input path = "subject" type = "text" />
        </label>

        <form:errors path="subject" cssClass="error"></form:errors>
    </p>

    <p>
        <label>
            Текст сообщения:
            <form: input path = "text" type = "text" />
        </label>

        <form:errors path ="text" cssClass="error"></form:errors>
    </p>

    <p>
        <label>
            Вложенные файлы:
            <form: input path = "attachedFiles" type = "text" />
        </label>

        <form:errors path ="attachedFiles" cssClass="error"></form:errors>
    </p>

    <p>
        <input> type = "submit">
    </p>

<form: errors assClass="error"/>
</form:form>
</body>
</html>
