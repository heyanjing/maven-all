/**
 * Created by heyanjing on 2018/8/24 17:34.
 */
console.log(DEBUG)

var pageSize = 2, pageIndex = 0;
$('#send').on('click', function () {
    var tmep = pageIndex++;
    $.ajax({
        cache: false,
        timeout: 5000,
        dataType: 'json',
        type: 'post',
        url: CTX + '/home/pageBySql',
        data: {
            pageSize: pageSize,
            pageIndex: tmep
        },
        success: function (result) {
            console.log(result);
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(xhr);
            console.log(textStatus);
            console.log(errorThrown);
            if (xhr.status === 403) {
                alert(xhr.responseJSON.msg);
                location.href = CTX + '/';
            } else if (xhr.status === 409) {
                alert(xhr.responseJSON.msg);
                location.href = CTX + '/';
            }
        }
    });
});

var kickoutInfoVal = $('#kickoutInfo').val();
if (kickoutInfoVal) {
    alert(kickoutInfoVal);
    location.href = CTX + '/';
}