/**
 * Created by heyanjing on 2018/8/24 17:34.
 */

var logout = $('#logout');
logout.on('click', function () {
    location.href = '/ssh/logout';
});

var pageSize = 2, pageIndex = 0;
$('#send').on('click', function () {
    var tmep=pageIndex++;
    $.ajax({
        cache: false,
        timeout: 5000,
        dataType: 'json',
        type: 'post',
        url: "/ssh/pageBySql",
        data: {
            pageSize: pageSize,
            pageIndex: tmep
        },
        success: function (result) {
            console.log(result);
        },
        error: function (xhr,textStatus,errorThrown) {
            console.log(xhr);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
});