$(function() {
    //页面加载完了之后移除loadding动画
    //$(".J_mainContent", window.parent.document).removeClass("page-loading");
    //form表单的一些样式优化
    $("body").on("click", ".J_checkBox input", function(event) {
        event.stopPropagation();
        var _this = $(this).parents(".J_checkBox");
        if (_this.hasClass("disabled") || _this.hasClass("check-disabled")) {
            return;
        }
        if (_this.hasClass("on")) {
            _this.removeClass("on");
			//$(this).removeAttr("checked");
        } else {
            _this.addClass("on");
			//$(this).attr("checked","checked");
        }
        /*var bccArray=[];
         $("input[name='bcc']:checked").each(function(){ 
              bccArray.push($(this).val());
        });
        console.log(bccArray);*/
    });
    $("body").on("click", ".J_radioBox label input", function() {
        var _this = $(this).parents("label");
        if (_this.hasClass("disabled") || _this.hasClass("check-disabled")) {
            return;
        }
        if (_this.hasClass("on")) return;
        _this.parents(".J_radioBox").find("label").removeClass("on");
        _this.addClass("on");
        //console.log(_this.parents(".J_radioBox").find("input"));
    });

    //产品下载里面修改radioBox
    $("body").on("click", ".J_radioBox2 label.radio-inline input", function() {
        var _this = $(this).parents("label.radio-inline");
        if (_this.hasClass("disabled") || _this.hasClass("check-disabled")) {
            return;
        }
        if (_this.hasClass("on")) {
            return;
        } else {
            _this.parents(".J_radioBox2").find("label").removeClass("on");
            _this.parents(".J_radioBox2").find("label input").prop("checked", false);
            _this.parents(".J_radioBox1").find("label").addClass("on");
            _this.parents(".J_radioBox1").find("label input").prop("checked", true);
            // console.log(_this.parents(".J_radioBox2").find("input"));
        }

    });
    //产品下载里面修改checkBox
    $("body").on("click", ".J_checkBox1 input", function(event) {
        event.stopPropagation();
        var _this = $(this).parents(".J_checkBox1");
        if (_this.hasClass("disabled") || _this.hasClass("check-disabled")) {
            return;
        }
        if (_this.hasClass("on")) {
            _this.removeClass("on");
            var flag = false;
            _this.siblings(".J_checkBox1").each(function(i, item) {
                if ($(item).find("input").is(":checked")) {
                    flag = true;
                    return;
                }
            });
            if (flag == false) {
                _this.parents(".J_radioBox1").find("label.radio-inline").removeClass("on");
                _this.parents(".J_radioBox1").find("label.radio-inline input").prop("checked", false);
            }
        } else {
            _this.addClass("on");
            _this.parents(".J_radioBox1").siblings(".J_radioBox1").find("label").removeClass("on");
            _this.parents(".J_radioBox1").siblings(".J_radioBox1").find("label input").prop("checked", false);
            _this.parents(".J_radioBox1").find("label.radio-inline").addClass("on");
            _this.parents(".J_radioBox1").find("label.radio-inline input").prop("checked", true);

        }
        // console.log(_this.parents(".J_radioBox2").find("input"));
    });
    //上传文件选择
    $("body").on("change", ".J_fileButton", function() {
        var _this = $(this);
        _this.parents(".J_FileBox").find(".J_fileInput").val(_this.val());

    });
    //提示信息的展开与关闭
    $("body").on("click", ".J_arrowAction", function() {
        if ($(this).find("i").hasClass("gi-up")) {
            if ($(this).parents(".g-warning-box")) {
                //$(this).parents(".g-warning-box").find(".g-title").css("border-color","#faebcc");
                $(this).parents(".g-warning-box").find(".J_tipBox").slideDown("slow");
                $(this).find("i").removeClass("gi-up").addClass("gi-down");
            }
            if ($(this).parents(".g-warning-dialog")) {
                if ($(this).parents(".g-warning-dialog").find(".J_tipBox")) {
                    $(this).parents(".g-warning-dialog").find(".J_tipBox").slideDown("slow");
                    //$(this).parents(".g-warning-dialog").find(".aui_title").css("border-color","#faebcc");
                    $(this).find("i").removeClass("gi-up").addClass("gi-down");
                }
            }
            return;
        }
        if ($(this).find("i").hasClass("gi-down")) {
            if ($(this).parents(".g-warning-box")) {
                // $(this).parents(".g-warning-box").find(".g-title").css("border-color","#ececec");
                $(this).parents(".g-warning-box").find(".J_tipBox").slideUp("slow");
                $(this).find("i").removeClass("gi-down").addClass("gi-up");
            }
            if ($(this).parents(".g-warning-dialog")) {
                if ($(this).parents(".g-warning-dialog").find(".J_tipBox")) {
                    $(this).parents(".g-warning-dialog").find(".J_tipBox").slideUp("slow");
                    //  $(this).parents(".g-warning-dialog").find(".aui_title").css("border-color","#ececec");
                    $(this).find("i").removeClass("gi-down").addClass("gi-up");
                }
            }
            return;
        }
    });
    //更多筛选条件的展开与关闭
    $("body").on("click", ".J_downBtn", function() {
        $(this).hide();
        $(this).parents("form").find(".J_upBtn").show();
        $(this).parents("form").find(".J_upBtn").removeClass("hide");
        $(this).parents("form").find(".J_moreFilter").slideDown("slow");
    });
    $("body").on("click", ".J_upBtn", function() {
        $(this).hide();
        $(this).parents("form").find(".J_downBtn").show();
        $(this).parents("form").find(".J_moreFilter").slideUp("slow");
    });
    //点击iframe页面内容关闭左侧菜单
    $("body").on("click", function() {
        $(".g-nav", window.parent.document).find(".nav-second-level").hide();
        $(".g-nav", window.parent.document).find("li").removeClass("active");
        //点击iframe页面内容关闭tab选项卡的右侧菜单
        $(".J_tabMenu", window.parent.document).hide();
        //点击iframe页面关闭无结果提示
        $(".g-search-nav", window.parent.document).hide();
    });
    //竖直列表点击选中事件
    $("body").on("click", ".J_navStacked li", function() {
        if ($(this).hasClass("active")) return;
        $(this).addClass("active");
        $(this).siblings().removeClass('active');
    });
    //右侧页面高度
    if ($(".J_pageContainer").length > 0) {
        $(".J_pageContainer").height($(window).height() - 20);
        $(".J_pageContainer").niceScroll({});
        $(window).resize(function() {
            $(".J_pageContainer").height($(window).height() - 20);
            $(".J_pageContainer").niceScroll({}).resize();
        });
    }
});
Date.prototype.format = function(format) //author: meizz 
{ 
  var o = { 
    "M+" : this.getMonth()+1, //month 
    "d+" : this.getDate(),    //day 
    "h+" : this.getHours(),   //hour 
    "m+" : this.getMinutes(), //minute 
    "s+" : this.getSeconds(), //second 
    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter 
    "S" : this.getMilliseconds() //millisecond 
  } 
  if(/(y+)/.test(format)) format=format.replace(RegExp.$1, 
    (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
  for(var k in o)if(new RegExp("("+ k +")").test(format)) 
    format = format.replace(RegExp.$1, 
      RegExp.$1.length==1 ? o[k] : 
        ("00"+ o[k]).substr((""+ o[k]).length)); 
  return format; 
} 