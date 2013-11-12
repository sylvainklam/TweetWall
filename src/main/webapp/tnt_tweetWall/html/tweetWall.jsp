<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>

<script text="text/javascript">
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
				if (url.indexOf("start") != -1) mdiv.innerHTML = "<font color=green>server started</font>";
				else if (url.indexOf("stop") != -1) mdiv.innerHTML = "<font color=red>server stopped</font>";
				else mdiv.innerHTML = "<p>????</p>";
			}
		}
	}
}
</script>

<c:url value="${url.base}${currentNode.path}.startTWS.do" var="startTWSURL"/>
<c:url value="${url.base}${currentNode.path}.stopTWS.do" var="stopTWSURL"/>

<div class="container">
	<div class="page-header">
		<h1>${currentNode.properties.title.string}</h1>
	</div>
	<c:if test="${renderContext.editMode}">
		<div class="starter-template">
	        <h1>You are now on Edit Mode</h1>
	        <p class="lead">You are going to track tweets with keyword(s) : <b>${currentNode.properties.track.string}</b><br> Go to Preview Mode and click on 'Start' button to launch Tweet Wall.</p>
	    </div>
	</c:if>
	<c:if test="${renderContext.previewMode}">
		<div class="starter-template">
	        <h1>You are now on Preview Mode</h1>
	        <p class="lead">You are going to track tweets with keyword(s) : <b>${currentNode.properties.track.string}</b><br> Click on 'Start' button to launch Tweet Wall.</p>
	    </div>
		<div class="btn-group">
		  <a href="#" onclick="sendCmdServer('${startTWSURL}');"><button type="button" class="btn btn-default" id="btn_start_server">Start</button></a>
		  <a href="#" onclick="sendCmdServer('${stopTWSURL}');"><button type="button" class="btn btn-default" id="btn_stop_server">Stop</button></a>
		</div>
		<div id="serverStatus"></div>
	</c:if>
	<c:if test="${renderContext.liveMode}">
		<div class="starter-template">
	        <h1>You are now on Live Mode</h1>
	        <p class="lead">Have fun with this tweet wall !!</p>
	    </div>
	</c:if>
</div>