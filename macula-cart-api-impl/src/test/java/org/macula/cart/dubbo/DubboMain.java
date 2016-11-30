/**
 * DubboMain.java 2016年11月11日
 */
package org.macula.cart.dubbo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.macula.ApplicationContext;
import org.macula.Configuration;
import org.macula.core.utils.StringUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

public class DubboMain {

	static {
		String profile = Configuration.getProfile();
		if (StringUtils.isNotEmpty(profile)) {
			String profilePath = Configuration.getProfilePath();

			// FOR log4j see LogManager
			System.setProperty("log4j.configuration", profilePath + "log4j.properties");

			// FOR Spring Profile see AbstractEnvironment
			System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, profile);
			
			// FOR macula.properties
			// 在PropertyConfigurationProvider中加入环境变量路径设置

			// FOR FreeMarker
			// 在 applicationContext-macula.xml中加入环境路径相关配置
			// FOR JDBC
			// 在 applicationContext-root.xml中加入环境路径相关配置，数据源具体配置会放在drui-xxx.properties中
			// FOR Redis
			// 在 applicationContext-root.xml中使用beans的profile配置
			// 上述配置在XML中指定
		}
	}
	
	public static void main(String[] args) {
		try {
			long startTime = System.currentTimeMillis();

			ClassPathXmlApplicationContext parentContext = new ClassPathXmlApplicationContext("classpath:applicationContext-root.xml");
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "classpath:/configs/applicationContext-app.xml", "classpath:/configs/applicationContext-core.xml" },
					parentContext);

			ApplicationContext.setContainer(context);

			context.start();
			long endTime = System.currentTimeMillis();
			System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " Dubbo service server started in "
					+ ((endTime - startTime) / 1000) + "s");
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized (DubboMain.class) {
			while (true) {
				try {
					DubboMain.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
