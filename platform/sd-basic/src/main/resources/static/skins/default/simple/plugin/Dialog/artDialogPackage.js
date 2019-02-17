/**
 * Created by 1 on 2017/11/21.
 */
function tipDialog(params){
    var that = this;
    that.className = "g-dialog " + params.className;
    that.width = params.width?params.width:400;
    that.height = params.height?params.height:"auto";
    that.title = params.title?params.title:"消息";
    that.padding = params.padding?params.padding:"0 0";
    that.content = params.content?params.content:"此内容为默认的内容，请在实例中的content属性设置您的提示内容";
    that.lock = params.lock == null?true:params.lock;
    that.ok = params.ok?params.ok:function (){};
    that.cancelBtnText = params.cancelBtnText?params.cancelBtnText:"取消";
    that.cancel = params.cancel == null?true:params.cancel;
    that.init = params.init?params.init:function(){};
    that.warning = params.warning?params.warning:false;
    tipDialog.prototype.creatDialog = function(){
        art.dialog({
            className:that.className,
            width: that.width,
            height:that.height,
            padding:that.padding,
            content: that.content,
            lock: that.lock,
            ok: that.ok,
            cancelVal: that.cancelBtnText,
            cancel: that.cancel,
            init:that.init,
            warning:that.warning
        });
    }//creatDialog.end
}