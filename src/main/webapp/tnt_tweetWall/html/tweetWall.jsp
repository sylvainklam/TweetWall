<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="tweetwall" uri="http://www.tweetwall.org/jahia" %>

<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>

<template:addResources type="javascript" resources="tweetwall.js"/>

<c:url value="${url.base}${currentNode.path}.startTWS.do" var="startTWSURL"/>
<c:url value="${url.base}${currentNode.path}.stopTWS.do" var="stopTWSURL"/>
	
<c:if test="${renderContext.editMode}">
	<img src="${currentNode.properties.logo.node.url}" />
	<p><fmt:message key="org.jahia.modules.tweetwall.track.intro"/> : <b>${currentNode.properties.track.string}</b>
	<br><br><fmt:message key="org.jahia.modules.tweetwall.language"/> : <b>${currentResource.locale.displayLanguage}</b> 
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
	<div id="tweetwall">
		<table>
		<tr>
			<td><img src="${currentNode.properties.logo.node.url}" /></td>
			<td><h1><a href="#" onclick="goFullscreen('tweetwall')">${currentNode.properties.title.string}</a></h1></td>
		</tr>
		</table>
		<h2><fmt:message key="org.jahia.modules.tweetwall.live.howto"/> : <b>${currentNode.properties.track.string}</b></h2>
		<tweetwall:display keywords="${currentNode.properties.track.string}" language="${currentResource.locale}"/>
		<div id="log" style="overflow:auto;word-wrap:break-word;"></div>
	</div>	
</c:if>