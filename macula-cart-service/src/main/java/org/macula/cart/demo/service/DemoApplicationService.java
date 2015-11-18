/**
 * Copyright 2010-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.macula.cart.demo.service;

import java.util.List;

import org.macula.cart.domain.DemoApplication;


/**
 * <p> <b>ApplicationManagerService</b> 是应用管理服务接口. </p>
 * 
 * @since 2011-8-17
 * @author Wilson Luo
 * @version $Id: DemoApplicationService.java 1734 2015-11-18 01:28:36Z wzp $
 */
public interface DemoApplicationService {

	/**
	 * 获取所有的应用列表
	 * 
	 * @return
	 */
	List<DemoApplication> getAllApplications();

	/**
	 * 按应用名称获取指定的Application
	 * 
	 * @param name
	 * @return
	 */
	DemoApplication findApplicationByAppId(String appId);

	/**
	 * 保存应用
	 * 
	 * @param application
	 * @return
	 */
	Long saveApplication(DemoApplication application);

	/**
	 * 删除应用
	 * 
	 * @param application
	 */
	void deleteApplication(DemoApplication application);

}
