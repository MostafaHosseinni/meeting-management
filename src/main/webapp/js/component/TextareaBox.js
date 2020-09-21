'use strict';
app.directive('textareabox', [ function() {
	var directive = {};
	directive.restrict = 'E';
	directive.scope = {
		"ngModel" : "=model",
		"label" : "=label",
		"ngDisabled" : "=disable",
		"mask" : "@mask",
		"required" : "=require",
		"row" : "@row",
		"maxlength" : "@maxlength",
		"minlength" : "@minlength"
	};

	directive.templateUrl = "js/component/TextareaBoxTemplate.html";

	directive.link = function($scope, elem, attr, ctrl) {

		if (attr.size)
			$scope.size = 'col-md-' + attr.size + ' col-sm-6 col-xs-12';
		else
			$scope.size = 'col-md-4 col-sm-6 col-xs-12';

		if (!attr.row)
			$scope.row = 3;
		
		if (!attr.maxlength)
			$scope.maxlength = "1000";

		if (!attr.minlength)
			$scope.minlength = "0";

		$scope.checkLength = function() {
			if ($scope.ngModel) {
				return " (" + $scope.ngModel.length + "/" + $scope.maxlength
						+ ")";
			} else
				return " (0/" + $scope.maxlength + ")";
		}
	};
	return directive;
} ]);