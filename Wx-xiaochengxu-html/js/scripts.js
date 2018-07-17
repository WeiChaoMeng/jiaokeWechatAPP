(function() {
    "use strict";

    // custom scrollbar

    $("html").niceScroll({styler:"fb",cursorcolor:"#1b93e1", cursorwidth: '6', cursorborderradius: '10px', background: '#FFFFFF', spacebarenabled:false, cursorborder: '0',  zindex: '1000'});

    $(".scrollbar1").niceScroll({styler:"fb",cursorcolor:"#1b93e1", cursorwidth: '6', cursorborderradius: '0',autohidemode: 'false', background: '#FFFFFF', spacebarenabled:false, cursorborder: '0'});

	
	
    $(".scrollbar1").getNiceScroll();
    if ($('body').hasClass('scrollbar1-collapsed')) {
        $(".scrollbar1").getNiceScroll().hide();
    }


//获取页面头部各个模块的数量
$.ajax({
    type:"GET",
    url: "http://127.0.0.1:8181/getIndexHeard.do",
    async: false,
    success:data => {
        if(data == null) return false;
        var ev = eval('(' + data + ')');
        $("#managerNum").text(ev.managerNum);
        $("#cityNum").text(ev.cityNum);
        $("#projectNum").text(ev.projectNum);
        $("#finishProNum").text(ev.finishProNum);
    }
})

//获取各城市项目数
$.ajax({
    type:"GET",
    url: "http://127.0.0.1:8181/getIndexCity.do",
    async: false,
    success:data =>{
        if (data == null) return false;
        var ev = eval('(' + data + ')');
        for (var p in ev ){
            $(".list-group").append(
                '<a href="#" class="list-group-item">' 
                +'<i class="fa fa-twitter fa-fw"></i>' + ev[p].cityName
				+		'<span class="pull-right text-muted small">'
                +'<em>' + ev[p].cityProNum + '个项目</em>'
                +    '</span>'
                + '</a> '
            )
        }

    }
})

//获取首页项目信息  
    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:8181/getIndexProject.do",
        async: false,
        success: data => {
            if (data == null) return false;
            var ev = eval('(' + data + ')');
            for (var p in ev) {
                var statusStr = (ev[p].statusName == "修建完成") ? '"label label-success"' : '"label label-danger"';
                $("#proList").append(
                    '<tr>'
                    +    '<th scope="row">' + ev[p].id + '</th>'
                    +    '<td>' + ev[p].proName + '</td>'
                    +       '<td>'
                    +          '<span ' + 'class=' + statusStr  + ' >' + ev[p].statusName +'</span>'
                    +       '</td>'
                    +    '<td>'
                    +        '<h5>' + ev[p].cityName 
                    +        '</h5>'
                    +    '</td>'
                    +'</tr>'
                )
            }

        }
    })




$('#test').find('input[type=checkbox]').bind('click', function () {
    $('#test').find('input[type=checkbox]').not(this).attr("checked", false);
});

})(jQuery);

                     
     
  