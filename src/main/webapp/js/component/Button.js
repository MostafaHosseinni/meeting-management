'use strict';
app.directive('mybutton', [ function() {
	var directive = {};
	directive.restrict = 'E';
	directive.scope = {
		"click" : "&click",
		"label" : "=label",
		"ngDisabled" : "=disable",
		"class" : "@class",
		"toggle" : "@toggle",
		"target" : "@target",
		"icon" : "@icon",
		"dismiss" : "@dismiss"
	};

	directive.templateUrl = "/js/component/ButtonTemplate.html";

	directive.link = function($scope, elem, attr, ctrl) {
	}
	return directive;
} ]);