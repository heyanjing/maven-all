<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <title>home</title>
</head>
<body>
    <h2>主页面${now}</h2>
    <a href="javascript:void(0)" id="send">ajax发出请求</a>
    <a href="${CTX}/validate/">jsr303验证页面</a>
    <input id="kickoutInfo" type="hidden" value="${kickoutInfo}">
    <script type="text/javascript" src="${JS}/home.js?${V}"></script>
</body>
</html>
