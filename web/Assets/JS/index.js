/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("#login").hide();

    $("#log").click(function () {
        $("#register").hide();
        $("#login").show();
        $("#reg").removeClass("active");
        $("#log").addClass("active");
    });
    $("#reg").click(function () {
        $("#login").hide();
        $("#register").show();
        $("#log").removeClass("active");
        $("#reg").addClass("active");
    });

    $("#registerButton").click(function () {
        var username = $("#username").val();
        var email = $("#email").val();
        var password = $("#password").val();
        $.ajax({
            url: "MainServlet",
            method: "POST",
            data: {
                username: username,
                email: email,
                password: password,
                action: "register"
            },
            datatype: "application/json",
            success: function (result) {
                if (result.response == "Success") {
                    alert("Successfully Registered!");
                    username = "";
                    email = "";
                    password = "";
                    $("#register").hide();
                    $("#login").show();
                    $("#register").removeClass("active");
                    $("#login").addClass("active");
                }
//              console.log(result.response);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });

    $("#loginButton").click(function () {
        var username = $("#loginUsername").val();
        var password = $("#loginPassword").val();
        $.ajax({
            url: "MainServlet",
            method: "POST",
            data: {
                username: username,
                password: password,
                action: "login"
            },
            datatype:"application/json",
            success: function (result) {
                if(result.role=="2"){
                    window.location.href="TraineePage.jsp";
                }else if(result.role=="1"){
//                    alert(result.regId);
//                    $.session("regId",result.regId);
                    window.location.href="AdminHomepage.jsp";
                }else if(result.password=="null"){
                    alert("Please check the password");
                }else if(result.user=="unregistered"){
                    alert("Please Register");
                }
                
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
});

//function noBack(){
//    alert(1);
//}
