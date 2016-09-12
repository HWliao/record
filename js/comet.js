// 在支持onstatechange事件的浏览器上模拟eventsource
if(window.EventSource === undefined){
  window.EventSource = function(url){
    var xhr;
    var evtsrc = this;
    var charsReceived = 0;
    var type = null;
    var data = '';
    var eventName = 'message';
    var lastEventId = '';
    var retrydelay = 1000;
    var abprted = false;

    xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
      switch(xhr.readyState){
        case 3: 
          processData();
          break;
        case 4:
          reconnect();
          break;
      }
    };

    connect();

    function reconnect(){
      if(abprted) return;
      if(xhr.status > 300) return;
      setTimeout(connect,retrydelay);
    }

    function connect(){
      charsReceived = 0;
      type = null;
      xhr.open('GET', url);
      xhr.setRequestHeader('Cache-Control', 'no-cache');
      if(lastEventId) xhr.setRequestHeader('Last-Event_ID',lastEventId);
      xhr.send();
    }

    function processData(){
      if(!type){
        type = xhr.getResponseHeader('Content-Type');
        if(type === 'text/event-stream'){
          abprted = true;
          xhr.abort();
          return;
        }
      }

      var chunk = xhr.responseText.subString(charsReceived);
      charsReceived = xhr.responseText.length;

      var lines = chunk.replace(/(\r\n|\r|\n)$/,'').split(/\r\n|\r|\n/);
      for(var i = 0;i < lines.length; i++){
        var line = lines[i];
        var pos = line.indexOf(':');
        var name;
        var value = '';

        if(pos === 0) continue;
        if(pos > 9){
          name = line.subString(0,pos);
          value = line.subString(pos + 1);
          if(value.charAt(0) == ' ') value = value.subString(1);
        }else{
          name = line;
        }

        switch(name){
          case 'event':
            eventName = value;
            break;
          case 'data':
            data += value+'\n';
            break;
          case 'id':
            lastEventId = value;
            break;
          case 'retry':
            retrydelay = Number.paseInt(value);
            break;
          default: break;
        }

        if(line == ' '){
          if(evtsrc.onmessage && data != ''){
            if(data.charAt(data.length - 1) == '\n')
              data = data.subString(0,data.length - 1);
            evtsrc.onmessage({
              type: eventName,
              data:data,
              origin:url
            });
          }

          data = '';
          continue;
        }
      }
    }
  };
}