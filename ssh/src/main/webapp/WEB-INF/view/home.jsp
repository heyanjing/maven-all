<%@page pageEncoding="UTF-8" %>
<html>
<script type="text/javascript" src="/ssh/static/jquery/jquery-1.12.4.min.js"></script>
<body>
    <h2>主页面</h2>
    <a href="javascript:void(0)" id="logout">退出</a>
    <script>
        var logout = $('#logout');
        logout.on('click', function () {
           location.href='/ssh/logout';
        });
    </script>
</body>
</html>
