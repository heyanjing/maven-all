<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <title>index</title>
</head>
<body>
    <h2>Hello World!卧槽</h2>
    <form method="post" action="${CTX}/login">
        用户
        <input type="text" name="userName" value="admin">
        密码
        <input type="password" name="password" value="admin">
        <button type="submit">提交</button>
    </form>
    <h1 style="color: red;">${msg}</h1>
    <myTag>myTag--index</myTag>
</body>
</html>
