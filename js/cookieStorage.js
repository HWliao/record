// 基于cookie实现类似与localstorage的存储api
// maxage:存储有效时间
// path:作用域
function CookieStorage(maxage,path){

  // 获取一个存储全部cookie信息的对象
  var cookie = (function (){
    var cookie = {};
    var all = document.cookie;
    if(all === '') return cookie;
    var list = all.split('; ');
    for(var i = 0; i < list.length; i++){
      var c = list[i];
      var p = c.indexOf('=');
      var name = c.substring(0,p);
      var value = c.substring(p + 1);
      var value = decodeURIComponent(value);
      cookie[name] = value;
    }
    return cookie;
  })();

  // 将所有的cookie名字存储到一个数组中
  var keys = [];
  for(var key in cookie)
    keys.push(key);

  // 定义存储的公共属性和方法
  this.length = keys.length;

  // 第n个key
  this.key = function(n){
    if(n < 0 || n >= keys.length) return null;
    return keys[n];
  };
  // 指定名字的值
  this.getItem = function(name){
    return cookie[name] || null;
  };

  // 存储cookie 
  this.setItem = function(key,value){
    if(!(key in keys)){
      keys.push(key);
      this.length++;
    }
    
    cookie[name] = value;

    var c = key + '=' + encodeURIComponent(value);

    if(maxage) c += '; max-age=' + maxage;
    if(path) c += '; path' + path;

    document.cookie = c;
  };

  // 删除
  this.removeItem = function(key){
    if(!(key in keys)) return;

    delete cookie[key];
    for(var i = 0; i < keys.length; i++){
      if(keys[i] === key){
        keys.splice(i,1);
        break;
      }
    }
    this.length --;

    document.cookie = key +'=; max-age=0';
  };

  // 清除所有cookie
  this.clear = function(){
    for(var i = 0; i < keys.length; i++){
      document.cookie = keys[i] + '=; max-age=0';
    }
    cookie = {};
    keys = [];
    this.length = 0;
  };
}



// another cookie
var Cookie = {
        set: function(name, value, expires, path, domain) {
            if (typeof expires == "undefined") {
                expires = new Date(new Date().getTime() + 1000 * 3600 * 24 * 365);
            }
            document.cookie = name + "=" + escape(value) + ((expires) ? "; expires=" + expires.toGMTString() : "") + ((path) ? "; path=" + path: "; path=/") + ((domain) ? ";domain=" + domain: "");
        },
        get: function(name) {
            var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
            if (arr != null) {
                return unescape(arr[2]);
            }
            return null;
        },
        clear: function(name, path, domain) {
            if (this.get(name)) {
                document.cookie = name + "=" + ((path) ? "; path=" + path: "; path=/") + ((domain) ? "; domain=" + domain: "") + ";expires=Fri, 02-Jan-1970 00:00:00 GMT";
            }
        }
    };