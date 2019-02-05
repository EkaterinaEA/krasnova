
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
           <input type="text" name="login"/>
       </label>
   </p>

    <p>
        <input type="submit">
    </p>
</form>
</body>
</html>
