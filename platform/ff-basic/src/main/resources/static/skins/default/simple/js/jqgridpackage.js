/**
 * Created by 1 on 2017/11/14.
 */
    var jqgridObjArr = [];//将所有jg对象作为数据成员存放
    function myJqGrid(param){ 
        var that = this;
            that.jqGridId = "jqGridTable";
            that.jqGridPagerID = "jqGridTablePager";
            that.multiselect = true;
            that.jqGridActionbarId = "jqGridActionbarId";
            that.jsonReader = {
            		root: function (obj) { 
    	                return obj.page.record;  
    	            },  
    	            page: function (obj) { 
	   	            	return obj.page.page;  
    	            },  
    	            total: function (obj) { //当出现定位在第2页，而查出的数据只有一页时，容错。
    	            	if(obj.page.page>obj.page.pageTotal){
    	            		return obj.page.page;  
	   	            	}else{
	   	            		 return obj.page.pageTotal;  
	   	            	}
    	            },  
    	            records: function (obj) {  
    	                return obj.page.total;  
    	            },  
    	            repeatitems: false
                                },

            that.isinit = false;
            callback = null;

        if(param){
            that.jqGridId = param.jqGridId?param.jqGridId:"jqGridTable";
            that.jqGridPagerID = param.jqGridPagerID?param.jqGridPagerID:"jqGridTablePager";
            that.postData = param.postData?param.postData:{};
            that.caption = param.caption?param.caption:null;
            that.rownumbers =param.rownumbers==null?true:param.rownumbers;
            that.rowNum = param.rowNum?param.rowNum:20;
            that.url = param.url?param.url:"您尚未设置数据URL";
            that.rowList = param.rowList?param.rowList:[20,40,60];
            that.datatype = param.datatype?param.datatype:"json";
            that.jsonReader = param.jsonReader?param.jsonReader:that.jsonReader;
            that.colModel = param.colModel?param.colModel:[];
            that.width = param.noFullScreen?param.width:(param.widthMinus?($(window).width() - param.widthMinus - 20):($(window).width() - 20));
            that.height = param.noFullScreen?param.height:(param.heightMinus?($(window).height() - param.heightMinus - 100):($(window).height()-100));
            that.viewrecords = param.viewrecords==null?true:param.viewrecords;
            that.shrinkToFit = param.shrinkToFit==null?true:Boolean(param.shrinkToFit);
            that.sortname = param.sortname?param.sortname:null;
            that.sortorder = param.sortorder==null?"asc":param.sortorder;
            that.multiselect = param.multiselect==null?true:Boolean(param.multiselect);

            that.delBtnId = param.delBtnId?param.delBtnId:null;

            callback = param.callback?param.callback:null;
        }

        that.currTableJqObject = $("#"+that.jqGridId);
        that.currTableDelBtnId = $("#"+that.delBtnId);

        myJqGrid.prototype.createJqGrid = function(){
            that.currTableJqObject.jqGrid({
                pager: that.jqGridPagerID,
                postData:that.postData,
                caption :that.caption,
                rownumbers:that.rownumbers,
                rowNum: that.rowNum,
                url:that.url,
                rowList:that.rowList,
                datatype:that.datatype,
                jsonReader:that.jsonReader,
                colModel: that.colModel,
                width: that.width,
                height: that.height,
                viewrecords: that.viewrecords,
                shrinkToFit:that.shrinkToFit,
                sortname: that.sortname,
                sortorder: that.sortorder,
                multiselect: that.multiselect,
                rownumWidth:30,
                reloadAfterSubmit:true,
              
            
                
            });
        }; 

        //ajax成功回调此方法，用于数据改变后的表格重绘
        //myJqGrid.prototype.reloadCurrJg = function(){
        //    that.currTableJqObject.trigger("reloadGrid");
        //}

        that.currTableDelBtnId.bind("click",function(){
                var ids = that.currTableJqObject.jqGrid("getGridParam","selarrrow");
                if(ids.length == 0){
                    alert("请选择您要删除的行数！");
                    return;
                }
                if(confirm("您确定要删除选中的记录？")){
                    $(ids).each(function(indx, elem){
                        that.currTableJqObject.jqGrid("delRowData",this);
                    });
                    //that.selectIds = null;
                    that.selectIds = ids;
                }
        });
        
        myJqGrid.prototype.selectOne=function(){
        	return this.selectedId(1);
        };
        myJqGrid.prototype.selectMany=function(){
        	return this.selectedId(0);
        };
        
        myJqGrid.prototype.selectedId=function(type, type1){
    		var ids = that.currTableJqObject.jqGrid('getGridParam','selarrrow');
    		if(Utils.isEmpty(ids) && type1!=0){
    			Message.error("请选择要操作的记录，当前选了0条记录!");
    			return null;
    		}
    		if(1==type){
    			if(ids.length>1){
    				Message.error("只能选择一条记录，当前选择了"+ids.length+"条记录!");
    				return null;
    			}
    		}
    		var arr = new Array;
    		for(i=0;i<ids.length; i++){ 
    			if(that.currTableJqObject.jqGrid('getRowData',ids[i]).ID==null || that.currTableJqObject.jqGrid('getRowData',ids[i]).ID==undefined){
    				arr.push(that.currTableJqObject.jqGrid('getRowData',ids[i]).id);
    			}else{
    				arr.push(that.currTableJqObject.jqGrid('getRowData',ids[i]).ID);
    			}
    			
    		}

    		return arr.join(",");
    	};
        myJqGrid.prototype.searchMethod = function(searchParam){
        	var urlplus = "";
            if(searchParam instanceof Array){
                if(searchParam != null && searchParam!=undefined && searchParam.length>0){
                    for(var i=0; i<searchParam.length;i++){
                        urlplus+="&"+searchParam[i].name+"="+searchParam[i].value;
                    }
                }
                if(urlplus != ""){
                    urlplus=this.url+"?"+ urlplus.substring(1);
                }
            }else{
                urlplus=this.url+"?";
                for(key in searchParam){
                    urlplus+=key+"="+searchParam[key];
                }

            }

            that.currTableJqObject.jqGrid("setGridParam",{
                    datatype:'json',
                    url:urlplus
                    //postData:searchParam
                }).trigger("reloadGrid");
            }



        myJqGrid.prototype.resizeCurrTable = function(){
            var currwidth = param.widthMinus?$(window).width() - param.widthMinus - 20:$(window).width() - 20;
            var currHeight = param.heightMinus?$(window).height() - param.heightMinus - 100:$(window).height()-100;
            that.currTableJqObject
                .jqGrid("setGridWidth",currwidth)
                .jqGrid("setGridHeight",currHeight).trigger("reloadGrid");
        }
    }