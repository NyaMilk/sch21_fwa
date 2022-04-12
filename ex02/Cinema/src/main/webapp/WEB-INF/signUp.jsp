<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>signUp</title>
</head>

<body>
<h2>Registration</h2>

<% if (request.getAttribute("message") != null) { %>
<div>
    <%= request.getAttribute("message") %>
</div>
<% } %>

<form action="/signUp" method="POST">
    <label>Username
        <input name="username" type="text" placeholder="Username">
    </label>
    <label>Lastname
        <input name="lastname" type="text" placeholder="Lastname">
    </label>
    <label>Password
        <input name="password" type="password" placeholder="Password">
    </label>
    <label>Phone number
        <input name="phone" type="text" placeholder="Phone number">
    </label>
    <button type="submit">Sign up</button>
</form>

</br>
<a href="/signIn">SignIn</a>
</body>

</html>