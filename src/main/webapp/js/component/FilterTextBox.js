'use strict';
app
		.directive(
				'filtertext',
				[ function() {
					var directive = {};
					directive.restrict = 'E';
					directive.scope = {
						"ngModel" : "=model"
					};

					directive.templateUrl = "js/component/FilterTextBoxTemplate.html";
					
					directive.link = function($scope, elem, attr, ctrl) {

						$scope.clearModel = function() {
							$scope.ngModel = '';
						}

					};
					return directive;
				} ]);