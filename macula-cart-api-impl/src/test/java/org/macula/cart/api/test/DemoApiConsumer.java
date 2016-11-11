/**
 * DemoApiConsumer.java 2016年11月11日
 */
package org.macula.cart.api.test;

import org.macula.cart.api.DemoApi;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * <b>DemoApiConsumer</b> is
 * </p>
 *
 * @since 2016年11月11日
 * @author Rain
 * @version $Id$
 */
public class DemoApiConsumer {
    private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) throws Exception {
        context = new ClassPathXmlApplicationContext(new String[] {"classpath:/applicationContext.xml"});
        
        context.start();
        DemoApi demoApi = (DemoApi)context.getBean("demoApi"); // 获取远程服务代理
        String hello = demoApi.sayHello("HELLO"); // 执行远程方法
 
        System.out.println( hello ); // 显示调用结果
    }
	
	public static ClassPathXmlApplicationContext getContext() {
		return context;
	}
}
