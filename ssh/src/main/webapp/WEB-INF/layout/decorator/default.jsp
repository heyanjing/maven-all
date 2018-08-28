<%@page pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/layout/taglib/jspTaglib.jsp" %>
<!doctype html>
<html>
<head>
    <title><c:if test="${DEBUG}">${consume_times}</c:if><sitemesh:write property='title'/></title>
    <%@include file="/WEB-INF/layout/taglib/head.jsp" %>
    <sitemesh:write property='head'/>
</head>
<body>
    <sitemesh:write property='body'/> <%-- 加入自定义标签，只要页面中有myTag标签就会被装饰
   <myTag>myTag--default  <sitemesh:write property="myTag"/></myTag>
    --%>
</body>
</html>
