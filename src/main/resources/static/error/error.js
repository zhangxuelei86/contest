$(function (){
    var urlParams = new URLSearchParams(window.location.search);
    var status = urlParams.get('status');
    console.log(status);
    $('#status').text(status);
})