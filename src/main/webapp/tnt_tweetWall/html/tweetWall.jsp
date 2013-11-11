<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>

<script text="text/javascript">
var requete;

function sendCmdServer(url)
{
	alert("url : "+url)
	if (window.XMLHttpRequest) {
		requete = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		requete = new ActiveXObject("Microsoft.XMLHTTP");
	}
	requete.open("GET", url, true);
	requete.send(null);
	if (requete.readyState == 4) {
		if (requete.status == 200) {
			
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
	<c:if test="${ renderContext.editMode}">
		<div class="starter-template">
	        <h1>You are now on Edit Mode</h1>
	        <p class="lead">You are going to track tweets with keyword(s) : <b>${currentNode.properties.track.string}</b><br> Go to Preview Mode and click on 'Start' button to launch Tweet Wall.</p>
	    </div>
	</c:if>
	<c:if test="${ renderContext.previewMode}">
		<div class="starter-template">
	        <h1>You are now on Preview Mode</h1>
	        <p class="lead">You are going to track tweets with keyword(s) : <b>${currentNode.properties.track.string}</b><br> Click on 'Start' button to launch Tweet Wall.</p>
	    </div>
		<div class="btn-group">
		  <a href="#" onclick="sendCmdServer('${startTWSURL}');"><button type="button" class="btn btn-default" id="btn_start_server">Start</button></a>
		  <a href="#" onclick="sendCmdServer('${stopTWSURL}');"><button type="button" class="btn btn-default" id="btn_stop_server">Stop</button></a>
		</div>
	</c:if>
	<!-- 
	<c:if test="${ not renderContext.editMode}">
		<tweetwall:display />
	</c:if>
	 -->
</div>