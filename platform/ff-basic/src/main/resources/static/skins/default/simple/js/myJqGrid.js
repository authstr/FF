function MyJqGrid(obj){
	this.jqGridId = obj.jqGridId==null?"jqGridId":obj.jqGridId;
	this.jqGridPagerId = "jqGridPagerId";
	this.multiselect=true;
	this.jqGridActionbarId = "jqGridActionbarId";
	this.isinit= false;
	callback = null;
	if(obj){
		this.jqGridId=obj.jqGridId?obj.jqGridId:'jqGridId';
		this.jqGridPagerId =obj.jqGridPagerId?obj.jqGridPagerId:'jqGridPagerId';
		this.rownumbers=(obj.rownumbers==true)?true:false;
		this.url = obj.url;
		this.datatype = obj.datatype?obj.datatype:'json';
		this.colModel = obj.colModel?obj.colModel:[];
		this.width = obj.width?obj.width:'auto';
	    this.height = obj.height?obj.height:'auto';
	    this.viewrecords = obj.viewrecords?obj.viewrecords:true;
	    this.shrinkToFit = (obj.shrinkToFit==false)?false:true;
	    this.sortname = obj.sortname?obj.sortname:null;
	    this.sortorder = obj.sortorder?obj.sortorder:'asc';
	    this.multiselect = (obj.multiselect==false)?false:true;
	    callback = (obj.callback == null?null:obj.callback);
	}
//    MyJqGrid.prototype.bindGridAddEvent = function(){
//    	$("#rrt-manager-form").find("input").each(function(){
//    		var this_obj = $(this);
//    		var this_obj_type = $(this).attr("type");
//    		if(undefined ==this_obj_type || this_obj_type=="" || "text"==this_obj_type){
//    			this_obj.val("");
//			}else if(this_obj_type=="radio" || "checkbox"==this_obj_type){
//				this_obj.each(function(){
//					this_obj.prop({'checked':false});
//				}); 
//			}
//    	});
//    	$("#rrt-manager-form").find("select").each(function(){
//    		$(this).val("");
//    	});
//    	$("#rrt-manager-form").find("textarea").each(function(){
//    		$(this).val("");
//    	});
//    	$("#rrt-manager-editing-id").val("");
//    	$("#rrt-manager-grid").hide();
//    	$("#rrt-manager-form").show();
//    };
//    MyJqGrid.prototype.bindGridEditEvent = function(url){
//    	var ids=$("#"+this.jqGridId).jqGrid("getGridParam","selarrrow");
//		if(ids.length>1){
//			Log.e("编辑时一次只能选择一行.");
//		}else if(ids.length==0){
//			Log.m("请选择一行.");
//		}else{
//			var rowId = $("#"+this.jqGridId).jqGrid("getGridParam","selrow");
//			var id = $("#"+this.jqGridId).jqGrid("getRowData", rowId).ID;
//			$("#rrt-manager-grid").hide();
//	    	$("#rrt-manager-form").show();
//	    	$("#rrt-manager-editing-id").val(id);
//	    	$.getJSON(url+id,function(json){
//	    		if(json&&json.message&&json.success==true){
//	    			var data = json.message;
//	    			for(var key in data){
//	    				var item = $("#rrt-manager-form").find("[name='"+key+"']");//$("#"+key);
//						if(data[key] && item){
//							var item_type = item.attr("type");
//							if(undefined ==item_type || item_type=="" || "text"==item_type){
//		        				item.val(data[key]);
//		        			}else if(item_type=="radio" || "checkbox"==item_type){
//		        				item.each(function(){
//		        					if($(this).val()==String(data[key])){
//		        						$(this).prop({'checked':true});
//		        					}else{
//		        						$(this).prop({'checked':false});
//		        					}
//		        				}); 
//		        			}else{
//		        				item.val(data[key]);
//		        				if(key=="fwxtb"){
//		        					$(".rcmdServImg").each(function(){
//		        						if($(this).attr("src").indexOf($("#"+key).val())>-1){
//		        							$(this).addClass("selected");
//		        						}
//		        					});
//		        				}
//		        			}
//						}
//					}
//	    		}else{
//	    			Log.e(json.message);
//	    		}
//	    	});
//		}
//    };
//    MyJqGrid.prototype.bindGridDelEvent = function(url){
//    	var rowIds=$("#"+this.jqGridId).jqGrid("getGridParam","selarrrow");
//		if(rowIds.length==0){
//			Log.m("请选要删除的数据.");
//		}else{
//			var c = confirm("确认要删除这些数据吗?");
//			if(c){
//				var ids = [];
//				for(var i=0;i<rowIds.length;i++){
//					var id = $("#"+this.jqGridId).jqGrid("getRowData", rowIds[i]).ID;
//					ids.push(id);
//				}
//				var jqGridId = this.jqGridId;
//		    	$.getJSON(url+"?ids="+ids.toString(),function(json){
//		    		if(json&&json.success==true){
//		    			$("#"+jqGridId).trigger("reloadGrid");
//		    		}else{
//		    			Log.e(json.message);
//		    		}
//		    	});
//			}
//		}
//    };
	
	MyJqGrid.prototype.loadMyGrid = function(){
		$("#"+this.jqGridId).jqGrid({
			url: this.url,
			mtype: "GET",
			datatype: 'json',
			shrinkToFit:this.shrinkToFit,//为true时列宽百分比自适应，为false时定宽出水平滚动条
	        colModel: this.colModel,
	        width: this.width,
	        height: this.height,
	        pager: this.jqGridPagerId,
	        viewrecords: this.viewrecords,
	        sortname: this.sortname,
	        sortorder: this.sortorder,
	        multiselect: this.multiselect,
			rowNum :20,
			//altRows:true,//奇偶行样式添加
			loadonce: true,
	        gridview: false,
	        rownumbers:true,
	        rownumWidth:40,
	        reloadAfterSubmit:true,
	        jsonReader: {  
	            root: function (obj) { 
	                return obj.page.record;  
	            },  
	            page: function (obj) { 
	                return obj.page.page;  
	            },  
	            total: function (obj) { 
	                return obj.page.pageTotal;  
	            },  
	            records: function (obj) {  
	                return obj.page.total;  
	            },  
	            repeatitems: false
	        },
	        gridComplete : function(){
	        	if(callback!=null){
	        		callback();
	        	}
	        	
	        	
	        	
	        }
		});
		//$("#"+this.jqGridId).closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
	};
	
	MyJqGrid.prototype.complete = function(params){
		
	};
	
	MyJqGrid.prototype.query = function(params){
		
		if(params=="" || params==null){
			return ;
		}
		
		var urls = {url:params.url,datatype: "json"};
		//alert(1);
		//$("#"+this.jqGridId).setGridParam(urls).trigger("reloadGrid"); 
		$("#"+this.jqGridId).jqGrid('setGridParam',urls).trigger("reloadGrid");
		//alert(2);
		//$("#"+this.jqGridId).jqGrid('setGridParam',urls).trigger("reloadGrid");
		//$("#"+this.jqGridId).jqGrid('setGridParam',{postData:params.param}).trigger("reloadGrid");
	};
	
	MyJqGrid.prototype.selectedId=function(type, type1){
		
		var ids = $("#"+this.jqGridId).jqGrid('getGridParam','selarrrow');
		if(Utils.isEmpty(ids) && type1!=0){
			Log.e("请选择要操作的记录，当前选了0条记录!");
			return null;
		}
		if(1==type){
			if(ids.length>1){
				Log.e("只能选择一条记录，当前选择了"+ids.length+"条记录!");
				return null;
			}
		}
		var arr = new Array;
		for(i=0;i<ids.length; i++){ 
			arr.push($("#"+this.jqGridId).jqGrid('getRowData',ids[i]).id);
		}

		return arr.join(",");
	};
	
	
	MyJqGrid.prototype.selected=function(type){
		var ids = $("#"+this.jqGridId).jqGrid("getDataIDs");
		 for(var j=0;j<role_res.length;j++){
		    for(i=0;i<ids.length; i++){ 
				var row_id=$("#"+this.jqGridId).jqGrid('getRowData',ids[i]).ID;
				if(role_res[j].zyid==row_id){
				    $("#"+this.jqGridId).jqGrid('setSelection', i+1);
				}
		}
	 }
	};
}