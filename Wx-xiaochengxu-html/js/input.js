(function(){


       $("html").niceScroll({
           styler: "fb",
           cursorcolor: "#1b93e1",
           cursorwidth: '6',
           cursorborderradius: '10px',
           background: '#FFFFFF',
           spacebarenabled: false,
           cursorborder: '0',
           zindex: '1000'
       });

       $(".scrollbar1").niceScroll({
           styler: "fb",
           cursorcolor: "#1b93e1",
           cursorwidth: '6',
           cursorborderradius: '0',
           autohidemode: 'false',
           background: '#FFFFFF',
           spacebarenabled: false,
           cursorborder: '0'
       });



       $(".scrollbar1").getNiceScroll();
       if ($('body').hasClass('scrollbar1-collapsed')) {
           $(".scrollbar1").getNiceScroll().hide();
       }




/**
 * 添加工程页
 * 
 */

//获取工程状态后显示在添加页
$.ajax({
    type: "GET",
    url: "http://127.0.0.1:8181/getProStatus.do",
    async: false,
    success: data => {
        if (data == null) return false;
        var ev = eval('(' + data + ')');
        for (var p in ev) {
            $("#test").append(
                '<div class="checkbox-inline1"><label><input name="checkbox" type="checkbox" class="check1" value="' + ev[p].id + '">' + ev[p].status + '</label></div>'
            )
        }

    }
})


//获取城市后显示在添加页
$.ajax({
    type: "GET",
    url: "http://127.0.0.1:8181/getAllCity.do",
    async: false,
    success: data => {
        if (data == null) return false;
        var ev = eval('(' + data + ')');
        for (var p in ev) {
            var cityid = ev[p].id;
            $("#opsion").append(
                '<option value="' + cityid + '">' + ev[p].cityName + '</option>'
            )
        }

    }
})







$('#test').find('input[type=checkbox]').bind('click', function () {
    $('#test').find('input[type=checkbox]').not(this).attr("checked", false);
});

})(jQuery);

//提交表单

function submit(){

    var city_location = $("#focusedinput").val();
    var pro_name = $("#disabledinput").val();
    var pro_message = $("#inputPassword").val();
    var pro_status = $(".check1:checked").val();
    var city_name = $("#opsion option:selected").val();
    if (city_location == null || pro_name == null || pro_message == null || pro_status == null || city_name == null ){
        alert("请完善信息")
    }else{

        $.ajax({
            type: "POST",
            url: "http://127.0.0.1:8181/addlocation.do",

            data: {
                "cityLocation": city_location,
                "proName": pro_name,
                "proMessage":pro_message,
                "proStatus":pro_status,
                "cityName":city_name
            },
            success: function (data) {
                if (data == null) return false;
                alert(data.Message)
                location.reload();
            }
        })

    }
    
}

function reloadPage(){
    location.reload();
}


function submitCity(){

    var cityName = $("#cityInput").val();
    cityName == "" ? alert("请添加城市名") : sendAjax(cityName);

}

function sendAjax(cityName){

    $.ajax({
        type: "POST",
        url: "http://127.0.0.1:8181/addCity.do",
        dataType: 'json',
        data: {
            "cityName":cityName
        },
        success: function (data) {
            if (data == null) return false;
            alert(data.message)
            location.reload();
        }
    })

}