#全局对象 global object
  当javascript解释器启动时，或者任何web浏览器加载新页面时，它将创建一组新的对象，并给予它定义的一组初始属性：
* 全局属性，比如undefined、Infinity、NaN
* 全局函数，比如isNaN()、parseInt()
* 构造函数，比如Date()、RegExp()、String()、Object()和Array()
* 全局对象，比如Math和JSON
#获取全局对象
  var global = this;  // 在js文件最顶层定义一个引用this的变量，浏览器中window对象及为全局对象