'use strict';
app.directive('buttontextbox', [ function() {
	var directive = {};
	directive.restrict = 'E';
	directive.scope = {
		"ngModel" : "=model",
		"label" : "=label",
		"ngDisabled" : "=disable",
		"mask" : "@mask",
		"required" : "=require",
		"operations" : "=operations",
		"type" : "@type",
		"minlength" : "@minlength",
		"maxlength" : "@maxlength",
	};

	directive.templateUrl = "js/component/ButtonTextBoxTemplate.html";
	directive.replace = true;

	directive.link = function($scope, elem, attr, ctrl) {
		if (attr.size)
			$scope.size = 'col-md-' + attr.size + ' col-sm-6 col-xs-12';
		else
			$scope.size = 'col-md-4 col-sm-6 col-xs-12';

		if ($scope.operations && $scope.operations.length > 0) {
			$scope.selectedOperation = $scope.operations[0];
		}

		$scope.buttonClicked = function() {
			var index = $scope.operations.indexOf($scope.selectedOperation);
			index++;
			if (index == $scope.operations.length)
				index = 0;
			$scope.selectedOperation = $scope.operations[index];
			$scope.selectedOperation.action();
		}
		if (!attr.maxlength)
			$scope.maxlength = "30";

		if (!attr.minlength)
			$scope.minlength = "0";

		if (!attr.type)
			$scope.type = "text";

		$scope.checkLength = function() {
			if ($scope.ngModel) {
				return " ("
						+ ($scope.ngModel.length ? $scope.ngModel.length
								: $scope.ngModel.toString().length) + "/"
						+ $scope.maxlength + ")";
			} else
				return " (0/" + $scope.maxlength + ")";
		}

	};
	return directive;
} ]);