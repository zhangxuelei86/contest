//发送gpt信息与返回
$(function () {
    // console.log("0000");
    // console.log("scrollHeight:" + $('#chat_his')[0].scrollHeight);
    // console.log('scrollTop:' + $('#chat_his').scrollTop());
    // console.log('innerHeight:' + $('#chat_his').innerHeight())
    // console.log("\n");
    //首先检测当前滚动条是否已经滚动到底部
    if (Math.round($("#chat_his").scrollTop() + $("#chat_his").innerHeight()) >= Math.round($("#chat_his")[0].scrollHeight)) {
        $('#last').hide();
    }
    $("#chat button").click(function () {
            console.log("点击按钮！");
            var chat_input = $("#chat input").val().trim();
            console.log(chat_input)
            if (chat_input !== '' && chat_input !== undefined && chat_input != null) {
                $("#chat button").css({'display': 'none'});
                $(".loading").css({'display': 'flex'});
                $.ajax({
                        type: "post", url: '/chatgpt',
                        data: {
                            prompt: chat_input
                        },
                        success: function (result) {
                            console.log(result);
                            var resultJson = JSON.parse(result);
                            console.log(resultJson);
                            $('#chat_his').append(
                                "<div class='dialogue'>" +
                                "<div class= 'request' >" +
                                "<div><img src='user.png'></div>" +
                                "<div>" + chat_input + "</div >" +
                                "</div >" +
                                "<div class='response'>" +
                                "<div><img src='chatgpt.png'></div>" +
                                "<div>" + resultJson['data'] + "</div>" +
                                "</div>" +
                                "</div>");
                            $("#chat button").css({'display': 'inline-block'});
                            $(".loading").css({'display': 'none'});
                            //当前父div的高度
                            var parentHeight = Math.round($("#chat_his").height());
                            // console.log("parentHeight:" + parentHeight);
                            //当前父div的scrollHeight
                            var parentSH = Math.round($("#chat_his")[0].scrollHeight);
                            // console.log("parentSH:" + parentSH);
                            //判断如果父div的高度小于scrollHeight，就说明当前滚动条未滚动到底部，则显示按钮
                            if (parentHeight < parentSH) {
                                $('#last').show();
                            } else {
                                $('#last').hide();
                            }
                        },
                        error: function (jqXHR) {
                            window.location.href = "/error/error.html?status="+jqXHR.status;
                        }
                    }
                )
            }
        }
    )
})
//到底按钮
$(function () {

    //点击跳到最下面
    $('#last').click(function () {
        var innerHeight = $(".dialogue").height();
        //获取该类所有元素总高度
        var totalHeight = $(".dialogue").map(function () {
            return $(this).outerHeight(true);
        }).get().reduce(function (a, b) {
            return a + b;
        });
        $('#chat_his').scrollTop(totalHeight);
    })

    // 监听滚动条
    $('#chat_his').on("scroll", function () {
        var scrollButton = $("#last");
        // console.log("scrollHeight:" + $('#chat_his')[0].scrollHeight);
        // console.log('scrollTop:' + $('#chat_his').scrollTop());
        // console.log('innerHeight:' + $('#chat_his').innerHeight())
        // console.log("\n");
        if (Math.round($(this).scrollTop() + $(this).innerHeight()) >= Math.round($(this)[0].scrollHeight).toFixed(2)) {
            scrollButton.hide();
        } else {
            scrollButton.show();
        }
    })
})