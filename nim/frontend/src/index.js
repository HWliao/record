/**
 * Created by lenovo on 2016/9/19.
 */

var data = {};
var nim = NIM.getInstance({
  // debug: true,
  appKey: 'appKey',
  account: 'account',
  token: 'token',
  onconnect: onConnect,
  onwillreconnect: onWillReconnect,
  ondisconnect: onDisconnect,
  onerror: onError
});

function onConnect() {
  console.log('');
}