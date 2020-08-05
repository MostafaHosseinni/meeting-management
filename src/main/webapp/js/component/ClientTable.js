'use strict';
app.directive('clienttable', [ function() {
	var directive = {};
	directive.restrict = 'AE';
	directive.scope = {
		"tableModel" : "=tablemodel",
		"elements" : "=elements"

	};
	directive.templateUrl = "js/component/ClientTableTemplate.html";
	directive.link = function($scope, elem, attr, ctrl) {

		$scope.Math = window.Math;

		$scope.clientPopoverInput = "clientPopoverInput";
	}

	return directive;
} ]);

app.filter('pagination', function() {

	return function(input, start) {
		start = +start;
		return input.slice(start);
	};
});