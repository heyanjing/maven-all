<%@page pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <title>默认user页面title中的内容: <sitemesh:write property='title'/></title>
    <meta name='description' content='默认user页面head中的内容'>
    <sitemesh:write property='head'/>
</head>
<body>
    <sitemesh:write property='body'/>
    <div>默认user页面body中的内容</div>
</body>
</html>
