/**
 * DubboMain.java 2016年11月11日
 */
package org.macula.cart.dubbo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.jar.Manifest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;
import org.joda.time.DateTime;
import org.macula.Configuration;
import org.macula.core.utils.StringUtils;
import org.macula.plugins.dubbo.MaculaContainer;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.ApplicationContext;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

public class DubboMain {

	static {
		String profile = Configuration.getProfile();
		if (StringUtils.isNotEmpty(profile)) {
			String profilePath = Configuration.getProfilePath();

			// FOR log4j see LogManager
			LogManager.resetConfiguration();
			PropertyConfigurator.configure(Loader.getResource(profilePath + "log4j.properties"));

			// FOR Spring Profile see AbstractEnvironment
			System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, profile);
			
			// FOR macula.properties
			// 在ProfilePropertyConfigurationProvider中加入环境变量路径设置，覆盖默认的macula.properties

			// FOR appconfig（统一业务系统配置），支持Http服务器、统一配置中心等
			
			// Spring的配置通过profile属性设置
		}
	}
	
	public static void main(String[] args) {
		try {
			long startTime = System.currentTimeMillis();

			// 加载父容器
			String locatorFactorySelector = "classpath:/configs/applicationContext-ref.xml";
		    String parentContextKey = "MaculaContextRoot";
		    
			BeanFactoryLocator locator = ContextSingletonBeanFactoryLocator.getInstance(locatorFactorySelector);
			
	        Log logger = LogFactory.getLog(MaculaContainer.class);
	        if (logger.isDebugEnabled()) {
	            logger.debug("Getting parent context definition: using parent context key of '" + parentContextKey + "' with BeanFactoryLocator");
	        }
	        
	        BeanFactoryReference parentContextRef = locator.useBeanFactory(parentContextKey);
	        ApplicationContext parentContext = (ApplicationContext) parentContextRef.getFactory();

			// 加载应用bean
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:/configs/applicationContext-app.xml", "classpath:/configs/applicationContext-core.xml" }, parentContext);
			
			org.macula.ApplicationContext.setContainer(context);
			
			// 设置版本
			// 设置程序版本
			String appVersion = Configuration.getProperty(Configuration.APP_VERSION_KEY);
			if (StringUtils.isEmpty(appVersion)) {
				// 从META-INF中获取版本号
				try {
					Manifest metainfo = new Manifest();
					metainfo.read(MaculaContainer.class.getResourceAsStream("/META-INF/MANIFEST.MF"));
					String manifestVersion = metainfo.getMainAttributes().getValue("Manifest-Version");
					if (StringUtils.isNotEmpty(manifestVersion)) {
						appVersion = manifestVersion;
					}
				} catch (NullPointerException e) {
					// ignore
				} catch (IOException e) {
					// ignore
				}

				// 规范化版本号
				if (StringUtils.isEmpty(appVersion) || "1.0".equals(appVersion)) {
					appVersion = new DateTime().toString("yyyyMMddHHmmss");
				}
				if (appVersion.indexOf("${maven.build.timestamp}") >= 0) {
					appVersion = appVersion.replace("${maven.build.timestamp}", new DateTime().toString("yyyyMMdd-HHmm"));
				}
				if (appVersion.indexOf("${environment}") >= 0) {
					appVersion = appVersion.replace("${environment}", "dev");
				}
				Configuration.putProperty(Configuration.APP_VERSION_KEY, appVersion);
			}
			
			// 设置实例名称
			String appInstance = System.getProperty(Configuration.APP_INSTANCE_KEY);
			if (StringUtils.isEmpty(appInstance)) {
				appInstance = "app01";
				Configuration.putProperty(Configuration.APP_INSTANCE_KEY, appInstance);
			}
			
			if (logger.isInfoEnabled()) {
				logger.info("Application deploy version: " + appVersion + ", runMode: " + Configuration.getRunMode() + ", Profile: "
						+ Configuration.getProfile());
			}
			
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
