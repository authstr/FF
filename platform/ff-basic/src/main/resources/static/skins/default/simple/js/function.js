/**
 * Created by 10937 on 2017-09-27.
 * 工具类，放常用js处理方法
 */
//判断字符串所占字符数(中文占两个字符)
function strlen(str){
    var len = 0;
    for (var i=0; i<str.length; i++) {
        var c = str.charCodeAt(i);
        //单字节加1
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {
            len++;
        }
        else {
            len+=2;
        }
    }
    return len;
}

//验证输入内容不允许带有空格
function validateSpacee(name){
    var obj = document.getElementById(name);
    if(/\s/.test(obj.value)){
        art.dialog.alert("输入内容不允许带有空格!");
        document.getElementById(name).value="";
        document.getElementById(name).focus();
        return false;
    }else{
        return true;
    }
}


//验证身份证号格式
function isIdCardNo(num,key)//num是身份证号，key是文本框id
{
    var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
    var error;
    var varArray = new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;
    if ((intStrLen != 15) && (intStrLen != 18)) {
        error = "输入身份证号码长度不对！";
        art.dialog.alert(error);
        document.getElementById(key).value = "";
        return false;
    }
    for(i=0;i<intStrLen;i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
            art.dialog.alert(error);
            document.getElementById(key).value = "";
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i]*factorArr[i];
        }
    }
    if (intStrLen == 18) {
        var date8 = idNumber.substring(6,14);
        if (checkDate(date8) == false) {
            error = "身份证中日期信息不正确！";
            art.dialog.alert(error);
            document.getElementById(key).value = "";
            return false;
        }
        for(i=0;i<17;i++) {
            lngProduct = lngProduct + varArray[i];
        }
        intCheckDigit = 12 - lngProduct % 11;
        switch (intCheckDigit) {
            case 10:
                intCheckDigit = 'X';
                break;
            case 11:
                intCheckDigit = 0;
                break;
            case 12:
                intCheckDigit = 1;
                break;
        }
        if (varArray[17].toUpperCase() != intCheckDigit) {
            error = "身份证号错误!";
            art.dialog.alert(error);
            document.getElementById(key).value = "";
            return false;
        }
    }
    else{
        var date6 = idNumber.substring(6,12);
        if (checkDate(date6) == false) {
            art.dialog.alert(error);
            document.getElementById(key).value = "";
            return false;
        }
    }
    autoXX(num);
    return true;
}

function checkDate(date){
    return true;
}

function isNumber(oNum) {
    if(!oNum) return false;
    var strP=/^\d+(\.\d+)?$/;
    if(!strP.test(oNum)) return false;
    try{
        if(parseFloat(oNum)!=oNum) return false;
    }
    catch(ex)
    {
        return false;
    }
    return true;
}



//验证字符串是否是正整数，正整数返回true
function isDigit(s)  {
    var patrn=/^\d+$/;
    if (!patrn.exec(s)) return false
    return true

}


//图片缩放
function resize_img(pic,maxWidth,maxHeight){
    var img = new Image();
    img.src = pic.src;
    var hRatio;
    var wRatio;
    var Ratio = 1;
    var w = img.width;
    var h = img.height;
    wRatio = maxWidth / w;
    hRatio = maxHeight / h;
    if (maxWidth ==0 && maxHeight==0){
        Ratio = 1;
    }else if (maxWidth==0){//
        if (hRatio<1) Ratio = hRatio;
    }else if (maxHeight==0){
        if (wRatio<1) Ratio = wRatio;
    }else if (wRatio<1 || hRatio<1){
        Ratio = (wRatio<=hRatio?wRatio:hRatio);
    }
    if (Ratio<1){
        w = w * Ratio;
        h = h * Ratio;
    }
    pic.height = h;
    pic.width = w;

}
function floatSub(arg1,arg2){
    var r1,r2,m,n;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2));
    //动态控制精度长度    
    n=(r1>=r2)?r1:r2;
    return ((arg1*m-arg2*m)/m).toFixed(n);
}
function tipDialog(text){
    var dialog =art.dialog({
        className:"g-dialog g-tip-dialog",
        width: 400,
        height:"auto",
        padding:"0 0",
        content: '<i class="icon error"></i><span class="text">'+text+'</span>',
        lock: true,
        ok: function () {

        },
       /* cancelVal: '取消',
        cancel: true, //为true等价于function(){}*/
        init:function(){
            this._reset();
        }
    });
    
    
   
}



function SectionToChinese(section){
	  var strIns = '', chnStr = '';
	  var unitPos = 0;
	  var zero = true;
	  var chnNumChar = ["零","一","二","三","四","五","六","七","八","九"];
	  var chnUnitSection = ["","万","亿","万亿","亿亿"];
	  var chnUnitChar = ["","十","百","千"];
	  while(section > 0){
	    var v = section % 10;
	    if(v === 0){
	      if(!zero){
	        zero = true;
	        chnStr = chnNumChar[v] + chnStr;
	      }
	    }else{
	      zero = false;
	      strIns = chnNumChar[v];
	      strIns += chnUnitChar[unitPos];
	      chnStr = strIns + chnStr;
	    }
	    unitPos++;
	    section = Math.floor(section / 10);
	  }
	  return chnStr;
	}


function NumberToChinese(num){
	  var unitPos = 0;
	  var strIns = '', chnStr = '';
	  var needZero = false;
	  var chnNumChar = ["零","一","二","三","四","五","六","七","八","九"];
	  var chnUnitSection = ["","万","亿","万亿","亿亿"];  
	  var chnUnitChar = ["","十","百","千"];     
	 
	  if(num === 0){
	    return chnNumChar[0];
	  }
	 
	  while(num > 0){
	    var section = num % 10000;
	    if(needZero){
	      chnStr = chnNumChar[0] + chnStr;
	    }
	    strIns = SectionToChinese(section);
	    strIns += (section !== 0) ? chnUnitSection[unitPos] : chnUnitSection[0];
	    chnStr = strIns + chnStr;
	    needZero = (section < 1000) && (section > 0);
	    num = Math.floor(num / 10000);
	    unitPos++;
	  }
	 
	  return chnStr;
	}