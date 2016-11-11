/**
 * DubboMain.java 2016年11月11日
 */
package org.macula.cart.dubbo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.macula.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboMain {

	private static final Log log = LogFactory.getLog(DubboMain.class);

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
			log.error("== DubboProvider context start error:", e);
		}
		synchronized (DubboMain.class) {
			while (true) {
				try {
					DubboMain.class.wait();
				} catch (InterruptedException e) {
					log.error("== synchronized error:", e);
				}
			}
		}
	}

}
