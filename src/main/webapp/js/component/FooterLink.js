'use strict';
app.directive('footerlink', [

'Labels', function(Labels) {
	var directive = {};
	directive.restrict = 'E';
	directive.scope = {
		"label" : "=label",
		"require" : "=require",
		"ngDisabled" : "=disable",
		"model" : "=model"
	};

	directive.templateUrl = "js/component/FooterLinkTemplate.html";

	directive.link = function($scope, elem, attr, ctrl) {

		$scope.Labels = Labels;
		$scope.addData = {};

		if (!$scope.model)
			$scope.model = [];

		if (attr.size)
			$scope.size = 'col-md-' + attr.size + ' col-sm-12 col-xs-12';
		else
			$scope.size = 'col-md-12 col-sm-12 col-xs-12';

		$scope.addOperation = function() {
			$scope.model.push($scope.addData);
			$scope.addData = {};
		};

		$scope.removeItem = function(item) {
			$scope.model = $scope.model.filter(function(el) {
				return el.link != item.link;
			});

		}
	}
	return directive;
} ]);