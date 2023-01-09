$(function () {
    $("#chat button").click(function () {
        console.log("点击按钮！");
        var chat_input = $("#chat input").val().trim();
        console.log(chat_input)
        if (chat_input !== '' && chat_input !== undefined && chat_input != null) {
            $("#chat button").css({ 'display': 'none' });
            $(".loading").css({ 'display': 'flex' });
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
                        $("#chat button").css({ 'display': 'inline-block' });
                        $(".loading").css({ 'display': 'none' });
                }
            }
            )
        }
    }
    )
})