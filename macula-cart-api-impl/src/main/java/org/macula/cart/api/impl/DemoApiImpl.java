/**
 * DemoServiceImpl.java 2016年4月20日
 */
package org.macula.cart.api.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.macula.cart.api.DemoApi;
import org.macula.cart.domain.DemoApplication;
import org.macula.cart.repository.DemoApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.RpcContext;

/**
 * <p>
 * <b>DemoApiImpl</b> DEMO SERVICE
 * </p>
 *
 * @since 2016年4月20日
 * @author Rain
 * @version $Id: DemoApiImpl.java 6048 2016-04-22 02:27:52Z wzp $
 */

@Service("demoApiImpl")
public class DemoApiImpl implements DemoApi {
	
	@Autowired
	private DemoApplicationRepository demoApplicationRepository;

	@Override
	public String sayHello(String name) {
//		DemoApplication n = new DemoApplication();
//		n.setAppId("HELLO");
//		n.setAppGroup("HELLO");
//		n.setPrivateKey("HELLP");
//		n.setSecureKey("HELLO");
//		n.setHomepage("http://");
//		n.setSupervisor("HELLO");
//		n.setContact("1234");
//		n.setName("HELLO MACULA");
//		demoApplicationRepository.saveAndFlush(n);
		
		DemoApplication a = demoApplicationRepository.findByAppId(name);
		
		
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + a.getName() + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "Hello " + a.getName() + ", response form provider: " + RpcContext.getContext().getLocalAddress();
    }
}
