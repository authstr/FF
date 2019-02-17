/**
 * Created by 1 on 2017/11/17.
 */
       
/*var returnIDs;
var currTreeObj;
var inputObj;
var initVal = "";*/
(function($){
    $.fn.extend({
        newcapecTree:function(params){
            var treeid = $(this).attr("id");
            var noSelectHTML = "<ul id='" + treeid + "' class='ztree reset-box-sizing J_containerWarp2' ></ul>";
            var selectHTML = "\
                <div id='"+ treeid +"Wrap' class='selectTreeBox'>\
                    <div class='input-group'>\
                        <input type='text' class='form-control' id='" + treeid + "Input' value=''/>\
                        <div class='input-group-btn'>\
                            <button type='button' class='btn btn-default caretbtn'>\
                            <span class='caret'></span>\
                            </button>\
                        </div>\
                    </div>\
                    <div class='TreeSelectDown'>\
                        <div class='TreeSelectDownWrap'>\
                            <ul id='" + treeid + "' class='ztree reset-box-sizing J_containerWarp2' ></ul>\
                        </div>\
                        <div class='TreeSelectDownBtn'>\
                            <button type='button' class='btn btn-success' id='" + treeid + "selokbtn'>确定</button>\
                            <button type='button' class='btn btn-default' id='" + treeid + "selcancelbtn'>取消</button>\
                        </div>\
                     </div>\
                 </div>";


            if(params.select){
                $(this).replaceWith($(selectHTML));
            }else{
                $(this).replaceWith($(noSelectHTML));
            }


            $(".caretbtn").bind("click",function(){
                $($(this).parents(".selectTreeBox").get(0)).find(".TreeSelectDown").toggle();
            });


            //require(["../plugin/ztree/jquery.ztree.all-3.5"],function(){


                var treeWrap = $("#"+treeid+"Wrap"),
                    okBtn = $("#"+treeid+"selokbtn"),
                    cancelBtn = $("#"+treeid+"selcancelbtn"),
                    inputObj = $("#"+treeid+"Input");

                    okBtn.bind("click",function(){
                       /* returnIDs = */$(this).inputHTML(treeid);
                       params.okBtnCallBack == null?null:params.okBtnCallBack();
                    });
                    cancelBtn.bind("click",function(){
                        treeWrap.find(".TreeSelectDown").hide();
                    });
                    inputObj.bind("focus",function(){
                        treeWrap.find(".TreeSelectDown").show();
                    });


                var setting = {
                        check: {
                            enable: params.checkenable == null? true : params.checkable,
                            chkStyle: params.chkStyle == null? "checkbox" : params.chkStyle,
                            radioType:params.radioType == null? "all" : params.radioType
                        },//check.end
                        data: {
                            simpleData: {
                                enable: true,
                                idKey:"id",
                                pIdKey:"pId"
                            }
                        },//data.end
                        async: {
                            enable:true,
                            url:params.url,
                            type:params.asyncType==null?"post":params.asyncType,
                            dataFilter:params.dataFilter==null?null:params.dataFilter
                        },
                        callback:{
                            onClick:params.onclick==null?null:params.onclick,
                            onAsyncSuccess:params.successCallback==null?null:params.successCallback
                        }
                    };//setting.end

                currTreeObj = $.fn.zTree.init($("#"+treeid), setting, params.treeZNodes);



                    params.radioParent = params.radioParent == null? true:params.radioParent;
                    if(params.chkStyle == "radio" && !params.radioParent){
                        var allNodes = currTreeObj.transformToArray(currTreeObj.getNodes());
                        $(allNodes).each(function(indx, elem){
                            if(elem.isParent){
                                elem.nocheck = true;
                            }
                        });
                        currTreeObj.refresh();
                    }


                    /*function inputHTML(currTreeObj,inputObj) {
                        var selectNames = "";
                        var checkedIDs = [];
                        var checkedNode = currTreeObj.getCheckedNodes(true);
                        $(checkedNode).each(function(indx, elem){
                            if(elem.children){
                                return true;
                            }else{
                                selectNames = selectNames + "," + elem.name;
                                inputObj.val(selectNames.substr(1));
                                checkedIDs.push(elem.id);
                            }
                        });
                        treeWrap.find(".TreeSelectDown").hide();
                        return checkedIDs;
                    }//点击确定获取选中的ID数组并返回,向输入框写入选项*/
            //});
        },


        inputHTML:function(treeid) {
            var inputObj = $("#"+treeid+"Input");
            var currTreeObj = $.fn.zTree.getZTreeObj(treeid);
            var selectNames = "";
            var checkedIDs = [];
            var treeWrap = $("#"+treeid+"Wrap")
            var checkedNode = currTreeObj.getCheckedNodes(true);
            $(checkedNode).each(function(indx, elem){
                /*if(elem.children){
                    return true;
                }else{*/
                    //console.log(elem.name);
                    selectNames = selectNames + "," + elem.name;
                    inputObj.val(selectNames.substr(1));
                    checkedIDs.push(elem.id);
                //}
            });
            treeWrap.find(".TreeSelectDown").hide();
            return checkedIDs;
        },//点击确定获取选中的ID数组并返回,向输入框写入选项


        setInitCheck:function(initCheckIDs){
            var treeid = $(this).attr("id");
            var currTreeObj = $.fn.zTree.getZTreeObj(treeid);
            var inputObj = $("#"+treeid+"Input");
            var initVal = inputObj.val();
            currTreeObj.checkAllNodes(false);
            $(initCheckIDs).each(function(indx,elem){
                var node = currTreeObj.getNodeByParam("id", elem);
                currTreeObj.checkNode(node);
                initVal += "," + node.name;
            });
            inputObj.val(initVal.substr(1));
        },


        getChkIDs:function(params){
            return returnIDs;
        }

    });
})(window.jQuery);
