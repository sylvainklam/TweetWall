<%@ taglib prefix="tweetwall" uri="http://www.tweetwall.org/jahia" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>

<div class="container">
	<div class="page-header">
		<h1>${currentNode.properties.title.string}</h1>
	</div>
	<c:if test="${ renderContext.editMode}">
	
	<div class="starter-template">
        <h1>You are now on Edit Mode</h1>
        <p class="lead">You are going to track tweets with keyword(s) : <b>${currentNode.properties.track.string}</b><br> Click on 'Start' button to launch Tweet Wall.</p>
    </div>
	<div class="btn-group">
	  <form action="<c:url value='${url.base}${linked}.startTW.do'/>" method="post" name="startTWForm" id="startTWForm">
	  <button type="submit" class="btn btn-default" onclick="">Start</button>
	  </form>
	  <button type="button" class="btn btn-default" onclick="">Stop</button>
	</div>
	</c:if>
	<c:if test="${ not renderContext.editMode}">
	<tweetwall:display />
	</c:if>
</div>