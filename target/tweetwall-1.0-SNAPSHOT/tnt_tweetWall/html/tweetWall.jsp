<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="tweetwall" uri="http://www.tweetwall.org/jahia" %>

<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>

<template:addResources type="javascript" resources="tweetwall.js"/>

<c:url value="${url.base}${currentNode.path}.startTWS.do" var="startTWSURL"/>
<c:url value="${url.base}${currentNode.path}.stopTWS.do" var="stopTWSURL"/>
	
<h1>${currentNode.properties.title.string}</h1>
<c:if test="${renderContext.editMode}">
	<p>You are going to track tweets with the following hashtag(s) : <b>${currentNode.properties.track.string}</b>
	<br>Tweets language : ${currentResource.locale.displayLanguage} 
	<br>Go to Preview Mode and click on 'Start' button to launch Tweet Wall.</p>
</c:if>
<c:if test="${renderContext.previewMode}">
	<p>You are going to track tweets with the following hashtag(s) : <b>${currentNode.properties.track.string}</b>
	<br>Tweets language : ${currentResource.locale.displayLanguage}
	<br> Click on 'Start'/'Stop' button to launch/stop Tweet Wall.</p>
	<a href="#" onclick="sendCmdServer('${startTWSURL}');"><button type="button" class="btn btn-default">Start</button></a>
	<a href="#" onclick="sendCmdServer('${stopTWSURL}');"><button type="button" class="btn btn-default">Stop</button></a>
	<br><br><div id="serverStatus"></div>
</c:if>
<c:if test="${renderContext.liveMode}">
	<p>Envoyez un tweet avec le(s) hashtag(s) suivant(s) <b>${currentNode.properties.track.string}</b></p>
	<tweetwall:display keywords="${currentNode.properties.track.string}" language="${currentResource.locale}"/>
	<div id="log"></div>
</c:if>