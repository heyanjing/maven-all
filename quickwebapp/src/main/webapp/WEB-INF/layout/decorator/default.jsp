<%@page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/layout/taglib/jspTaglib.jsp" %>
<!doctype html>
<html>
<head>
    <title><c:if test="${DEBUG}">${consume_times}ms </c:if><sitemesh:write property='title'/></title>
    <%@include file="/WEB-INF/layout/taglib/head.jsp" %>
    <sitemesh:write property='head'/>
</head>
<body>
    <a href="javascript:void(0)" id="logout">退出</a>
    <br>
    <sitemesh:write property='body'/> <%-- 加入自定义标签，只要页面中有myTag标签就会被装饰
   <myTag>myTag--default  <sitemesh:write property="myTag"/></myTag>
    --%>
    <script>
        var logout = $('#logout');
        logout.on('click', function () {
            location.href = CTX + '/logout';
        });
    </script>
</body>
</html>
