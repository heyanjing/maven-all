<%@page pageEncoding="UTF-8" %>
<%@page import="com.he.maven.ssh.Constant" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="DEBUG" value="<%=Constant.APP_DEBUG%>"/> <c:if test="${DEBUG}"> <c:set var="V" value="<%=Math.random()%>"/> </c:if>

<c:set var="JQUERY" value="${LIB}/jquery"/>



