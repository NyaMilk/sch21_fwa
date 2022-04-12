<%@ page import="edu.school21.cinema.models.User" %>
<%@ page import="edu.school21.cinema.models.AuthData" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
<a href="/logout">Logout</a>
<h1>Profile</h1>
<% User user = (User) request.getSession().getAttribute("user"); %>
<p>
    <%=user.getFirstname()%>
</p>
<%--<br/>--%>
<p>
    <%=user.getLastname()%>
</p>
<%--<br/>--%>
<p>
    <%=user.getPhone()%>
</p>
<%--<br/>--%>
<table>
    <tr>
        <th>Date</th>
        <th>Ip</th>
    </tr>
    <% for (AuthData authData : user.getAuthData()) { %>
    <tr>
        <td>
            <%=authData.getIp()%>
        </td>
        <td>
            <%=authData.getTimestamp()%>
        </td>
    </tr>
    <% } %>
</table>

</body>
</html>