<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/layout/taglib/jspTaglib.jsp" %>
<script type="text/javascript">
    window.CTX = '${CTX}';
    window.IMG = '${IMG}';
    window.JS = '${JS}';
    window.CSS = '${CSS}';
    window.LIB = '${LIB}';
    <c:if test="${DEBUG}">
    window.DEBUG = '${DEBUG}';
    </c:if>
</script>
