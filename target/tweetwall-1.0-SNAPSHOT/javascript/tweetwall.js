var requete;

function sendCmdServer(url)
{
	if (window.XMLHttpRequest) {
		requete = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		requete = new ActiveXObject("Microsoft.XMLHTTP");
	}
	requete.open("GET", url, true);
	requete.onreadystatechange = majIHM(url);	
	requete.send(null);
}

function majIHM(url)
{
	return function() {
		if (requete.readyState == 4) {
			if (requete.status == 200) {
				mdiv = document.getElementById("serverStatus");
				if (url.indexOf("start") != -1) mdiv.innerHTML = "<font color=green>Websocket Server started</font>";
				else if (url.indexOf("stop") != -1) mdiv.innerHTML = "<font color=red>Websocket Server stopped</font>";
			}
		}
	}
}

$(function() {
    if (window["WebSocket"]) {
            var conn = new WebSocket('ws://localhost:8000/websocket/tw');

            conn.onopen = function(evt) {
            	$("#log").prepend('<div>Tweet wall launched.</div>');
            };
            
            conn.onmessage = function(evt) {
            	var d = $.parseJSON(evt.data);
                var tweet = d.status;
                var status = tweet.replace(/((https?|s?ftp|ssh)\:\/\/[^"\s\<\>]*[^.,;'">\:\s\<\>\)\]\!])/g, function(url) {
                 return '<a href="'+url+'">'+url+'</a>';
                 }).replace(/\B@([_a-z0-9]+)/ig, function(reply) {
                 return reply.charAt(0)+'<a href="http://twitter.com/'+reply.substring(1)+'">'+reply.substring(1)+'</a>';
                 });
                $('#log').prepend('<h3><a href=\"http://twitter.com/' + d.screenname + '\">' + d.screenname + '</a><small> ' + d.user + ' </small></h3><blockquote><p>' + status + '</p> <small>' + d.date + '</small></blockquote>');    
            };

            conn.onclose = function(evt) {
            	$("#log").prepend('<div>Tweet wall stopped.</div>');
            };
            
    } else {
            $("#log").prepend('<div>Your browser does not support web sockets</div>')
    }
});