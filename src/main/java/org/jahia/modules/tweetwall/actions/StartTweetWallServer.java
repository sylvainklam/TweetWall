package org.jahia.modules.tweetwall.actions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.modules.tweetwall.websocket.TweetWallServer;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;

public class StartTweetWallServer extends Action {
	@Override
	public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session,
			Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
		String host = renderContext.getRequest().getParameter("host");
		String port = renderContext.getRequest().getParameter("port");
		TweetWallServer.startServer(host, port);
		renderContext.getResponse().getWriter().write(TweetWallServer.getURL(host, port));
		return null;
	}
}
