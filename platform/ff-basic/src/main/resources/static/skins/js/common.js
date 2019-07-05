
//日期格式化
Date.prototype.format = function (format) {
    var args = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var i in args) {
        var n = args[i];
        if (new RegExp("(" + i + ")").test(format)) format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
    }
    return format;
};
//form的封装参数数组转为参数字面量
function paraArrayToLiteral(para) {
    var literal={};
    for(var i=0;i<para.length;i++){
        literal[para[i].name]=para[i].value
    }
    console.log(literal);
    return literal;
}

//将字面量封装成参数字符串
function  LiteralToString(literal) {
    var index=0;
    var res="";
    for(var key in literal){
        if(index==0){
            res=res+"?";
        }else{
            res=res+"&";
        }
        res=res+key+"="+literal[key];
        index++;
    }
    return res;
}

//将字面量数组封装成参数字符串
function  ArrayLiteralToString(array) {
    var index=0;
    var res="";
    for(var i=0;i<array.length;i++){
        var literal=array[i];
        for(var key in literal){
            if(index==0){
                res=res+"?";
            }else{
                res=res+"&";
            }
            res=res+key+"="+literal[key];
            index++;
        }
    }
    return res;
}

//将数组封装成参数字符串
function  ArrayToString(paraName,array) {
    var res="";
    for(var i=0;i<array.length;i++){
        if(i==0){
            res=res+"?";
        }else{
            res=res+"&";
        }
        res=res+paraName+"="+array[i];
    }
    return res;
}


//获取字面量数组的第一个id项
function paraGetOneId(para) {
    if(para.length==0){
        return null;
    }else{
        var temp=para[0];
        return temp.id;
    }
}
//获取字面量数组的id项
function paraGetId(para) {
    var ids=[];
    if(para.length==0){
        return null;
    }
    for(var i=0;i<para.length;i++){
        ids.push(para[i].id)
    }
    return ids;

}

/**通过JSON字符串向界面对象放值的方法
 * 例子
 * $("#form1").setFormValues({id:111,name:'ccc',dyh:'aaaa' ,khfs:'-1'});
 * auth sntey
 * @param {Object} $
 */
(function($) {
    $.fn.setFormValues=function(json){
        var form1 = $(this);
        $.each(json,function(id,value){
            /**该功能是为jquery版的 combox提供**/
            var this_obj = form1.find("[name='"+id+"']");
            var this_obj_type = this_obj.attr("type");

            if(undefined ==this_obj_type || this_obj_type=="" || "text"==this_obj_type){
                this_obj.val(value);
            }else if(this_obj_type=="radio" || "checkbox"==this_obj_type){
                if(value instanceof  Array){
                    this_obj.each(function(){
                        for(i=0;i<value.length;i++){
                            if($(this).val()==value[i]){
                                $(this).prop({'checked':true});
                            }
                        }
                    });
                }else{
                    this_obj.each(function(){
                        if($(this).val()==value){
                            $(this).prop({'checked':true});
                        }else{
                            $(this).prop({'checked':false});
                        }
                    });
                }

            }else{
                this_obj.val(value);
            }
        });
    };
    $.fn.clearFormValues=function(){
        var form1 = $(this);
        form1.find("input").each(function(){
            var this_obj = $(this);
            var this_obj_type = $(this).attr("type");
            if(undefined ==this_obj_type || this_obj_type=="" || "text"==this_obj_type || "hidden"==this_obj_type){
                this_obj.val("");
            }else if(this_obj_type=="radio" || "checkbox"==this_obj_type){
                this_obj.each(function(){
                    this_obj.prop({'checked':false});
                });
            }else{
                this_obj.val("");
            }
        });
        form1.find("select").each(function(){
            $(this).val("");
        });
        form1.find("textarea").each(function(){
            $(this).val("");
        });
    };
    $.fn.validate = function(){
        var form1 = $(this);
        b = true;
        $.each(form1.find(".must"),function(i,v){
            var cv = $(this).val();
            if(cv.replace(/\s/g,"")==""){
                $(this).focus();
                b  = false;
                return b;
            }
        });

        return b;
    };
    /****
     * 判断对象的值是否为空.
     * @memberOf {TypeName}
     * @return {TypeName}
     */
    $.fn.isEmpty=function(){
        var cv = $(this).val();
        return cv.replace(/\s/g,"")==""
    };
})(jQuery);