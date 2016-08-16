function Keymap(bindings){
  this.map = {};
  if(bindings){
    for(var name in bindings) this.bind(name ,bindings[name]);
  }
}

Keymap.prototype.bind = function(key,func){
  this.map[Keymap.normalize(key)] = func;
};

Keymap.prototype.unbind = function(key){
  delete this.map[Keymap.normalize(key)];
};

Keymap.prototype.install = function(element){
  var keymap = this;
  
  function handler(event){
    return keymap.dispatch(event,element);
  }

  if(element.addEventListener){
    element.addEventListener('keydown',handler,false);
  }else if(element.attachEvent){
    element.attachEvent('onkeydown',handler);
  }
};

Keymap.prototype.dispatch = function(event,element){
  var modifers = '';
  var keyname = null;

  // TOOMORE....
};