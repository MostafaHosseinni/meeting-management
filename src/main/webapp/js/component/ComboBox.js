'use strict';
app.directive('combobox', [
	'CrudUtil',
	'BaseTableUtil',

	function (CrudUtil, BaseTableUtil) {
		var directive = {};
		directive.restrict = 'E';
		directive.scope = {
			"ngModel": "=model",
			"comboModel": "=combomodel",
			"label": "=label",
			"ngDisabled": "=disable",
			"required": "=require"
		};
		directive.templateUrl = "js/component/ComboBoxTemplate.html";

		directive.link = {};

		directive.link.pre = function ($scope, elem, attr, ctrl) {
			if ($scope.comboModel.isLocal) {
				$scope.ngOptions = "a[comboModel.value] as a[comboModel.displayName] for a in  comboModel.localData";
			} else {
				$scope.ngOptions = " a as a[comboModel.displayName] for a in  comboModel.localData track by a.id"
			}
		}
		directive.link.post = function ($scope, elem, attr, ctrl) {
			if (attr.size)
				$scope.size = 'col-md-' + attr.size + ' col-sm-6 col-xs-12';
			else
				$scope.size = 'col-md-4 col-sm-6 col-xs-12';


			if (!$scope.comboModel.isLocal) {
				if ($scope.comboModel.service == undefined) {
					BaseTableUtil
						.getBaseTable($scope.comboModel.entityName)
						.then(function (data) {
							$scope.comboModel.localData = data;
						});
				} else {
					CrudUtil.customPostService(
						$scope.comboModel.entityName,
						$scope.comboModel.param,
						$scope.comboModel.methodName).then(
							function (data) {
								$scope.comboModel.localData = data;
							});
				}
			}


			$scope.changeValue = function () {
				$scope.comboModel.change($scope.ngModel);
			}

		}

		return directive;
	}]);