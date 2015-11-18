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
package org.macula.cart.demo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.macula.cart.demo.service.DemoApplicationService;
import org.macula.cart.domain.DemoApplication;
import org.macula.cart.repository.DemoApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p> <b>ApplicationManagerServiceImpl</b> 是ApplicationManagerService实现. </p>
 * 
 * @since 2011-8-17
 * @author Wilson Luo
 * @version $Id: DemoApplicationServiceImpl.java 1734 2015-11-18 01:28:36Z wzp $
 */
@Service
public class DemoApplicationServiceImpl implements DemoApplicationService {

	@Autowired
	private DemoApplicationRepository demoApplicationRepository;

	@Override
	public List<DemoApplication> getAllApplications() {
		List<DemoApplication> result = new ArrayList<DemoApplication>(demoApplicationRepository.findAll());
		Collections.sort(result, new Comparator<DemoApplication>() {

			@Override
			public int compare(DemoApplication o1, DemoApplication o2) {
				int result = o1.getAppGroup().compareTo(o2.getAppGroup());
				return result != 0 ? result : o1.getAppId().compareToIgnoreCase(o2.getAppId());
			}
		});
		return result;
	}

	@Override
	public DemoApplication findApplicationByAppId(String appId) {
		return demoApplicationRepository.findByAppId(appId);
	}

	@Override
	@Transactional
	public Long saveApplication(DemoApplication application) {
		application.updateApplicationInstances();
		Long id = demoApplicationRepository.save(application).getId();
		return id;
	}

	@Override
	@Transactional
	public void deleteApplication(DemoApplication application) {
		demoApplicationRepository.delete(application);
	}

}
