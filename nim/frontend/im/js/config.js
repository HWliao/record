(function() {
    // 配置
    var envir = 'test';
    var configMap = {
        test: {
            appkey: '7ca1eb5e5cd9416289ef35d78c67085a',
            url:'http://localhost:8080'
        },
        pre:{
    		appkey: '45c6af3c98409b18a84451215d0bdd6e',
    		url:'http://preapp.netease.im:8184'
        },
        online: {
           appkey: '45c6af3c98409b18a84451215d0bdd6e',
           url:'https://app.netease.im'
        }
    };
    window.CONFIG = configMap[envir];
}())