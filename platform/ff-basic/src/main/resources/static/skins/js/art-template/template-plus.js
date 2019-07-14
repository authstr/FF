template.defaults.imports.dateFormat = function (date, format) {
	if(date == null){
		return "";
	}
    date = new Date(date); //新建日期对象

    /*日期字典*/
    var map = {
        "M": date.getMonth() + 1, //月份 
        "d": date.getDate(), //日 
        "h": date.getHours(), //小时 
        "m": date.getMinutes(), //分 
        "s": date.getSeconds(), //秒 
        "q": Math.floor((date.getMonth() + 3) / 3), //季度 
        "S": date.getMilliseconds() //毫秒 
    };

    /*正则替换*/
    format = format.replace(/([yMdhmsqS])+/g, function(all, t){
        var v = map[t];
        if(v !== undefined){
            if(all.length > 1){
                v = '0' + v;
                v = v.substr(v.length-2);
            }
            return v;
        }
        else if(t === 'y'){
            return (date.getFullYear() + '').substr(4 - all.length);
        }
        return all;
    });

    /*返回自身*/
    return format;
};

/*将时间戳改成分钟*/
template.defaults.imports.dateMinuteFormat = function (data) {  
    if(data<0){
    	return 0;
    }
    var minute =Math.round((data / (1000 * 60)));  
    return minute;   
};





template.defaults.imports.nvl = function(d,def){
	if(d == null){
		return def;
	}
	
	if((d+"").replace(/\s/g,"")==""){
		return def;
	}else{
		return d;
	}
};

template.defaults.imports.varlimit = function(d,limit){
	if(d == null){
		return "";
	}
	
	if(d.length>limit){
		return d.substring(0,limit)+"……";
	}else{
		return d;
	}
};

template.defaults.imports.numEmpty = function(d,def){
	if(d == null || d==undefined){
		return def;
	}
	return d;
};

template.defaults.imports.isExist = function(d,k){
    return d.indexOf(k) != -1;
};

