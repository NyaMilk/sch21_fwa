<%@ page import="edu.school21.cinema.models.User" %>
<%@ page import="edu.school21.cinema.models.AuthData" %>
<%@ page import="edu.school21.cinema.models.Photo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <title>Profile</title>
</head>

<body>
<a href="/logout">Logout</a>
<h1>Profile</h1>
<%
    User user = (User) request.getSession().getAttribute("user");
    String path = user.getPhotos().size() != 0 ? user.getPhotos().get(0).getPath() : "img/ava.jpg";
%>
<div class="about">
    <div class="avatar">
        <image src=<%= path %>></image>
        <form action="/images" method="post" enctype="multipart/form-data">
            <label>
                Load
                <input type="file" name="file" multiple>
            </label>
            <input type="submit" value="Send">
        </form>
    </div>
    <p>
        Firstname
        <%= user.getFirstname() %>
    </p>
    <p>
        Lastname
        <%= user.getLastname() %>
    </p>
    <p>
        Phone
        <%= user.getPhone() %>
    </p>
</div>
<table class="auth">
    <tr>
        <th>DATE</th>
        <th>IP</th>
    </tr>
    <% for (AuthData authData : user.getAuthData()) { %>
    <tr>
        <td>
            <%= new SimpleDateFormat("dd MM yyyy HH:mm:ss").format(authData.getTimestamp()) %>
        </td>
        <td>
            <%= authData.getIp() %>
        </td>
    </tr>
    <% } %>
</table>

<table>
    <tr>
        <th>FILE NAME</th>
        <th>SIZE</th>
        <th>MIME</th>
    </tr>
    <% for (Photo photo : user.getPhotos()) { %>
    <tr>
        <td>
            <a href="/images?id=<%= photo.getId() %>">
                <%= photo.getName() %>
            </a>
        </td>
        <td>
            <%= photo.getSize() %>
        </td>
        <td>
            <%= photo.getMime() %>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>