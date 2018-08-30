<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <title>validate</title>
</head>
<body>
    <a href="javascript:void(0)" id="validate">ajax请求</a>
    <a href="${CTX}/validate/validate?age=200&email=123&phone=11111&height=110" target="_blank">普通验证</a>
    <a href="${CTX}/validate/group?age=200&email=123&phone=11111&height=110" target="_blank">分组验证</a>
    <script>
        var validate = $('#validate');
        validate.on('click', function () {
            $.ajax({
                cache: false,
                timeout: 5000,
                dataType: 'json',
                type: 'post',
                url: CTX + "/validate/validate?age=200&email=123&phone=11111&height=110",
                data: {},
                success: function (result) {
                    console.log(result)
                },
                error: function (xhr, textStatus, errorThrown) {

                }
            });
        });
    </script>
</body>
</html>
