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
