'use strict';
app.directive('multiselect', [
	'OperationUtil', 'Labels',
	function (OperationUtil, Labels) {
		var directive = {};
		directive.restrict = 'E';
		directive.scope = {
			"label": "=label",
			"require": "=require",
			"ngDisabled": "=disable",
			"multimodel": "=multimodel",
			"model": "=model"
		};

		directive.templateUrl = "js/component/MultiSelectTemplate.html";

		directive.link = function ($scope, elem, attr, ctrl) {

			$scope.Labels = Labels;

			$scope.selectedLabel = $scope.label + " - " + Labels.Generals.selectedItems;

			if (!$scope.model)
				$scope.model = [];

			if ($scope.ngDisabled == undefined) {
				$scope.ngDisabled = false;
			}

			if (attr.size)
				$scope.size = 'col-md-' + attr.size + ' col-sm-6 col-xs-12';
			else
				$scope.size = 'col-md-4 col-sm-6 col-xs-12';


			$scope.addOperation = OperationUtil.getDefaultIconOperation(
				'fa fa-plus',
				function () {
					if($scope.addData)
					$scope.model.push($scope.addData);
					$scope.addData = null;

				});

			$scope.removeItem = function (item) {
				$scope.model = $scope.model.filter(function (el) {
					return el[$scope.multimodel.titleField] != item[$scope.multimodel.titleField]
				});

			}

			$scope.valueRender = function (a) {
				return a[$scope.multimodel.titleField];
			}
		}
		return directive;
	}
]);