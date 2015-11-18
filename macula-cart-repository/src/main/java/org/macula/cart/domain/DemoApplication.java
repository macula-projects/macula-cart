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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.macula.core.domain.AbstractAuditable;
import org.macula.core.hibernate.audit.Auditable;
import org.macula.core.validation.Length2;

/**
 * <p>
 * <b>JpaApplication</b> 是Application的JPA实现类.
 * </p>
 * 
 * @since 2010-12-27
 * @author Wilson Luo
 * @version $Id: DemoApplication.java 1734 2015-11-18 01:28:36Z wzp $
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "DEMO_APPLICATION")
@Auditable
public class DemoApplication extends AbstractAuditable<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "APP_ID", nullable = false, unique = true, length = 50)
	@NotNull
	@Length2(min = 1, max = 50)
	private String appId;
	@Column(name = "APP_NAME", nullable = false, length = 50)
	@NotNull
	@Length2(min = 1, max = 50)
	private String name;
	@Column(name = "APP_GROUP", nullable = false, length = 50)
	@NotNull
	@Length2(min = 1, max = 50)
	private String appGroup;
	@Column(name = "SECURE_KEY", nullable = false, length = 1024)
	@NotNull
	@Length2(min = 1, max = 1024)
	private String secureKey;
	@Column(name = "PRIVATE_KEY", nullable = false, length = 1024)
	@NotNull
	@Length2(min = 1, max = 1024)
	private String privateKey;
	@Column(name = "HOME_PAGE", nullable = false, length = 255)
	@NotNull
	@Length2(min = 1, max = 255)
	private String homepage;

	@Column(name = "SUPERVISOR", length = 255)
	@Length2(min = 0, max = 255)
	private String supervisor;

	@Column(name = "CONTACT", length = 255)
	@Length2(min = 0, max = 255)
	private String contact;

	@Column(name = "COMMENTS", length = 255)
	@Length2(min = 0, max = 255)
	private String comments;

	@Column(name = "THEME", length = 50)
	@Length2(min = 0, max = 50)
	private String theme;

	@Column(name = "IS_SSIN", nullable = false)
	private boolean singleSignOn;
	@Column(name = "IS_SSOUT", nullable = false)
	private boolean singleSignOut;

	@Column(name = "USE_ATTRS", nullable = false)
	private boolean useAttributes;

	@Column(name = "ALLOWED_ATTRS")
	private String allowedAttributes;

	@OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = DemoApplicationInstance.class, fetch = FetchType.EAGER)
	private List<DemoApplicationInstance> appInstances;

	public static DemoApplication createApplication(String appId) {
		if (appId == null) {
			return null;
		}
		DemoApplication tmpApp = new DemoApplication();
		tmpApp.setAppId(appId);
		tmpApp.setId(0L);
		return tmpApp;
	}

	public static DemoApplication createApplication(Long id) {
		if (id == null) {
			return null;
		}
		DemoApplication tmpApp = new DemoApplication();
		tmpApp.setId(id);
		return tmpApp;
	}

	public String getAppId() {
		return this.appId;
	}

	public String getSecureKey() {
		return secureKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public String getHomepage() {
		return homepage;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public String getContact() {
		return contact;
	}

	public String getComments() {
		return comments;
	}

	/**
	 * @param appId the id to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @param secureKey the secureKey to set
	 */
	public void setSecureKey(String secureKey) {
		this.secureKey = secureKey;
	}

	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * @param homepage the homepage to set
	 */
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	/**
	 * @param supervisor the supervisor to set
	 */
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @param comment the comments to set
	 */
	public void setComments(String comment) {
		this.comments = comment;
	}

	public boolean isSingleSignOn() {
		return singleSignOn;
	}

	public boolean isSingleSignOut() {
		return singleSignOut;
	}

	/**
	 * @param singleSignOn the singleSignOn to set
	 */
	public void setSingleSignOn(boolean singleSignOn) {
		this.singleSignOn = singleSignOn;
	}

	/**
	 * @param singleSignOut the singleSignOut to set
	 */
	public void setSingleSignOut(boolean singleSignOut) {
		this.singleSignOut = singleSignOut;
	}

	/**
	 * @return the appInstances
	 */
	public List<DemoApplicationInstance> getAppInstances() {
		return appInstances;
	}

	/**
	 * @param appInstances the appInstances to set
	 */
	public void setAppInstances(List<DemoApplicationInstance> appInstances) {
		this.appInstances = appInstances;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void updateApplicationInstances() {
		if (appInstances != null) {
			List<DemoApplicationInstance> removed = new ArrayList<DemoApplicationInstance>();
			for (DemoApplicationInstance instance : appInstances) {
				if (instance.isDeleted()) {
					removed.add(instance);
				} else if (instance.getApplication() == null) {
					instance.setApplication(this);
				}
			}
			for (DemoApplicationInstance instance : removed) {
				instance.setApplication(null);
				appInstances.remove(instance);
			}
		}
	}

	public String getTheme() {
		if (this.theme == null) {
			this.theme = this.appId;
		}
		return this.theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * @return the appGroup
	 */
	public String getAppGroup() {
		if (appGroup == null) {
			appGroup = this.appId;
		}
		return appGroup;
	}

	/**
	 * @param appGroup the appGroup to set
	 */
	public void setAppGroup(String appGroup) {
		this.appGroup = appGroup;
	}

	/**
	 * @return the useAttributes
	 */
	public boolean isUseAttributes() {
		return useAttributes;
	}

	/**
	 * @param ignoreAttributes the useAttributes to set
	 */
	public void setUseAttributes(boolean ignoreAttributes) {
		this.useAttributes = ignoreAttributes;
	}

	/**
	 * @return the allowedAttributes
	 */
	public String getAllowedAttributes() {
		return allowedAttributes;
	}

	/**
	 * @param allowedAttributes the allowedAttributes to set
	 */
	public void setAllowedAttributes(String allowedAttributes) {
		this.allowedAttributes = allowedAttributes;
	}

}
