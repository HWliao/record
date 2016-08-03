// http-client工具模块

exports.get = function(url, callback){

  url = require('url').parse(url);
  var hostname = url.hostname, port = url.port || 80;
  var pathname = url.pathname, query = url.query;
  if(query){
    path += '?' + query;
  }

  var client = require('http').creatClient(port, hostname);

  var request = client.request('GET', path, {'Host':hostname});
  request.end();

  request.on('response', function(response){
    response.setEncoding('utf8');

    var body = '';
    response.on('end',function(){
      if(callback){
        callback(response.statusCode,response.headers,body);
      }
    });
  });
};

exports.post = function(url,data,callback){
  
  url = require('url').parse(url);
  var hostname = url.hostname, port = url.port || 80;
  var pathname = url.pathname, query = url.query;
  if(query){
    path += '?' + query;
  }

  var type;
  if(data == null){
    data = '';
  }else if(data instanceof Buffer){
    type = 'application/octet-stream;';
  }else if(typeof data === 'string'){
    type = 'text/pain; charset=UTF-8';
  }else if(typeof data === 'object'){
    data = require('querystring').stringify(data);
    type = 'application/x-www--form-urlencoded';
  }

  var client = require('http').creatClient(port,hostname);
  var request = client.request('POST',pathname,{'Host':hostname,'Content-Type':type};

  request.write(data);
  request.end();

  request.on('response',function(response){
    response.setEncoding('utf8');

    var body = '';
    response.on('data',function(chunk){
      body += chunk;
    });

    response.on('end',function(){
      if(callback){
        callback(response.statusCode,response.headers,body);
      }
    });
  });
};