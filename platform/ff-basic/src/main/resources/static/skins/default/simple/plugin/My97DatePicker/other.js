window.onload=window.onresize=function(){
    $(".WdateDiv .YMenu").next(".yminput,.yminputfocus").css({
    	    "text-align": "left",
    "padding-left":"10px",
    "margin-top":"1px"
    });
    $(".WdateDiv .dpButton").hover(function(){
        $(this).addClass("on");
    },function(){
        $(this).removeClass("on");
    })
  }