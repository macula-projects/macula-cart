// $Id: edit.js 1728 2015-11-17 08:42:51Z wzp $
var ApplicationForm = (function($) {
	var code = 'edit-application', parentCode = 'application-list', $form = $('#form-' + code);

	var appInstanceDomain = {
		id : '',
		code : '',
		name : '',
		intranetHomepage : '',
		deleted : false
	};

	var viewModel = function(data) {
		var self = this;

		ko.mapping.fromJS(data, {}, self);

		self.onAddApplicationInstance = function() {
			self.appInstances.push(ko.mapping.fromJS(appInstanceDomain));
			$form.trigger('updateValidate');
			return false;
		};

		self.onDeleteApplicationInstance = function(detail) {
			detail.id() ? detail.deleted(true) : self.appInstances.remove(detail);
			return false;
		};
	};

	return {
		init : function() {
			// 初始化界面数据
			var currentId = $form.attr('item-id');
			if (!currentId) {
				// -1 means server return add object struct
				currentId = -1;
			}
			$.getJSON(base + '/admin/demo/application/app/' + currentId, function(data) {
				ko.applyBindings(new viewModel(data.returnObject), $form[0]);
				$form.trigger('updateValidate');
			});
			
			// 绑定按钮事件
			$('#save-action-' + code).click(function(e) {
				var that = $(this);
				$form.ajaxValidSubmit({
					success : function(data) {
						MessageBox.success('保存成功');
						that.trigger('pop.mu.breadcrumb');
						$('#list-' + parentCode).DataTable().ajax.reload();
					},
					error : function() {
					}
				});
			});
			
			$('#gen-key-action').click(function(e) {
				// TODO 产生公钥和私钥
			});
		}
	};
}(jQuery));

$(function() {
	ApplicationForm.init();
});
