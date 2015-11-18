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
package org.macula.cart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.macula.core.domain.AbstractAuditable;
import org.macula.core.hibernate.audit.Auditable;
import org.macula.core.validation.Length2;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <p>
 * <b>JpaApplicationInstance</b> 是应用实例的JPA实现。
 * </p>
 * 
 * @since 2011-2-23
 * @author Wilson Luo
 * @version $Id: DemoApplicationInstance.java 1734 2015-11-18 01:28:36Z wzp $
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "DEMO_APP_INSTANCE")
@Auditable
public class DemoApplicationInstance extends AbstractAuditable<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "CODE", nullable = false, length = 50)
	@NotNull
	@Length2(min = 1, max = 50)
	private String code;
	
	@Column(name = "NAME", nullable = false, length = 50)
	@NotNull
	@Length2(min = 1, max = 50)
	private String name;

	@ManyToOne(targetEntity = DemoApplication.class, optional = false)
	@JoinColumn(name = "APP_ID", referencedColumnName = "APP_ID")
	@NotNull
	private DemoApplication application;

	@Column(name = "HOME_PAGE", nullable = false, length = 255)
	@NotNull
	@Length2(min = 1, max = 255)
	private String intranetHomepage;

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public DemoApplication getApplication() {
		return application;
	}

	public String getIntranetHomepage() {
		return intranetHomepage;
	}

	/**
	 * @param application the application to set
	 */
	public void setApplication(DemoApplication application) {
		this.application = application;
	}

	/**
	 * @param intranetHomepage the intranetHomepage to set
	 */
	public void setIntranetHomepage(String intranetHomepage) {
		this.intranetHomepage = intranetHomepage;
	}

}
