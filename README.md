# record
学习工作问题记录
# /html
在html使用中的一些问题痛点


NSIS $DESKTOP桌面没有快捷方式 20
是因为我的桌面文件夹换成了D盘的某个文件夹，但是C盘的原来的桌面文件夹还在，所以NSIS制作的安装包每次安装都安在了C盘那个文件夹里，但是桌面显示的D盘的路径，所以桌面上没有快捷方式，$DESKTOP不能自动检测当前系统的桌面路径吗？改怎么解决，求助！
已经解决：
是因为用户问题，各个不同的PC用户，桌面路径是不一样的，但是电脑上会有一个公共的桌面路径，一般qq啊，360啊都是在这创建的桌面快捷方式，这样在修改其他用户的桌面路径的时候，这个路径是不影响的，在此文件夹下的桌面快捷方式，依然会留在桌面上，不会消失；
使用nsis的时候，通过使用 SetShellVarContext all命令，来指定$DESKTOP指向公共桌面文件夹路径；使用SetShellVarContext Current来指定$DESKTOP指向当前用户桌面文件夹。
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
  var o = {
    "M+": this.getMonth() + 1,                 //月份
    "d+": this.getDate(),                    //日
    "h+": this.getHours(),                   //小时
    "m+": this.getMinutes(),                 //分
    "s+": this.getSeconds(),                 //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    "S": this.getMilliseconds()             //毫秒
  };
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt))
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
};
