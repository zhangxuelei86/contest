//发送gpt信息与返回
$(function () {
    //首先检测当前滚动条是否已经滚动到底部
    if ($("#chat_his").scrollTop() + $("#chat_his").innerHeight() >= $("#chat_his")[0].scrollHeight) {
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
                            $('#chat_his').append(
                                "<div class='dialogue'>" +
                                "<div class= 'request' >" +
                                "<div><img src='user.png'></div>" +
                                "<div>" + chat_input + "</div >" +
                                "</div >" +
                                "<div class='response'>" +
                                "<div><img src='chatgpt.png'></div>" +
                                "<div>" + result + "</div>" +
                                "</div>" +
                                "</div>");
                            $("#chat button").css({'display': 'inline-block'});
                            $(".loading").css({'display': 'none'});
                            //当前父div的高度
                            var parentHeight = $("#chat_his").height();
                            //当前父div的scrollHeight
                            var parentSH = $("#chat_his")[0].scrollHeight;
                            //判断如果父div的高度小于scrollHeight，就说明当前滚动条未滚动到底部，则显示按钮
                            if (parentHeight < parentSH) {
                                $('#last').show();
                            }
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
        console.log("scrollHeight:" + $('#chat_his')[0].scrollHeight);
        console.log('scrollTop:' + $('#chat_his').scrollTop());
        console.log('innerHeight:' + $('#chat_his').innerHeight())
        if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
            scrollButton.hide();
        } else {
            scrollButton.show();
        }
    })
})