var requete;

function sendCmdServer(url,msg)
{
	if (window.XMLHttpRequest) {
		requete = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		requete = new ActiveXObject("Microsoft.XMLHTTP");
	}
	requete.open("GET", url, true);
	requete.onreadystatechange = majIHM(url,msg);	
	requete.send(null);
}

function majIHM(url,msg)
{
	return function() {
		if (requete.readyState == 4 && requete.status == 200) {
			mdiv = document.getElementById("serverStatus");
			if (url.indexOf("start") != -1) {
				mdiv.innerHTML = "<font color=green>"+msg+"</font>";
				if (localStorage.getItem("TWSURL") == undefined 
						|| localStorage.getItem("TWSURL") == ""
						|| localStorage.getItem("TWSURL") == null)
				{
					localStorage.setItem("TWSURL", requete.responseText);
				}
			}
			else if (url.indexOf("stop") != -1) mdiv.innerHTML = "<font color=red>"+msg+"</font>";
		}
	}
}

function goFullscreen(id) {
    var element = document.getElementById(id);
    // Firefox
    if (element.mozRequestFullScreen) {
    	element.mozRequestFullScreen();
    } 
    // Chrome
    else if (element.webkitRequestFullScreen) {
    	element.webkitRequestFullScreen();
    // IE
    } else if (element.requestFullscreen) {
    	element.requestFullscreen();
    }
}

$(function() {
    if (window["WebSocket"]) {
		var conn = new WebSocket(localStorage.getItem("TWSURL"));

        conn.onopen = function(evt) {
        	$("#log").prepend('<div><font color=green>Tweet wall launched.</font></div>');
        };
        
        conn.onmessage = function(evt) {
        	var d = $.parseJSON(evt.data);
            var tweet = d.status;
            var status = tweet.replace(/((https?|s?ftp|ssh)\:\/\/[^"\s\<\>]*[^.,;'">\:\s\<\>\)\]\!])/g, function(url) {
             return '<a href="'+url+'">'+url+'</a>';
             }).replace(/\B@([_a-z0-9]+)/ig, function(reply) {
             return reply.charAt(0)+'<a href="http://twitter.com/'+reply.substring(1)+'">'+reply.substring(1)+'</a>';
             });
            $('#log').prepend('<h3><a href=\"http://twitter.com/' + d.screenname + '\"><img src=\"'+d.iconURL+'\"></a><small> ' + d.user + ' </small></h3><blockquote><p>' + status + '</p> <small>' + d.date + '</small></blockquote>');    
        };

        conn.onclose = function(evt) {
        	$("#log").prepend('<div><font color=red>Tweet wall stopped.</font></div>');
        };        
    } else {
    	$("#log").prepend('<div><font color=red>Your browser does not support web sockets</font></div>')
    }
});