/**
 * 工具类
 * author wl
 * data 2015-09-28
 */
var Util ={};
//获取当前时间（yyyy-mm-dd HH:MM:ss）
Util.getNowFormatDate = function() {
    var date = new Date();
    var currentdate = this.formatDate(date);
    return currentdate;
};

Util.formatDate = function(d){
	var date = new Date(d);
	var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var d = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return d;
}
