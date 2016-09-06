/**
 * @author renhengli 2016-04-27
 * @version 1.0
 */
(function() {
	var Win = {
			/**
			 * success的提示
			 * @param content
			 * @param time
			 */
			alert: function (content,time){
				var d = dialog({

					title: '提示',

					content: content,
					//quickClose: true,

					okValue: '确定',

					ok: function(){}

				});
				$(d.node).find(".ui-dialog").addClass('success');
				// d.show(document.getElementById('quickref-bubble'));
				d.showModal();
				if(time && time != ""){
					setTimeout(function() {
					   d.close();
					},time);
				}
			},
			/**
			 * 打断提示框
			 * @param content
			 * @param ok
			 * @param time
			 */
			alertbreak: function(content, ok, time) {
				if(typeof(ok) != 'function'){
					this.error('参数类型错误');
					return;
				}
				/* 带确定取消功能 */
				var d = dialog({

						title: '提示',

						content: content,
						//quickClose: true,

						okValue: '确定',

						ok: ok

					});
					$(d.node).find(".ui-dialog").addClass('success');
					//d.show(document.getElementById('quickref-bubble'));
					d.showModal();
					if(time && time != ""){
						setTimeout(function() {
						   d.close();
						},time);
					}
				
				
			},
			/**
			 * 提示性警告
			 * @param content
			 * @param time
			 */
			warning: function (content,time){
				var d = dialog({

					title: '提示',

					content: content,
					//quickClose: true,

					okValue: '确定',

					ok: function(){}

				});
				$(d.node).find(".ui-dialog").addClass('delete');
				// d.show(document.getElementById('quickref-bubble'));
				d.showModal();
				if(time && time != ""){
					setTimeout(function() {
					   d.close();
					},time);
				}
			},
			/**
			 * 错误性警告
			 * @param content
			 * @param time
			 */
			error: function(content, time) {
				var d = dialog({

					title: '提示',

					content: content,
					//quickClose: true,

					okValue: '确定',

					ok: function(){}

				});
				$(d.node).find(".ui-dialog").addClass('error');
				// d.show(document.getElementById('quickref-bubble'));
				d.showModal();
				if(time && time != ""){
					setTimeout(function() {
					   d.close();
					},time);
				}
			},
			/**
			 * 带有确定和取消的提示性警告
			 * @param content
			 * @param ok
			 * @param cancel
			 * @param time
			 */
			confirm: function(content, ok, cancel,time) {
				if(typeof(ok) != 'function'){
					this.error('参数类型错误');
					return;
				}
				if(typeof(cancel) != 'function'){
					this.error('参数类型错误');
					return;
				}
				/* 带确定取消功能 */
				var d = dialog({

						title: '提示',

						content: content,
						//quickClose: true,

						okValue: '确定',

						ok: ok,
						cancelValue:'取消',
						cancel:cancel

					});
					$(d.node).find(".ui-dialog").addClass('delete');
					//d.show(document.getElementById('quickref-bubble'));
					d.showModal();
					if(time && time != ""){
						setTimeout(function() {
						   d.close();
						},time);
					}
				
				
			}
		};
	window.Win = Win;
})();