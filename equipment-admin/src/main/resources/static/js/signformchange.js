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
    $.ajax({
        type: "post",
        url: "/user/login",
        data: {
            "username": username,
            "password": password,
        },
        success: function(r) {
            if (r.code == 0) {
                var url =  window.location.href;
                url = url.replace("#","");
                location.replace(url);
            }else {
                alert("用户名密码错误");
            }
        }
    });
}

function doRegist(){
    var loginName = $("#registeruser").val();
    var password = $("#registerpwd").val();
    var password2 = $("#registerrepwd").val();
    if(password != password2){
        $("#tag").html("* 两次密码不一致");
        return ;
    }
    if(loginName == ""){
        return ;
    }
    $.ajax({
        type: "post",
        url: "/user/regist",
        data: {
            "loginName": loginName,
            "password": password,
        },
        success: function(r) {
            if (r.code == 0) {
                alert("注册成功");
                $("#username").val(loginName);
                $("#password").val(password);
                doLogin();
            }else {
                alert(r.msg);
            }
        },error:function(e){
          console.log(e)
        }
    });
}

function comparePwd(){
    var password = $("#registerpwd").val();
    var password2 = $("#registerrepwd").val();
    if(password.length < 5 || password.length > 20){
        $("#tag").html("* 密码在5-20位之间");
        return;
    }else{
        $("#tag").html("");
    }
    if(password != password2){
        $("#tag").html("* 两次密码不一致");
    }else{
        $("#tag").html("");
    }
}