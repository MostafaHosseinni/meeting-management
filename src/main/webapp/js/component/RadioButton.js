/*
 * please set radioname  
 */


'use strict';
app.directive('radiobutton', [ function() {
	var directive = {};
	directive.restrict = 'E';
	directive.scope = {
		"ngModel" : "=model",
		"label" : "=label",
		"ngDisabled" : "=disable",
		"required" : "=require",
		"radioModel" : "=radiomodel",
		"radioName" : "@radioname"
	};

	directive.templateUrl = "js/component/RadioButtonTemplate.html";
	directive.link = function($scope, elem, attr, ctrl) {
	  if (attr.size)
      $scope.size = 'col-md-' + attr.size + ' col-sm-6 col-xs-12';
    else
     $scope.size = 'col-md-4 col-sm-6 col-xs-12';
	};
	return directive;
} ]);
