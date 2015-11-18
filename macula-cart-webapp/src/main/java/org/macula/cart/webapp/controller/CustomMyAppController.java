/**
 * CustomMyAppController.java 2015年11月16日
 */
package org.macula.cart.webapp.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.macula.base.acl.domain.Resource;
import org.macula.base.controller.CustomAppController;
import org.macula.base.security.principal.UserPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * <p>
 * <b>CustomMyAppController</b> 定义AppController的默认实现，包括登陆、菜单、首页的处理
 * </p>
 *
 * @since 2015年11月16日
 * @author Rain
 * @version $Id: CustomMyAppController.java 5954 2015-11-16 09:28:31Z wzp $
 */
@Controller
public class CustomMyAppController implements CustomAppController {

	@Override
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		return null;
	}

	@Override
	public String loginFailed(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		return null;
	}

	@Override
	public Collection<Resource> getMenuTree(HttpServletRequest request, HttpServletResponse response, Model model, String path) {
		return null;
	}

	@Override
	public Collection<Resource> getMenuTree(HttpServletRequest request, HttpServletResponse response, Model model, String path, Long root,
			int level) {
		return null;
	}

	@Override
	public String dashboard(HttpServletRequest request, HttpServletResponse response, Model model, String path) {
		return null;
	}

	@Override
	public String getUserType(UserPrincipal user) {
		return null;
	}

	@Override
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		return null;
	}

}
