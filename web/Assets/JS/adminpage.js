/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//$("#logout").click(function () {
//    window.location.href;
//});


$(document).ready(function () {
    $("#assignments").hide();
    $("#submissions").hide();
    $("#assgSubmissions").hide();
});

$("#assg").click(function () {
//    count++;
    $("#assignments").hide();
    $("#submissions").hide();
    $("#assgSubmissions").hide();
    $("#add").show();
});

var files;
var filePath;
$("#filePath").on('change', function (event) {
    files = event.target.files;
});
$("#btnUpload").on("click", prepareUpload);
function prepareUpload(event)
{
//    files=$("#filePath").files[0];

    var aname = $("#aname").val();
    var desc = $("#desc").val();
    var deadline = $("#deadline").val();
    var issuer = $("#issuer").val();
//    files = event.target.files;
    event.stopPropagation();
    event.preventDefault();
    var data = new FormData();
    $.each(files, function (key, value) {
        data.append(key, value);
    });
    data.append("aname", aname);
    data.append("deadline", deadline);
    data.append("desc", desc);
    data.append("issuer", issuer);
    $.ajax({
        url: "UploadServlet",
        type: "POST",
        data: data,
        cache: false,
        processData: false,
        contentType: false,
        success: function (data, textStatus, jqXHR) {
            alert(data.status);
        },
        error: function (jqXHR, textStatus, errorThrown) {}
    });
}
var count = 0;

$("#view").click(function () {
    $("#add").hide();
    $("#submissions").hide();
    $("#assgSubmissions").hide();
    $("#assg").removeClass("active");

    $('#assgTable').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            url: 'MainServlet',
            type: 'POST',
            data: {
                action: "getAssignments"
            }
        }, "columns": [
            {"data": "id"},
            {"data": "aname"},
            {"data": "description"},
            {"data": "deadline"},
            {"data": "issuer"},
            {"data": "fileName"}
        ]
    });
    if (count != 0) {
        var ta = $('#assgTable').DataTable();
        ta.destroy();
//        console.log("hello");
    }
//    var ta = $('#assgTable').DataTable();

    $("#assignments").show();
});

//$(document).ready(function () {
//
//    var t = $('#assgSubTable').DataTable();
//    $.ajax({
//        url: "MainServlet",
//        type: "POST",
//        datatype: "json",
//        data: {
//            action: "traineeAssg"
//        },
//        success: function (data) {
//            for (var i = 0; i < data.length; i++) {
//                t.row.add([
//                    data[i].id,
//                    data[i].name,
//                    data[i].description,
//                    data[i].deadline,
//                    data[i].issuer,
//                    '<input type=button  value="View">'
//                ]).draw(false);
//            }
//        },
//        error: function (data) {
//            console.log(data);
//        }
//    });
//
//
////    $("#fileName").on("change", prepareUpload);
////    $("body").on("change", "input[type='file']", prepareUpload);
//});

$("#sub").click(function () {
    count++;
    $("#assignments").hide();
    $("#add").hide();
    $("#assgSubmissions").hide();

    var t = $('#assgSubTable').DataTable();
    t.clear();
    $.ajax({
        url: "MainServlet",
        type: "POST",
        datatype: "json",
        data: {
            action: "traineeAssg"
        },
        success: function (data) {
//            t.row.empty();
            for (var i = 0; i < data.length; i++) {
                t.row.add([
                    data[i].id,
                    data[i].name,
                    data[i].description,
                    data[i].deadline,
                    data[i].issuer,
                    '<input type=button  class="viewBtn" value="View" data=' + data[i].id + '>'
                ]).draw(false);
            }
        },
        error: function (data) {
            console.log(data);
        }
    });

    $("#submissions").show();
});

$("body").on("click", ".viewBtn", function (event) {
    var id = event.target.outerHTML.split("data")[1].split("\"")[1];
    $.ajax({
        url: "MainServlet",
        type: "POST",
        datatype: "json",
        data: {
            id: id,
            action: "getSubmissions"
        },
        success: function (data) {
//            console.log(data);
            var e=$("#assgSubmissionTable").DataTable();
            e.clear();
            for(var i=0; i<data.length; i++){
                e.row.add([
                    data[i].subId,
                    data[i].assgName,
                    data[i].username,
                    data[i].filePath
                ]).draw(false);
//                console.log(data[i].filePath);
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
    $("#submissions").hide();
    $("#assgSubmissions").show();
});

