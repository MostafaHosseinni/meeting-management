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
		"operations" : "=operations"
	};

	directive.templateUrl = "js/component/ButtonTextBoxTemplate.html";

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

	};
	return directive;
} ]);