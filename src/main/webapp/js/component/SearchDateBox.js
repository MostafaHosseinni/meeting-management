'use strict';
app.directive('searchdatebox', [ function() {
	var directive = {};
	directive.restrict = 'E';
	directive.scope = {
		"ngModel" : "=model",
		"label" : "=label"
	};

	directive.templateUrl = "js/component/SearchDateBoxTemplate.html";

	directive.link = function($scope, elem, attr, ctrl) {
	  if (attr.size)
      $scope.size = 'col-md-' + attr.size + ' col-sm-6 col-xs-12';
    else
     $scope.size = 'col-md-4 col-sm-6 col-xs-12';		
	};
	return directive;
} ]);