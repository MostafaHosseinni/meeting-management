'use strict';
app.directive('filterbutton', [ function() {
	var directive = {};
	directive.restrict = 'E';
	directive.scope = {
		"ngModel" : "=model",
		"operations" : "=operations"
	};

	directive.templateUrl = "js/component/FilterButtonTemplate.html";

	directive.link = function($scope, elem, attr, ctrl) {

		if ($scope.operations && $scope.operations.length > 0) {
			$scope.selectedOperation = $scope.operations[0];
		}
		
		$scope.buttonClicked = function(){
			var index = $scope.operations.indexOf($scope.selectedOperation);
			index++;
			if(index == $scope.operations.length)
				index=0;
			$scope.selectedOperation = $scope.operations[index];
			$scope.selectedOperation.action();
		}

	};
	return directive;
} ]);