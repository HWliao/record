// jquey 中可以使用trigger() 手动触发事件,并且可以专递参数
// 传递的参数作为处理函数的第二个参数使用,第一个参数为jquery封装后的event对象
// jquery中,处理函数返回值始终有意义,
// 当返回false时,则与之关联的默认行为以及该事件接下来的冒泡行为都会被取消


// jquey的事件机制
// jquey bind
// 1.参数传递一个eventtype,function  简单调用
// 2.eventtype,data,function data将绑定到event的data属性,不需要再闭包
// 3.eventtype 命名空间,eventtype可以用.分隔命名空间;以空格注册多个事件
// 4.第一个参数传递对象,属性为eventtype

// jquey unbind
// 1.不带参数,取消全部handler
// 2.带一个字符串参数,取消该类型的参数
// 3.一个字符串,一个function 取消指定函数
// 4.给一个对象,将遍历这个对象,属性作为eventtype,值作为function,来删除handler

// jquery trigger
// trigger(eventtype[,...]) 第二个参数作为handler的参数传递给handler
// trigger接收命名空间形式的eventtype

// 自定义事件
// 使用bind可以绑定任意事件,使用trigger可以触发
// 利用这种机制,可以制定一时间,在实现一些发布订阅模型,或者观察者模式时候非常有效
// 用户点击'logoff' 按钮时,广播一个自定义事件
// 给任何需要保存数据的观察者,然后导航到logoff页面
$('logoff').click(function(){
  $.event.trigger('logoff');
  window.location.href = 'logoff.php';
});

// 实时事件 原理:通过事件冒泡,并判断类型解决
$(document).delegate('a','mouseover',function(e){
  console.log('test');
});
// 另外一个更加广泛的实时事件机制:live.原理依然是冒泡
$('#log').live('mouseover',function(){
  console.log('test');
});

































