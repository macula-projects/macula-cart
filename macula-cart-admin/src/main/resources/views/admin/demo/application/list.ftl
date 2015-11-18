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
<#-- $Id: list_mower.ftl 5812 2015-09-11 01:35:16Z arron.lin $ -->
<@layout.mower_admin title="应用列表" scripts="admin/demo/application/list.js" version="[$Revision: 4511 $]" require="knockoutjs">
	<#assign code="application-list" />
	<@ui.panel>
		<@ui.panel_head>
			<div class="col-xs-12 col-md-12">
				<a id="add-action-${code}" class="btn btn-default" data-toggle="pushBreadcrumb" data-label="新增" data-page="admin/demo/application/create">
				    <i class="fa fa-plus-circle fa-lg"></i>
				    新增
				</a>
				<a id="edit-action-${code}" class="btn btn-default" data-label="编辑">
				    <i class="fa fa-pencil fa-lg"></i>
				    编辑
				</a>
				<a id="delete-action-${code}" class="btn btn-danger">
				    <i class="fa fa-trash-o fa-lg"></i>
				    删除
				</a>
			</div>
		</@ui.panel_head>
		<@ui.panel_body>
			<table id="list-${code}" class="table table-striped table-bordered table-hover" width="100%"
			 data-serverSide="true" 
			 data-paging="false" 
			 data-ordering="false" 
			 data-ajax-url="${base}/admin/demo/application/apps"
			 data-ajax-type="get"
			 data-select="true"
			 data-row-id="id"
			 rel="datatables">
			    <thead>
			        <tr>
			            <th data-name="appGroup">应用分组</th>
			            <th data-name="appId">应用编号</th>
			            <th data-name="name">应用名称</th>
			            <th data-name="supervisor">负责人</th>
			            <th data-name="contact">联系方式</th>
			            <th data-name="singleSignOn">支持单点登陆</th>
			            <th data-name="singleSignOut">支持单点登出</th>
			        </tr>
			    </thead>
			</table>
		</@ui.panel_body>
	</@ui.panel>
</@layout.mower_admin>