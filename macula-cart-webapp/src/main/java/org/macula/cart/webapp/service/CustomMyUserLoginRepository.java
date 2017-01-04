/**
 * CustomMyUserLoginRepository.java 2015年11月16日
 */
package org.macula.cart.webapp.service;

import org.macula.Configuration;
import org.macula.MaculaConstants;
import org.macula.base.security.principal.CustomUserLoginRepository;
import org.macula.base.security.principal.UserPrincipal;
import org.macula.base.security.principal.impl.UserPrincipalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <b>CustomMyUserLoginRepository</b> 框架与应用登陆对接，主要处理用户的来源
 * </p>
 *
 * @since 2015年11月16日
 * @author Rain
 * @version $Id: CustomMyUserLoginRepository.java 1734 2015-11-18 01:28:36Z wzp $
 */
@Component
public class CustomMyUserLoginRepository implements CustomUserLoginRepository {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO 以下代码需要替换
		UserPrincipalImpl user = null;
		// 在开发环境下，如果没有实现用户Respository，则提供一个超级密码
		if (MaculaConstants.RUN_MODE_DEV.equals(Configuration.getRunMode())) {
			user = new UserPrincipalImpl(username, passwordEncoder.encode("infi123*"));
		}
		return user;
		
// 参考下面代码完成
//		JpaUIMUser user = uimUserRepository.findByUserName(username);
//		if (user != null) {
//			UserPrincipalImpl principal = new UserPrincipalImpl(user.getUserName());
//			principal.setPassword(user.getPassword());
//			return principal;
//		}
//		throw new UsernameNotFoundException("AbstractUserDetailsAuthenticationProvider.badCredentials");
	}

	@Override
	public void postValidateUserPrincipal(UserPrincipal principal) throws AuthenticationException {
		// TODO 可以校验凭据的合法性，非法则抛出异常
	}

}
