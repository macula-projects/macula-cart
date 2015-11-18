<#--

    Copyright 2010-2012 the original author or authors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

            http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

-->
<#-- $Id: edit_mower.ftl 5883 2015-09-29 00:57:00Z eric.ou $ -->
<#assign title>
	<#if id?exists>编辑应用<#else>新增应用</#if>
</#assign>

<@layout.mower_admin title=title scripts="admin/demo/application/edit.js" version="[$Revision: 4511 $]" require="knockoutjs">
	<#assign code="edit-application" />
		<@ui.panel>
			<@ui.panel_head>
				<div class="col-xs-12 col-md-12">
				    <a id="save-action-${code}" class="btn btn-primary">
				        <i class="fa fa-check-circle fa-lg"></i>
				        保存
				    </a>
				    <a id="cancel-action-${code}" class="btn btn-default" data-toggle="popBreadcrumb">
				        <i class="fa fa-reply fa-lg"></i>
				        取消
				    </a>
				</div>
			</@ui.panel_head>
			<@ui.panel_body>
		        <form id="form-${code}" item-id="${id?if_exists}" action="${base}/admin/demo/application/save" method="post" class="form-horizontal" rel="validate-form" data-bv-container="tooltip">
		        	<input type="hidden" name="application.id" data-bind="value: id" />
		            <div class="form-body">
		            	<h3 class="form-section">应用信息</h3>
		                <div class="row">
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label class="control-label col-md-3">应用编号：</label>
	                                <div class="col-md-9">
	                                    <input type="text"  name="application.appId" data-bind="value: appId" class="form-control input-sm" required maxlength="50" <#if id?exists>readOnly</#if> />
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label class="control-label col-md-3">应用名称：</label>
	                                <div class="col-md-9">
	                                    <input type="text"  name="application.name" data-bind="value: name"  class="form-control input-sm" required maxlength="50"/>
	                                </div>
	                            </div>
	                        </div>
		                </div>
		                <div class="row">
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label class="control-label col-md-3">应用分组：</label>
	                                <div class="col-md-9">
	                                    <input type="text"  name="application.appGroup" data-bind="value: appGroup" class="form-control input-sm" required maxlength="50"/>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label class="control-label col-md-3">应用风格：</label>
	                                <div class="col-md-9">
	                                    <input type="text"  name="application.theme" data-bind="value: theme"  class="form-control input-sm" required maxlength="50" />
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label class="control-label col-md-3">应用密钥：</label>
	                                <div class="col-md-9">
										<div class="input-group input-group-sm">
											<span class="input-group-btn">
												<button id="gen-key-action" class="btn btn-default" type="button">Go!</button>
											</span>		                                
											<input type="text"  name="application.secureKey" data-bind="value: secureKey" class="form-control input-sm" required maxlength="1024"/>
										</div>						
									</div>
	                            </div>
	                        </div>
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label class="control-label col-md-3">应用私钥：</label>
	                                <div class="col-md-9">
	                                    <input type="text"  name="application.privateKey" data-bind="value: privateKey"  class="form-control input-sm" required  maxlength="1024" />
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label class="control-label col-md-3">应用入口：</label>
	                                <div class="col-md-9">
	                                    <input type="text"  name="application.homepage" data-bind="value: homepage" class="form-control input-sm" required  maxlength="1024"/>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label class="control-label col-md-3">负责人：</label>
	                                <div class="col-md-9">
	                                    <input type="text"  name="application.supervisor" data-bind="value: supervisor" class="form-control input-sm" required  maxlength="255"/>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label class="control-label col-md-3">联系方式：</label>
	                                <div class="col-md-9">
	                                    <input type="text"  name="application.contact" data-bind="value: contact"  class="form-control input-sm" required maxlength="255" />
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label class="control-label col-md-3"></label>
	                                <div class="col-md-9">
	                        			<label class="checkbox-inline">
			                        	    <input type="hidden" name="application.singleSignOn" data-bind="value: singleSignOn, type: 'boolean' " />
			                        	    <input type="checkbox" data-bind="checked: singleSignOn" />
											支持单点登录
		                        	    </label>
	                        			<label class="checkbox-inline">
			                        	    <input type="hidden" name="application.singleSignOut" data-bind="value: singleSignOut, type: 'boolean' " />
			                        	    <input type="checkbox" data-bind="checked: singleSignOut" />
											支持单点登出
		                        	    </label>
	                        			<label class="checkbox-inline">
			                        	    <input type="hidden" name="application.useAttributes" data-bind="value: useAttributes, type: 'boolean' " />
			                        	    <input type="checkbox" data-bind="checked: useAttributes" />
											回传属性
		                        	    </label>
	                        		</div>
	                        	</div>
	                        </div>
	                    </div>
	                    <div class="row">
		                    <div class="col-md-6">
		                        <div class="form-group">
		                        	<label class="control-label col-md-3">属性列表：</label>
		                            <div class="col-md-9">
		                            	<input type="text"  name="application.allowedAttributes" data-bind="value: allowedAttributes" class="form-control input-sm" maxlength="1024"/>
		                            </div>
		                        </div>
		                    </div>
							<div class="col-md-6">
		                        <div class="form-group">
		                            <label class="control-label col-md-3">备注：</label>
		                            <div class="col-md-9">
		                                <textarea type="text"  name="application.comments" data-bind="value: comments" rows="2"  class="form-control input-sm">
										</textarea>
		                            </div>
		                        </div>
		                    </div>
						</div>
						<h3 class="form-section">应用实例信息列表</h3>
		                <table id="appInstances" class="table table-bordered table-striped table-responsive table-condensed">
		                    <thead>
								<tr>
									<th style="width:15%">编码</th>
									<th style="width:20%">名称</th>
									<th style="width:55%">内部地址</th>
									<th style="width:10%">&nbsp;</th>
								</tr>
		                    </thead>
							<tbody data-bind="foreach: {data : appInstances, as : 'detail'}">
								<tr data-bind="visible: !detail.deleted()">
										<input type="hidden"  data-bind="attr : {value: detail.id, name : 'application.appInstances['+$index()+'].id'}" />
										<input type="hidden" data-bind="attr : {value: (detail.deleted()?1:0), name : 'application.appInstances['+$index()+'].deleted'}" />
									<td>
										<input type="text" data-bv-group="td"  required data-bind="attr : {value: detail.name, name : 'application.appInstances['+$index()+'].name'}" class="form-control input-sm" style="width:100%"/>
									</td>
									<td>
										<input type="text" data-bv-group="td" required data-bind="attr : {value: detail.code, name : 'application.appInstances['+$index()+'].code'}" class="form-control input-sm" style="width:100%"/>
									</td>
									<td>
										<input type="text" data-bv-group="td"  required data-bind="attr : {value: detail.intranetHomepage, name : 'application.appInstances['+$index()+'].intranetHomepage'}" class="form-control input-sm" style="width:100%"/>
									</td>
									<td><button class="btn btn-danger" style="padding: 4px 10px;" data-bind="click: $parent.onDeleteApplicationInstance, clickBubble: false" class="form-control input-sm">删除</button></td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="4">
										<button class="btn btn-primary" style="padding: 2px 10px;" data-bind="click: onAddApplicationInstance">添加实例</button>
									</td>
								</tr>
							</tfoot>
		                </table>
		            </div>
		        </form>
			</@ui.panel_body>
	</@ui.panel>
</@layout.mower_admin>
