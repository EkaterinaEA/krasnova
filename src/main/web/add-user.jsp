
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="bean" type="AddUserPageBean" scope="request"/>
<html>
<head>
    <title>Adding user</title>
</head>
<body>
<form action="add-user" method="post">
    <p>
        <label>
            Kind:
        <select name="kind">
            <!--- TODO select from bean -->
            <option value="admin">Admin</option>
            <option value="client">Client</option>
    </select>
        </label>
    </p>
   <p>
       <label> Login:
           <input type="text" name="login" value="${bean.login}"/>
       </label>
       <c:if test = "${not empty bean.loginError}">
           <span style="color: red">
               ${bean.loginError}
           </span>
       </c:if>

   </p>
    <p>
        <input type="submit">
    </p>
</form>
</body>
</html>
