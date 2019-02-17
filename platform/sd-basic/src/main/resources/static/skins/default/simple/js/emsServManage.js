//$(function(){
//	//loadZoneServList();
//	//bindServManagePageEvent();
//	//initFwlx();
//	//initJcfs();
//	//initJcmb();
//	//initServImgEvent();
//});
//
////function bindServManagePageEvent(){
////	$("#jqGridActionbarId").children().each(function(){
////		var this_obj = $(this);
////		var myGrid = new MyJqGrid();
////		if(this_obj.attr("btncode")=="ADD"){
////			this_obj.click(function(){
////				$("#cjr").attr("disabled", false);
////				myGrid.bindGridAddEvent();
////			});
////		}else if(this_obj.attr("btncode")=="EDIT"){
////			this_obj.click(function(){
////				url = path+"/service/get/";
////				myGrid.bindGridEditEvent(url);
////			});
////		}else if(this_obj.attr("btncode")=="DELETE"){
////			this_obj.click(function(){
////				var url = path+"/service/delete";
////				myGrid.bindGridDelEvent(url);
////			});
////		}else if(this_obj.attr("btncode")=="START"){
////			this_obj.click(function(){
////				bindServStatusEvent('start');
////			});
////		}else if(this_obj.attr("btncode")=="STOP"){
////			this_obj.click(function(){
////				bindServStatusEvent('stop');
////			});
////		}else if(this_obj.attr("btncode")=="CHECK"){
////			this_obj.click(function(){
////				bindServUsableEvent();
////			});
////		}
////	});
////	$("#add_service_back_btn").click(function(){
////		window.location.reload();
////	});
////	
////	$("#add_service_ok_btn").unbind('click');
////	$("#add_service_ok_btn").click(function(){
////		var url = "";
////		var id = $("#rrt-manager-editing-id").val();
////		if(id==""){
////			url = path+"/service/save";
////		}else{
////			url = path+"/service/update";
////		}
////		if($("#fwdtb").val()=="" || $("#fwxtb").val()==""){
////			Log.m("请选择一个图标.");
////			return;
////		}
////		var param = {
////				id : id,
////				fwmc : $("#fwmc").val(),
////				fwms : $("#fwms").val(),
////				url : $("#url").val(),
////				sfky : "1",
////				fwdtb : $("#fwdtb").val(),
////				fwxtb : $("#fwxtb").val(),
////				fwztb : "",
////				jcfs : $("#jcfs").val(),
////				jcmb : $("#jcmb").val(),
////				iframeHeight : $("#iframeHeight").val(),
////				fwlx : $("#fwlx").val()
////		};
////		$("#add_service_ok_btn").attr("disabled", true);
////		$.post(url, param, function(json){
////			$("#add_service_ok_btn").attr("disabled", false);
////			if(json&&json.success==true){	
////				$("#jqGridId").trigger("reloadGrid");
////				$("#rrt-manager-editing-id").val("");
////				$("#rrt-manager-grid").show();
////		    	$("#rrt-manager-form").hide();
////			}else{
////				if(id=="") {
////					Log.e("创建失败!"+json.message);
////				}else {
////					Log.e("修改失败!"+json.message);
////				}
////			}
////		} ,'json');
////	});
////}
////
////function formatSfky(cellValue, options, rowObject){
////	return cellValue=="1"?"启动":"停止";
////}
////var myGrid = null;
////function loadZoneServList(param){
////	//var param = {
////	//		url : M.url,
////			//width : clientWidth,
////			//height : clientHeight,
////	//		colModel : M.gridTitle 
////	//};
////	if(myGrid == null){
////		myGrid = new MyJqGrid(param);
////		myGrid.loadMyGrid();
////	}else{
////		myGrid.query(param);
////	}
////}
//
//function bindServStatusEvent(action){
//	var rowIds=$("#jqGridId").jqGrid("getGridParam","selarrrow");
//	if(rowIds.length==0){
//		Log.m("请选择要"+(action=='stop'?"停用":"启用")+"的服务.");
//	}else{
//		var ids = [];
//		for(var i=0;i<rowIds.length;i++){
//			var id = $("#jqGridId").jqGrid("getRowData", rowIds[i]).ID;
//			ids.push(id);
//		}
//		var word = action=='stop'?"确认要停用吗？":"确认要启用吗？";
//		var con = confirm(word);
//		if(con){
//			var url = path + "/service/"+(action=='stop'?"stop":"start")+"?ids="+ids.toString();
//			$.getJSON(url,function(json){
//				if(json&&json.success==true){
//					Log.m(action=='stop'?"停用成功":"启用成功");
//					$("#jqGridId").trigger("reloadGrid");
//				}else{
//					Log.e(json.message);
//				}
//			});
//		}
//	}
//}
//
//function bindServUsableEvent(){
//	var ids=$("#jqGridId").jqGrid("getGridParam","selarrrow");
//	if(ids.length>1){
//		Log.e("探测时一次只能选择一行.");
//	}else if(ids.length==0){
//		Log.m("请选择一行.");
//	}else{
//		var rowId = $("#jqGridId").jqGrid("getGridParam","selrow");
//		var id = $("#jqGridId").jqGrid("getRowData", rowId).ID;
//		var url = path+"/service/"+id+"/status/check";
//		$.getJSON(url,function(json){
//			if(json&&json.message&&json.success==true){
//				$("#jqGridId").jqGrid('setCell', rowId, 'HEALTHY', "可用");
//			}else{
//				$("#jqGridId").jqGrid('setCell', rowId, 'HEALTHY', "不可用");
//			}
//		});
//	}
//}
//
//var servObjIndex = 0;
//function loadUsableOfServ(objArray){
//	if(servObjIndex>=objArray.length) return;
//	var id = $(objArray[servObjIndex]).parent().attr("id");
//	var url = path+"/service/"+id+"/status/check";
//	$.getJSON(url,function(json){
//		if(json&&json.message&&json.success==true){
//			if(json.message.status==true){
//				$(objArray[servObjIndex]).html("可用");
//			}else{
//				$(objArray[servObjIndex]).html("不可用");
//			}
//		}else{
//			$(objArray[servObjIndex]).html("不可用");
//		}
//		servObjIndex++;
//		loadUsableOfServ(objArray);
//	});
//}
//
//function loadServCata(){
//	var url = path+"/dictionary/query?key=zone_service_category_key";
//	$.getJSON(url,function(json){
//		if(json&&json.message&&json.success==true){
//			$(".servcata").each(function(){
//				var data = json.message;
//				for(var i=0;i<data.length;i++){
//					var servcata = data[i];
//					if(servcata.id==$(this).attr("servcataid")){
//						$(this).html(servcata.value);
//						break;
//					}
//				}
//			});
//		}
//	});
//}
//
//function initServImgEvent(){
//	$(".rcmdServImg").each(function(){
//		$(this).click(function(){
//			$(".rcmdServImg").each(function(){
//				$(this).removeClass("selected");
//			});
//			$(this).addClass("selected");
//			var index = $(this).attr("index");
//			$("#fwdtb").val("/skins/default/images/icon148"+index+".gif");
//			$("#fwxtb").val("/skins/default/images/icon50"+index+".gif");
//		});
//	});
//}
//
//function initJcmb(){
//	var url = path+"/dictionary/query?key=zone_service_jcmb";
//	$.getJSON(url,function(json){
//		if(json&&json.message&&json.success){
//			var data = json.message;
//			var jcmbSelect = $("#jcmb");
//			for(var i=0;i<data.length;i++){
//				var option = $("<option></option>").attr("name", "jcmb").val(data[i].key).html(data[i].value);
//				option.appendTo(jcmbSelect);
//			}
//		}else{
//			Log.e("查询字典错误,请联系管理员.");
//		}
//	});
//}
//
//function initFwlx(){
//	var url = path+"/dictionary/query?key=zone_service_category_key";
//	$.getJSON(url,function(json){
//		if(json&&json.success){
//			var data = json.message;
//			var fwlxSelect = $("#fwlx");
//			fwlxSelect.html("");
//			var option = $("<option></option>").attr("name", "fwlx").val("").html("");
//			option.appendTo(fwlxSelect);
//			for(var i=0;i<data.length;i++){
//				var option = $("<option></option>").attr("name", "fwlx").val(data[i].id).html(data[i].value);
//				option.appendTo(fwlxSelect);
//			}
//		}else{
//			Log.e("查询字典错误,请联系管理员.");
//		}
//	});
//}
//
//function initJcfs(){
//	var url = path+"/dictionary/query?key=zone_service_jcfs";
//	$.getJSON(url,function(json){
//		if(json&&json.message&&json.success){
//			var data = json.message;
//			var jcfsSelect = $("#jcfs");
//			for(var i=0;i<data.length;i++){
//				var option = $("<option></option>").attr("name", "jcfs").val(data[i].key).html(data[i].value);
//				option.appendTo(jcfsSelect);
//			}
//			bindOnChangeEvent(jcfsSelect);
//		}else{
//			Log.e("查询字典错误,请联系管理员.");
//		}
//	});
//}
//
//function bindOnChangeEvent(jcfsSelect){
//	jcfsSelect.change(function(){
//		if($(this).val()=="zone_service_jcfs_iframe"){
//			$("#iframeHeight_div").show();
//		}else{
//			$("#iframeHeight_div").hide();
//		}
//	});
//}
//
//function bindServManageTableEvent(){
//	$(".serv-manage-table-edit").each(function(){
//		$(this).click(function(){
//			var servid = $(this).attr("servid");
//			$("#serv-manage-editing-id").val(servid);
//			var url = path+"/service/get/"+servid;
//			$.getJSON(url,function(json){
//				if(json&&json.message&&json.success==true){
//					$("#serv-manage-list").hide();
//					$("#rrt-serv-edit").show();
//					var serv = json.message;
//					$("#fwmc").val(serv.fwmc);
//					$("#fwms").val(serv.fwms);
//					$("#url").val(serv.url);
//					$("#fwdtb").val(serv.fwdtb);
//					$("#fwxtb").val(serv.fwxtb);
//					$("#jcfs_select").val(serv.jcfs);
//					$("#jcmb_select").val(serv.jcmb);
//					$("#fwlx_select").val(serv.fwlx);
//					$(".rcmdServImg").each(function(){
//						if($(this).attr("src").indexOf(serv.fwxtb)>-1){
//							$(this).addClass("selected");
//						}
//					});
//					if(serv.jcfs=="zone_service_jcfs_iframe"){
//						$("#iframeHeight_div").show();
//						$("#iframeHeight").val(serv.iframeHeight);
//					}
//				}else{
//					Log.e(json.message);
//				}
//			});
//		});
//	});
//	$(".serv-manage-table-delete").each(function(){
//		$(this).click(function(){
//			var con = confirm("确认要删除此服务吗？");
//			if(con){
//				var url = path+"/service/delete/"+$(this).attr("servid");
//				$.getJSON(url,function(json){
//					if(json&&json.success==true){
//						Log.m("删除成功.");
//						loadZoneServList();
//					}else{
//						Log.e(json.message);
//					}
//				});
//			}
//		});
//	});
//}
//
//function bindServManageListMoreEvent(page, rows){
//	$("#serv-manage-list-more").click(function(){
//		page+=1;
//		$("#serv-manage-list-page").val(page);
//		$("#serv-manage-list-rows").val(rows);
//		var url = path+"/service/query?page="+page+"&rows="+rows;
//		$.getJSON(url,function(json){
//			if(json&&json.message&&json.success==true){
//				if(json.message.total>(page*rows)){
//					$("#serv-manage-list-more").show();
//					bindZoneManageListMoreEvent(page, rows);
//				}else{
//					$("#serv-manage-list-more").hide();
//				}
//				var servs = json.message.record;
//				var table = $("#serv-manage-list-tbody");
//				for(var i=0;i<servs.length;i++){
//					var serv = servs[i];
//					var tr = $("<tr></tr>").addClass("thstyle").attr("id", serv[0]);
//					var td1 = $("<td></td>").addClass("td-center").html(serv[1]);
//					var td2 = $("<td></td>").addClass("td-center servcata").html("").attr("servcataid", serv[7]);
//					var td3 = $("<td></td>").addClass("td-center").html(serv[11]);
//					var td4 = $("<td></td>").addClass("td-center serv-usable").html("探测中...").attr("servid", serv[0]);
//					var td5 = $("<td></td>").addClass("td-center serv-status").html(serv[8]==1?"启用":"停用");
//					var edit = $("<a></a>").attr("href", "javascript:void(0);").html("编辑").addClass("serv-manage-table-edit").attr("servid", serv[0]);
//					var del = $("<a></a>").attr("href", "javascript:void(0);").html("删除").addClass("serv-manage-table-delete").attr("servid", serv[0]);
//					var usable = $("<a></a>").attr("href", "javascript:void(0);").html("探测").addClass("serv-manage-table-usable").attr("servid", serv[0]);
//					var status = $("<a></a>").attr("href", "javascript:void(0);").html(serv[8]==1?"停止":"启动").addClass("serv-manage-table-status").attr("servid", serv[0]).attr("serv-status", serv[8]);
//					var td6 = $("<td></td>").addClass("td-center");
//					td6.append(edit).append("&nbsp;").append(del).append("&nbsp;").append(usable).append("&nbsp;").append(status);
//					tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6);
//					table.append(tr);
//				}
//				loadServCata();
//				loadUsableOfServ($(".serv-usable"));
//				bindServUsableEvent();
//				bindServStatusEvent();
//				bindServManageTableEvent();
//			}
//		});
//	});
//}