$(function() {
    window.onload = window.onresize = function() {
    	$.getJSON("menu/my/get",function(json){
    		if(json.code==1){
    			var htmls = template('menu_tpl', json);
    			$("#menu_tpl_div").html(htmls);
    			 //头部菜单自动收缩\放开
    	        if ($(".J_menuWarp").length > 0) {
    	            var imgWarpWidth = $(".J_imgWarp").outerWidth();
    	            var num = $(".J_menuWarp").find(".item").length;
    	            var itemWidth = $(".J_menuWarp").find(".item").outerWidth();
    	            var menuWarpWidth = itemWidth * num + num;
    	            var otherWarpWidth = $(".J_moreMenu").outerWidth() + $(".J_adminAction").outerWidth() + $(".J_history").width();

    	            var winWidth = $(window).width();
    	            var totalWidth = imgWarpWidth + menuWarpWidth + otherWarpWidth;
    	            var num3 = (winWidth - totalWidth) / itemWidth;
    	            var num2 = Math.floor(num3);
    	            if (num3 <= 0) {
    	                $(".J_menuWarp .item").css("display", "block");
    	                var dom = $(".J_menuWarp .item").slice(num2 - 1);
    	                if (Math.abs(num2) == 0) {
    	                    dom = $(".J_menuWarp .item").slice(num - 2);
    	                }
    	                $(".g-menu-box").html(dom.clone());
    	                dom.css("display", "none");
    	                $(".J_moreMenu").show();
    	                $(".g-menu-box").find(".item").css("display", "block");
    	            } else {
    	                $(".g-menu-box").html("");
    	                $(".J_menuWarp .item").css("display", "block");
    	                $(".J_moreMenu").hide();
    	                $(".g-menu-box").find(".item").css("display", "none");
    	            }
    	        }
    	        //左侧滚动条
    	        $(".J_mainContent").height($(window).height() - $(".g-header").height() - 30);
    	        $(".J_navBar").height($(window).height() - $(".g-header").height() - 30);
    	        $(".ui-autocomplete").height($(window).height() - $(".g-header").height() - 30);
    	        $(".J_navBar").parent(".slimScrollDiv").height($(".J_navBar").height());
    	        $('.J_navBar').slimScroll({
    	            color: '#999',
    	            size: '7px',
    	            opacity: .4,
    	            height: $(".J_navBar").height(),
    	            alwaysVisible: false,
    	            disableFadeOut: true
    	        });
    	        $(".nav-second-level").height($(window).height() - $(".g-header").height());
    	        $(".nav-second-level").niceScroll({});
    	        //显示不显示向左滑动向右滑动的图标
    	        if ($(".g-content-tabs").length > 0) {
    	            var TabsWidth = $(".page-tabs-content").width();
    	            if (TabsWidth > $(".g-content-tabs").outerWidth() - 140) {
    	                $(".J_tabLeft,.J_tabRight").show();
    	                $(".J_menuTabs").css("margin-left", 30);
    	            } else {
    	                $(".J_tabLeft,.J_tabRight").hide();
    	                $(".J_menuTabs").css("margin-left", 0);
    	                $(".page-tabs-content").animate({
    	                    marginLeft: 0
    	                }, "fast")
    	            }
    	        }
    		}
    	});
       
    }
    //ie8下不支持placeholder
    if (!("placeholder" in document.createElement("input"))) {
        $("body").find(".J_placeholder").not(":checkbox").each(function(index) {
            var input = $(this),
                clone = $("<input type='text' />");
            clone.attr({
                name: "re" + input.attr("name"),
                "id": "J_re" + index
            }).val(input.attr("placeholder"));
            input.hide().after(clone);
            clone.show();
            clone.addClass(input.attr("class")).val(input.attr("placeholder")).css("color", "#989696");
            clone.focus(function() {
                clone.hide();
                input.val('').show().focus();
            });
            input.blur(function() {
                if (input.val() == '') {
                    clone.show();
                    input.hide();
                }
            });

        });
    }
    $(".nav-first-level").on("mouseenter", "li", function(e) {
        //一级菜单li
        if ($(this).parent().hasClass("nav-first-level")) {
            $(".g-nav .nav-first-level").find("ul").hide();
            $(".g-nav .nav-first-level").find("li").removeClass("active");
            $(".g-nav").find(".gi-23").removeClass("gi-24");
            $(this).addClass("active");
         /*   $("#bgIframe").hide();*/
            if ($(this).find(".nav-second-level").length > 0) {
                $(this).find(".nav-second-level").show();
               /* $("#bgIframe").show();*/
            }
        }
    });
    $(".search-box").on("mouseenter", function(e) {
        //一级菜单li
        $(".g-nav .nav-first-level").find("ul").hide();
        $(".g-nav .nav-first-level").find("li").removeClass("active");
        $(".g-nav").find(".gi-23").removeClass("gi-24");
      /*  $("#bgIframe").hide();*/

    });
    $(".g-nav").on("mouseleave", function(e) {
        //一级菜单li
        $(".g-nav .nav-first-level").find("ul").hide();
        $(".g-nav .nav-first-level").find("li").removeClass("active");
        $(".g-nav").find(".gi-23").removeClass("gi-24");
       /* $("#bgIframe").hide();*/

    });
    //滑过显示三级菜单
    $(".nav-second-level").on("mouseenter", "li", function(e) {
        $(".g-nav .J_tip").tooltip("hide");
        $(this).addClass("active");
        if ($(this).has("ul")) {
            $(this).find("ul").show();
            $(this).find(".gi-23").addClass("gi-24");
        }
    });
    $(".nav-second-level").on("mouseleave", "li", function(e) {
        $(".g-nav .J_tip").tooltip("hide");
        $(this).removeClass("active");
        if ($(this).has("ul")) {
            $(this).find("ul").hide();
            $(this).find(".gi-23").removeClass("gi-24");
        }
    });
    $(".nav-third-level").on("mouseenter", "li", function(e) {
        $(this).addClass("active");
    });
    $(".nav-third-level").on("mouseleave", "li", function(e) {
        $(this).removeClass("active");
    });
   
    //主菜单、迷你菜单转换
    $("body").on("click", ".J_menuToggleBt", function() {
        $("body").toggleClass("g-mini-nav");
        //ie8下不支持placeholder
        if (!("placeholder" in document.createElement("input"))) {
            var oldInput = $(".g-main-nav").find(".search-box .J_placeholder").eq(0);
            var newInput = $(".g-main-nav").find(".search-box .J_placeholder").eq(1);
            if ($("body").hasClass("g-mini-nav")) {
                $(".g-nav .J_tip").tooltip();
                if (oldInput.val() != "") {
                    $(".g-main-nav").find(".search-box .J_placeholder").eq(0).hide();
                } else {
                    $(".g-main-nav").find(".search-box .J_placeholder").eq(1).hide();
                }
            } else {
                $(".g-nav .J_tip").tooltip("destroy");
                if (oldInput.val() != "") {
                    $(".g-main-nav").find(".search-box .J_placeholder").eq(0).show();
                } else {
                    $(".g-main-nav").find(".search-box .J_placeholder").eq(1).show();
                }
            }
        } else {
            if ($("body").hasClass("g-mini-nav")) {
                $(".g-nav .J_tip").tooltip();
            } else {
                $(".g-nav .J_tip").tooltip("destroy");
            }
        }
    });

    $(".g-header").on("click", ".g-fr a.item", function() {
        if ($(this).hasClass("on")) return;
        $(this).parents(".g-header").find("a.item").removeClass("on");
        $(this).addClass("on");
    });
    //更多菜单选项卡的滑过事件
    var menuHoverTimer;
    $(".g-header").on("mouseenter", ".J_moreMenu", function() {
        clearTimeout(menuHoverTimer);
        $(".g-menu-box").css({
            "left": $(".J_moreMenu").offset().left,
            "top": $(".J_moreMenu").offset().top + $(".J_moreMenu").outerHeight()
        })
        $(this).addClass("active");
        $(".g-menu-box").show();
    });
    $(".g-header").on("mouseleave", ".J_moreMenu", function() {
        var _this = $(this);
        menuHoverTimer = setTimeout(function() {
            $(".g-menu-box").hide();
            _this.removeClass("active");
        }, 50);
    });
    $(".g-header").on("mouseenter", ".g-menu-box", function() {
        clearTimeout(menuHoverTimer);
    });
    $(".g-header").on("mouseleave", ".g-menu-box", function() {
        $(".g-menu-box").hide();
        $(".g-header .J_moreMenu").removeClass("active");
    });
    //管理员选项卡的滑过事件
    var hoverTimer;
    $(".g-header").on("mouseenter", ".J_adminAction", function() {
        clearTimeout(hoverTimer);
        $(".g-action-box").css({
            "left": $(".J_adminAction").offset().left,
            "top": $(".J_adminAction").offset().top + $(".J_adminAction").outerHeight()
        })
        $(this).addClass("active");
        $(".g-action-box").show();
    });
    $(".g-header").on("mouseleave", ".J_adminAction", function() {
        var _this = $(this);
        hoverTimer = setTimeout(function() {
            $(".g-action-box").hide();
            _this.removeClass("active");
        }, 50);
    });
    $(".g-header").on("mouseenter", ".g-action-box", function() {
        clearTimeout(hoverTimer);
    });
    $(".g-header").on("mouseleave", ".g-action-box", function() {
        $(".g-action-box").hide();
        $(".g-header .J_adminAction").removeClass("active");
    });
    //历史记录选项卡的滑过事件
    var hisHoverTimer;
    $(".g-header").on("mouseenter", ".J_history", function() {
        clearTimeout(hisHoverTimer);
        $(".g-history-list").css({
            "left": $(".J_history").offset().left - $(".g-history-list").outerWidth() + $(".J_history").outerWidth(),
            "top": $(".J_history").offset().top + $(".J_history").outerHeight()
        })
        $(".g-history-list").show();
        $(this).addClass("active");
    });
    $(".g-header").on("mouseleave", ".J_history", function() {
        var _this = $(this);
        hisHoverTimer = setTimeout(function() {
            $(".g-history-list").hide();
            _this.removeClass("active");
        }, 50);
    });
    $("body").on("mouseenter", ".g-history-list", function() {
        clearTimeout(hisHoverTimer);
    });
    $("body").on("mouseleave", ".g-history-list", function() {
        $(".g-history-list").hide();
        $(".g-header .J_history").removeClass("active");
    });
    //点击页面其它部分关闭左侧菜单
    $(document).mouseup(function(e) {
        var _con = $('.g-nav'); // 设置目标区域
        if (!_con.is(e.target) && _con.has(e.target).length === 0) {
            $(".g-nav").find(".nav-second-level").hide();
            $(".g-nav").find("li").removeClass("active");
        }
        //点击页面其它部分关闭tab选项卡的右侧菜单
        var _con1 = $('.g-content-tabs .J_menuTabs'); // 设置目标区域
        if (!_con1.is(e.target) && _con1.has(e.target).length === 0) {
            $(".J_tabMenu").hide();
        }
        //点击页面其它部分关闭无结果提示
        var _con2 = $('.g-search-nav'); // 设置目标区域
        if (!_con2.is(e.target) && _con2.has(e.target).length === 0) {
            $(".g-search-nav").hide();
        }
    });
    
    //头部tab选项卡添加右键点击事件
    $(".g-content-tabs").on("contextmenu", ".J_menuTab", function(event) {
        var _this = this;
        var leftWidth = $(".g-nav").width();
        $(".J_tabMenu").css({
            "top": $(_this).height(),
            "left": $(_this).offset().left - leftWidth
        }).show();
        return false;
    });
    $(".g-content-tabs").on("click", ".J_menuTab", function() {
        $(".J_tabMenu").hide();
    });
    $(".J_tabMenu li a").click(function() {
        $(".J_tabMenu").hide();
    });

});