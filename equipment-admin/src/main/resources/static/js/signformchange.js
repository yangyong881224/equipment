$(function ()
{
    $('.change a').click(function ()
    {
        $('.signform').animate({height: 'toggle', opacity: 'toggle'}, 'slow');
    });


})

function login() {
    document.getElementById('signform').style.display="block";
    document.getElementsByClassName("mask")[0].style.display = "block";
}
function regist() {
    document.getElementById('registerform').style.display="block";
    document.getElementsByClassName("mask")[0].style.display = "block";
}
function signclose() {
    document.getElementById('registerform').style.display="none"
    document.getElementById('signform').style.display="none"
    document.getElementsByClassName("mask")[0].style.display = "none";

}

function doLogin(){
    var username = $("#username").val();
    var password = $("#password").val();
    console.log(username + "    "+ password)
    $.ajax({
        type: "post",
        url: "login",
        data: {
            "username": username,
            "password": password,
        },
        success: function(r) {
            if (r.code == 0) {
                location.href = 'index';
            }else {
                $.modal.msg(r.msg);
            }
        }
    });
}
