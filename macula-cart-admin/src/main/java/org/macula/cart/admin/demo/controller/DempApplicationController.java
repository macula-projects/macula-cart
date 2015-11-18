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
package org.macula.cart.admin.demo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.macula.cart.demo.service.DemoApplicationService;
import org.macula.cart.domain.DemoApplication;
import org.macula.cart.domain.DemoApplicationInstance;
import org.macula.core.exception.FormBindException;
import org.macula.core.mvc.annotation.FormBean;
import org.macula.core.mvc.annotation.OpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p> <b>ApplicationManagerController</b> 是应用实例维护. </p>
 * 
 * @since 2011-8-17
 * @author Wilson Luo
 * @version $Id: DempApplicationController.java 1734 2015-11-18 01:28:36Z wzp $
 */
@Controller
public class DempApplicationController extends DemoBaseController {
	@Autowired
	private DemoApplicationService demoApplicationService;

	@RequestMapping(value = "/application/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		return super.getRelativePath("/application/list");
	}

	@RequestMapping(value = "/application/apps", method = RequestMethod.GET)
	@OpenApi
	public Page<DemoApplication> getApplications() {
		return new PageImpl<DemoApplication>(demoApplicationService.getAllApplications());
	}

	@RequestMapping(value = "/application/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		model.addAttribute("id", id);
		return super.getRelativePath("/application/edit");
	}

	@RequestMapping(value = "/application/create", method = RequestMethod.GET)
	public String create() {
		return super.getRelativePath("/application/edit");
	}

	@RequestMapping(value = "/application/app/{id}", method = RequestMethod.GET)
	@OpenApi
	public DemoApplication getApplication(@PathVariable("id") DemoApplication application) {
		if (application == null) {
			application = new DemoApplication();
			application.setSingleSignOn(true);
			application.setAppInstances(new ArrayList<DemoApplicationInstance>());
		}
		return application;
	}

	@RequestMapping(value = "/application/save", method = RequestMethod.POST)
	@OpenApi
	public Long save(@FormBean("application") @Valid DemoApplication application) {
		if (hasErrors()) {
			throw new FormBindException(getMergedBindingResults());
		}
		return demoApplicationService.saveApplication(application);
	}

	@RequestMapping(value = "/application/delete/{id}", method = RequestMethod.POST)
	@OpenApi
	public Long delete(@PathVariable("id") DemoApplication application) {
		demoApplicationService.deleteApplication(application);
		return application.getId();
	}

}
