// 难得测试,只有/test/mirror能用
// 这是一个简单的node http服务器,能处理当前目录文件
// 并能实现两种特殊的url用与测试
// 用http://localhost:8000 或者 http://127.0.0.1:8000 连接这个服务器

// 首先加载所有要用的模块
var http = require('http');
var fs = require('fs');

var server = new http.Server();
server.listen(8000);

// node使用on方法监听事件处理程序
// 当服务器得到新的请求,则运行处理函数
server.on('request',function(request, response){
  // 解析请求中url
  var url = require('url').parse(request.url);

  // 特殊url会让服务器在发送响应前先等待
  // 此处用于模拟缓慢的网路请求
  if(url.pathname == '/test/delay'){
    var delay = Number.parseInt(url.query) || 2000;

    response.writeHead(200, {'Content-Type':'text/plain;charset=UTF-8'});

    response.write('Sleep For Delay: ' + delay + ' milliseconds.');

    setTimeout(function(){
      response.write('done.');
      response.end();
    }, delay);
  }else if(url.pathname == '/test/mirror'){
    response.writeHead(200, {'Content-Type':'text/plain;charset=UTF-8'});

    response.write(request.method + ' ' + response.url + ' HTTP/' + request.httpVersion + '\r\n');

    for(var h in request.headers){
      response.write(h + ': ' + request.headers[h] + '\r\n');
    }

    response.write('\r\n');

    request.on('data', function(chunk){
      response.write(chunk);
    });

    request.on('end', function(){
      response.end();
    });
  }else{
    var filename = url.pathname.substring(1);

    var type;
    switch(filename.substring(filename.lastIndexOf('.') + 1)){
      case 'html'     : 
      case 'htm'      :   type = 'text/html; charset=UTF-8'; break;
      case 'js'       :   type = 'application/javascript; charset=UTF-8'; break;
      case 'css'      :   type = 'text/css; charset=UTF-8'; break;
      case 'txt'      :   type = 'text/plain; charset=UTF-8'; break;
      case 'manifest' :   type = 'text/cache-manifest; charset=UTF-8'; break;
      default         :   type = 'application/octet-stream; charset=UTF-8'; break;
    }

    fs.readfile(filename,function(err, content){
      if(err){
        response.writeHead(404, {'Content-Type':'text/plain; charset=UTF-8'});

        response.write(err.message);

        response.end();
      }else{
        response.writeHead(200, {'Content-Type': type});

        response.write(content);
        response.end();
      }
    });
  }
});