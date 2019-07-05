/**
 * Created by 1 on 2017/11/17.
 */
function creatTree(){
    that = this;
    that.returnIDs = [];
    that.creatTreeFun = function(params){
        var vurrTreeObj = $.fn.zTree.init($("#"+params.treeid), params.setting, params.zNodes);
        that.returnIDs = params.initIDs;
        if(params.setting.check.chkStyle == "radio"){
            var allNodes = vurrTreeObj.transformToArray(vurrTreeObj.getNodes());
            console.log(allNodes);
            $(allNodes).each(function(indx, elem){
                if(elem.isParent){
                    elem.nocheck = true;
                }
            });
            vurrTreeObj.refresh();
        }
        $("#"+params.okbtnID).click(function(){
            that.returnIDs = inputHTML(this,vurrTreeObj,params.inputID);
        });

    that.returnIDSFun = function(){
        return that.returnIDs
    };
        $("#"+params.concelBtnID).bind("click",function(){
            $($(this).parents(".TreeSelectDown").get(0)).hide();
        });

        $("#"+params.inputID).bind("focus",function(){
            $($(this).parents(".selectTreeBox").get(0)).find(".TreeSelectDown").show();
        });

        var initVal = "";
        if(params.initIDs){
            $(params.initIDs).each(function(indx,elem){
                var node = vurrTreeObj.getNodeByParam("id", elem);
                vurrTreeObj.checkNode(node);
                initVal += ("," + node.name);
            });
            $("#"+params.inputID).val(initVal.substr(1));
        }
        return vurrTreeObj;
    }

}

function inputHTML(okbtn,treeObj,inputObj) {
    var selectNames = "";
    var checkedIDs = [];
    var checkedNode = treeObj.getCheckedNodes(true);
    $(checkedNode).each(function(indx, elem){
        if(elem.children){
            return true;
        }else{
            selectNames = selectNames + "," + elem.name;
            $("#"+inputObj).val(selectNames.substr(1));
            checkedIDs.push(elem.id);
        }
    });

    $($(okbtn).parents(".TreeSelectDown").get(0)).hide();
    return checkedIDs;
}


function caretShowTreeSeclectDown(triggerObj){
    $($(triggerObj).parents(".selectTreeBox").get(0)).find(".TreeSelectDown").toggle();
}