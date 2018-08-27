<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<html>
<script type="text/javascript" src="${JQUERY}/jquery-1.12.4.min.js"></script>
<body>
    <h2>temp页面</h2>
    <a href="javascript:void(0)" id="logout">退出</a>
    <a href="javascript:void(0)" id="send">发出请求</a>
    <input id="kickoutInfo" type="hidden" value="${kickoutInfo}">
    <script type="text/javascript" src="${JS}/home.js"></script>
</body>
</html>
