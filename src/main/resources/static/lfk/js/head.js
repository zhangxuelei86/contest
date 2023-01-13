/*跳转到主页*/
$(function () {
    $("#logo").click(function () {
        window.location.href = "/index.html"
    })
});
/*跳转到VR*/
$(function () {
    $("#VR").click(function () {
        window.location.href = "/lfk/vr.html"
    })
});
/*跳转about界面*/
$(function () {
    $("#About").click(function () {
        window.location.href = "/aboutus/aboutus.html"
    })
});
/*显示分类*/
$(function () {
    $("#list").click(function () {
        $("#D-list").toggle()
    })
});
/*跳转到分类*/
$(function () {
    $("#E-list").click(function () {
        window.location.href = "/lfk/E-content.html"
    })
});
$(function () {
    $("#C-list").click(function () {
        window.location.href = "/lfk/C-content.html"
    })
});
$(function () {
    $("#L-list").click(function () {
        window.location.href = "/lfk/L-content.html"
    })
});