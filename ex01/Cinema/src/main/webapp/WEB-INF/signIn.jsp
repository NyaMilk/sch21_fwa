<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
<h2>Login</h2>

<% if (request.getAttribute("message") != null) { %>
<div>
    <%= request.getAttribute("message") %>
</div>
<% } %>

<form action="/signIn" method="POST">
    <label>Username
        <input name="username" type="text" placeholder="Username">
    </label>
    <label>Password
        <input name="password" type="password" placeholder="Password">
    </label>
    <button type="submit">Sign in</button>
</form>
</body>

</html>