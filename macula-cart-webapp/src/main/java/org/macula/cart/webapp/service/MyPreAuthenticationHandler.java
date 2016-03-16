/**
 * MyPreAuthenticationHandler.java 2016年3月16日
 */
package org.macula.cart.webapp.service;

import javax.servlet.http.HttpServletRequest;

import org.macula.base.security.principal.impl.UserPrincipalImpl;
import org.macula.base.security.web.authentication.preauth.PreAuthenticationHandler;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <b>MyPreAuthenticationHandler</b> 处理通过外部系统已经登录的用户自动登录本系统
 * </p>
 *
 * @since 2016年3月16日
 * @author Rain
 * @version $Id$
 */

@Component
public class MyPreAuthenticationHandler implements PreAuthenticationHandler {

	/* (non-Javadoc)
	 * @see org.macula.base.security.web.authentication.preauth.PreAuthenticationHandler#getPreAuthenticatedPrincipal(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Object getPreAuthenticatedPrincipal(HttpServletRequest request) {

		String username = request.getParameter("username");
		if (username != null) {
			return new UserPrincipalImpl(username);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.macula.base.security.web.authentication.preauth.PreAuthenticationHandler#getPreAuthenticatedCredentials(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return "N/A";
	}
}
