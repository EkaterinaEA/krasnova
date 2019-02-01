<%@ page import="java.util.Date" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello, JSP!</h1>
<%= new Date().toString() %>

<table>
    <tbody>
    <% for(String name: Collections.list(request.getHeaderNames())) { %>
    <tr>
        <td> <%= name %> </td>
        <td> <%= request.getHeader(name) %> </td>
    </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>