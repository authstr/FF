$(function() {
	function f(l) {
		var k = 0;
		$(l).each(function() {
			k += $(this).outerWidth(true)
		});
		return k
	}

	function g(n) {
		var o = f($(n).prevAll()),
			q = f($(n).nextAll());
		var l = f($(".g-content-tabs").children().not(".J_menuTabs"));
		var k = $(".g-content-tabs").outerWidth(true) - l;
		var p = 0;
		if ($(".page-tabs-content").outerWidth() < k) {
			p = 0
		} else {
			if (q <= (k - $(n).outerWidth(true) - $(n).next().outerWidth(true))) {
				if ((k - $(n).next().outerWidth(true)) > q) {
					p = o;
					var m = n;
					while ((p - $(m).outerWidth()) > ($(".page-tabs-content").outerWidth() - k)) {
						p -= $(m).prev().outerWidth();
						m = $(m).prev()
					}
				}
			} else {
				if (o > (k - $(n).outerWidth(true) - $(n).prev().outerWidth(true))) {
					p = o - $(n).prev().outerWidth(true)
				}
			}
		}
		if(!$(".J_tabLeft").is(":visible")){
			p=0;
		}
		$(".page-tabs-content").animate({
			marginLeft: 0 - p + "px"
		}, "fast")
	}

	function a() {
		var o = Math.abs(parseInt($(".page-tabs-content").css("margin-left")));
		var l = f($(".g-content-tabs").children().not(".J_menuTabs"));
		var k = $(".g-content-tabs").outerWidth(true) - l;
		var p = 0;
		if ($(".page-tabs-content").width() < k) {
			return false
		} else {
			var m = $(".J_menuTab:first");
			var n = 0;
			while ((n + $(m).outerWidth(true)) <= o) {
				n += $(m).outerWidth(true);
				m = $(m).next()
			}
			n = 0;
			if (f($(m).prevAll()) > k) {
				while ((n + $(m).outerWidth(true)) < (k) && m.length > 0) {
					n += $(m).outerWidth(true);
					m = $(m).prev()
				}
				p = f($(m).prevAll())
			}
		}
		$(".page-tabs-content").animate({
			marginLeft: 0 - p + "px"
		}, "fast")
	}

	function b() {
		var o = Math.abs(parseInt($(".page-tabs-content").css("margin-left")));
		var l = f($(".g-content-tabs").children().not(".J_menuTabs"));
		var k = $(".g-content-tabs").outerWidth(true) - l;
		var p = 0;
		if ($(".page-tabs-content").width() < k) {
			return false
		} else {
			var m = $(".J_menuTab:first");
			var n = 0;
			while ((n + $(m).outerWidth(true)) <= o) {
				n += $(m).outerWidth(true);
				m = $(m).next()
			}
			n = 0;
			while ((n + $(m).outerWidth(true)) < (k) && m.length > 0) {
				n += $(m).outerWidth(true);
				m = $(m).next()
			}
			p = f($(m).prevAll());
			if (p > 0) {
				$(".page-tabs-content").animate({
					marginLeft: 0 - p + "px"
				}, "fast")
			}
		}
	}
	$(".J_menuItem").each(function(k) {
		if (!$(this).attr("data-index")) {
			$(this).attr("data-index", k)
		}
	});

	function c() {
		var o = $(this).attr("href"),
			m = $(this).data("index"),
			l = $.trim($(this).text()),
			k = true;
		if (o == undefined || $.trim(o).length == 0) {
			return false
		}
		$(".J_menuTab").each(function() {
			if ($(this).data("id") == o) {
				if (!$(this).hasClass("active")) {
					$(this).addClass("active").siblings(".J_menuTab").removeClass("active");
					g(this);
					$(".J_mainContent .J_iframe").each(function() {
						if ($(this).data("id") == o) {
							$(this).show().siblings(".J_iframe").hide();
							return false
						}
					})
				}
				k = false;
				return false
			}
		});
		if (k) {
			var p = '<a href="javascript:;" class="active J_menuTab" data-id="' + o + '">' + l + ' <i class="gi-44"></i></a>';
			$(".J_menuTab").removeClass("active");
			var n = '<iframe class="J_iframe" name="iframe' + m + '" width="100%" height="100%" src="' + o + '" frameborder="0" data-id="' + o + '" seamless></iframe>';
			$(".J_mainContent").find("iframe.J_iframe").hide();
			//添加加载动画
  			$(".J_mainContent").addClass("page-loading");
			$(".J_mainContent").append(n);
			$(".J_menuTabs .page-tabs-content").append(p);
			g($(".J_menuTab.active"))
		}
         $(".g-nav .nav-first-level").find("ul").hide();
         $(".g-nav").find("ul li").removeClass("active");
         $(".g-nav").find(".gi-23").removeClass("gi-24");

        $(".g-main-container").show();
        //搜索菜单隐藏
        if($(".g-search-nav").length>0){
        	 $(".g-search-nav").hide();
        }
        //显示不显示向左滑动向右滑动的图标
       if($(".g-content-tabs").length>0){
        var TabsWidth=$(".page-tabs-content").width();
        if(TabsWidth>$(".g-content-tabs").outerWidth()-140){
          $(".J_tabLeft,.J_tabRight").show();
          $(".J_menuTabs").css("margin-left",30);
        }else{
          $(".J_tabLeft,.J_tabRight").hide();
          $(".J_menuTabs").css("margin-left",0);
          $(".page-tabs-content").animate({
            marginLeft: 0
          }, "fast")
        }
      }
		return false
	}
	$("body").on("click",".J_menuItem",c);
	function h() {
		var m = $(this).parents(".J_menuTab").data("id");
		var l = $(this).parents(".J_menuTab").width();
		if ($(this).parents(".J_menuTab").hasClass("active")) {
			if ($(this).parents(".J_menuTab").next(".J_menuTab").size()) {
				var k = $(this).parents(".J_menuTab").next(".J_menuTab:eq(0)").data("id");
				$(this).parents(".J_menuTab").next(".J_menuTab:eq(0)").addClass("active");
				$(".J_mainContent .J_iframe").each(function() {
					if ($(this).data("id") == k) {
						$(this).show().siblings(".J_iframe").hide();
						return false
					}
				});
				var n = parseInt($(".page-tabs-content").css("margin-left"));
				if (n < 0) {
					$(".page-tabs-content").animate({
						marginLeft: (n + l) + "px"
					}, "fast")
				}
				$(this).parents(".J_menuTab").remove();
				$(".J_mainContent .J_iframe").each(function() {
					if ($(this).data("id") == m) {
						$(this).remove();
						return false
					}
				})
			}
			if ($(this).parents(".J_menuTab").prev(".J_menuTab").size()) {
				var k = $(this).parents(".J_menuTab").prev(".J_menuTab:last").data("id");
				$(this).parents(".J_menuTab").prev(".J_menuTab:last").addClass("active");
				$(".J_mainContent .J_iframe").each(function() {
					if ($(this).data("id") == k) {
						$(this).show().siblings(".J_iframe").hide();
						return false
					}
				});
				$(this).parents(".J_menuTab").remove();
				$(".J_mainContent .J_iframe").each(function() {
					if ($(this).data("id") == m) {
						$(this).remove();
						return false
					}
				})
			}
		} else {
			$(this).parents(".J_menuTab").remove();
			$(".J_mainContent .J_iframe").each(function() {
				if ($(this).data("id") == m) {
					$(this).remove();
					return false
				}
			});
			g($(".J_menuTab.active"))
		}
		if($(this).parents(".J_menuTabs").find(".J_menuTab").length==1){
			$(this).parents(".J_menuTab").remove();
			$(".J_mainContent .J_iframe").each(function() {
				if ($(this).data("id") == m) {
					$(this).remove();
					return false
				}
			});
			$(".g-main-container").hide();
		}
	    //显示不显示向左滑动向右滑动的图标
           if($(".g-content-tabs").length>0){
            var TabsWidth=$(".page-tabs-content").width();
            if(TabsWidth>$(".g-content-tabs").outerWidth()-140){
              $(".J_tabLeft,.J_tabRight").show();
              $(".J_menuTabs").css("margin-left",30);
            }else{
              $(".J_tabLeft,.J_tabRight").hide();
              $(".J_menuTabs").css("margin-left",0);
              $(".page-tabs-content").animate({
                marginLeft: 0
              }, "fast")
            }
          }
		return false
	}
	$(".J_menuTabs").on("click", ".J_menuTab i", h);

	function i() {
		$(".page-tabs-content").children("[data-id]").not(":first").not(".active").each(function() {
			$('.J_iframe[data-id="' + $(this).data("id") + '"]').remove();
			$(this).remove()
		});
		/*$(".page-tabs-content").children("[data-id]").not(".active").each(function() {
			$('.J_iframe[data-id="' + $(this).data("id") + '"]').remove();
			$(this).remove()
		});*/
		//$(".page-tabs-content").css("margin-left", "0");
		 //显示不显示向左滑动向右滑动的图标
           if($(".g-content-tabs").length>0){
            var TabsWidth=$(".page-tabs-content").width();
            if(TabsWidth>$(".g-content-tabs").outerWidth()-140){
              $(".J_tabLeft,.J_tabRight").show();
              $(".J_menuTabs").css("margin-left",30);
            }else{
              $(".J_tabLeft,.J_tabRight").hide();
              $(".J_menuTabs").css("margin-left",0);
              $(".page-tabs-content").animate({
                marginLeft: 0
              }, "fast")
            }
          }
	}
	$(".J_tabCloseOther").on("click", i);

	function j() {
		g($(".J_menuTab.active"))
	}
	$(".J_tabShowActive").on("click", j);

	function e() {
		if (!$(this).hasClass("active")) {
			var k = $(this).data("id");
			$(".J_mainContent .J_iframe").each(function() {
				if ($(this).data("id") == k) {
					$(this).show().siblings(".J_iframe").hide();
					return false
				}
			});
			$(this).addClass("active").siblings(".J_menuTab").removeClass("active");
			g(this)
		}
	}
	$(".J_menuTabs").on("click", ".J_menuTab", e);

	function d() {
		var l = $('.J_iframe[data-id="' + $(this).data("id") + '"]');
		var k = l.attr("src")
	}
	$(".J_menuTabs").on("dblclick", ".J_menuTab", d);
	$(".J_tabLeft").on("click", a);
	$(".J_tabRight").on("click", b);
	$(".J_tabCloseAll").on("click", function() {
		$(".page-tabs-content").children("[data-id]").not(":first").each(function() {
			$('.J_iframe[data-id="' + $(this).data("id") + '"]').remove();
			$(this).remove();
		});
		$(".page-tabs-content").children("[data-id]:first").each(function() {
			$('.J_iframe[data-id="' + $(this).data("id") + '"]').show();
			$(this).addClass("active");
		});
		//$(".page-tabs-content").css("margin-left", "0");
	/*	$(".page-tabs-content").children("[data-id]").each(function() {
			$('.J_iframe[data-id="' + $(this).data("id") + '"]').remove();
			$(this).remove()
		});
		$(".page-tabs-content").css("margin-left", "0");
		$(".g-main-container").hide();*/
		          //显示不显示向左滑动向右滑动的图标
           if($(".g-content-tabs").length>0){
            var TabsWidth=$(".page-tabs-content").width();
            if(TabsWidth>$(".g-content-tabs").outerWidth()-140){
              $(".J_tabLeft,.J_tabRight").show();
              $(".J_menuTabs").css("margin-left",30);
            }else{
              $(".J_tabLeft,.J_tabRight").hide();
              $(".J_menuTabs").css("margin-left",0);
              $(".page-tabs-content").animate({
                marginLeft: 0
              }, "fast")
            }
          }
	})
});