/*!
 * jQuery loading Plugin v1.0.1
 *
 * Copyright 2017 Nick Han
 */
(function (factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD
		define(['jquery'], factory);
	} else if (typeof exports === 'object') {
		// CommonJS
		factory(require('jquery'));
	} else {
		// Browser globals
		factory(jQuery);
	}
}(function ($) {
	
	/*
	 * full screen loading mask
	 */
	$.mask_fullscreen = function(content){
		var myContent;
		if(content && content!=""){
			myContent = content;
		}else{
            myContent = "数据加载中..."
		}
		if($(".loadmask[ele='full_screen']").length > 0){
			return;
		}
		//禁止滚动
		$("body").addClass("scroll-off");
		var mask = '<div class="loadmask" ele="full_screen"><div class="loadmaskTableCell"><div class="loadContent">'+ myContent +'</div></div></div>';
		$("body").append(mask);
	};
	
	/*
	 * certain element loading mask
	 */
	$.mask_element = function(ele_id, content, lockfullscreen){
        var myContent;
        if(content && content!=""){
            myContent = content;
        }else{
            myContent = "数据加载中..."
        }
        if($(".loadmask[ele='full_screen']").length > 0){
            return;
        }
		$(ele_id).addClass("minHeight200");
		//判断当前元素是否已经添加遮罩，如果已添加，则直接返回
		if($(".loadmask[ele='"+ele_id+"']").length > 0){
			return;
		}
		//添加遮罩元素
		var mask = '<div class="loadmask" ele='+ele_id+' style="height:'+$(ele_id).height()+'px"><div class="loadmaskTableCell"><div class="loadContent">'+ myContent +'</div></div></div>';
        if($(ele_id).css("position") == "static" || $(ele_id).css("position") == ""){
        	$(ele_id).css("position","relative");
        }
        $(ele_id).append(mask);
	};
	
	/*
	 * close certain loading mask
	 */
	$.mask_close = function(ele_id){
		$(".loadmask[ele='"+ele_id+"']").remove();
        $(ele_id).removeClass("minHeight200");
        $(ele_id).css("position",$(ele_id).data("normalPosition"));
        //禁止滚动
        $("body").removeClass("scroll-off");
	};
	
	/*
	 * close all loading mask
	 */
	$.mask_close_all = function(){
		$(".loadmask").remove();
        $(".minHeight200").removeClass("minHeight200");
        //禁止滚动
        $("body").removeClass("scroll-off");
	}
}));