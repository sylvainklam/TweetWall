<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="tweetwall" uri="http://www.tweetwall.org/jahia" %>

<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>

<template:addResources type="javascript" resources="tweetwall.js"/>
<template:addResources type="css" resources="tweetwall.css"/>

<c:url value="${url.base}${currentNode.path}.startTWS.do?host=${currentNode.properties.wshost.string}&port=${currentNode.properties.wsport.string}" var="startTWSURL"/>
<c:url value="${url.base}${currentNode.path}.stopTWS.do?host=${currentNode.properties.wshost.string}&port=${currentNode.properties.wsport.string}" var="stopTWSURL"/>
	
<c:if test="${renderContext.editMode}">
	<img src="${currentNode.properties.logo.node.url}" />
	<p><fmt:message key="org.jahia.modules.tweetwall.track.intro"/> : <b>${currentNode.properties.track.string}</b>
	<br><br><fmt:message key="org.jahia.modules.tweetwall.language"/> : <b>${currentResource.locale.displayLanguage}</b>
	<br><br><fmt:message key="org.jahia.modules.tweetwall.websocket.server.name"/> : <b>${currentNode.properties.wshost.string}</b>, 
	<fmt:message key="org.jahia.modules.tweetwall.websocket.server.port"/> : <b>${currentNode.properties.wsport.long}</b> 
	<br><br><fmt:message key="org.jahia.modules.tweetwall.edit.howtolaunch"/></p>
</c:if>
<c:if test="${renderContext.previewMode}">
	<img src="${currentNode.properties.logo.node.url}" />
	<p><fmt:message key="org.jahia.modules.tweetwall.track.intro"/> : <b>${currentNode.properties.track.string}</b>
	<br><br><fmt:message key="org.jahia.modules.tweetwall.language"/> : <b>${currentResource.locale.displayLanguage}</b>
	<br><br><fmt:message key="org.jahia.modules.tweetwall.preview.howtolaunch"/></p>
	<a href="#" onclick="sendCmdServer('${startTWSURL}','<fmt:message key="org.jahia.modules.tweetwall.websocket.server.started"/>');">
	<button type="button" class="btn btn-default"><fmt:message key="org.jahia.modules.tweetwall.preview.button.start"/></button></a>
	<a href="#" onclick="sendCmdServer('${stopTWSURL}','<fmt:message key="org.jahia.modules.tweetwall.websocket.server.stopped"/>');">
	<button type="button" class="btn btn-default"><fmt:message key="org.jahia.modules.tweetwall.preview.button.stop"/></button></a>
	<br><br><div id="serverStatus"></div>
</c:if>
<c:if test="${renderContext.liveMode}">
	<tweetwall:display keywords="${currentNode.properties.track.string}" language="${currentResource.locale}" 
			wshost="${currentNode.properties.wshost.string}" wsport="${currentNode.properties.wsport.long}"
			debugEnabled="${currentNode.properties.debug.boolean}" OAuthConsumerKey="${currentNode.properties.OAuthConsumerKey.string}" 
			OAuthConsumerSecret="${currentNode.properties.OAuthConsumerSecret.string}" OAuthAccessToken="${currentNode.properties.OAuthAccessToken.string}" 
			OAuthAccessTokenSecret="${currentNode.properties.OAuthAccessTokenSecret.string}"/>
	<div id="tweetwall" class="tweetwall">
		<table>
		<tr>
			<td><img src="${currentNode.properties.logo.node.url}" /></td>
			<td><h1><a href="#" onclick="goFullscreen('tweetwall')">${currentNode.properties.title.string}</a></h1></td>
		</tr>
		<tr>
			<td colspan="2"><h2><fmt:message key="org.jahia.modules.tweetwall.live.howto"/> : <b>${currentNode.properties.track.string}</b></h2></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><div id="log" align="center"></div></td>
		</tr>
		</table>
	</div>	
</c:if>