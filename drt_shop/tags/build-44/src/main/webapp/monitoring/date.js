function initDate(){
	$.fn.datetimepicker.dates['zh'] = {  
            days:       ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六","星期日"],  
            daysShort:  ["日", "一", "二", "三", "四", "五", "六","日"],  
            daysMin:    ["日", "一", "二", "三", "四", "五", "六","日"],  
            months:     ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月","十二月"],  
            monthsShort:  ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],  
            meridiem:    ["上午", "下午"],  
            today:       "今天"  
    }; 
	
    var date = $('#datetimepicker').datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh',
        minView: "month",//设置只显示到月份
        autoclose:true,//选中关闭
        todayBtn: true//今日按钮
    });
}


