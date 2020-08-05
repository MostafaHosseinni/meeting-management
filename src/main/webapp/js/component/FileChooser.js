(function() {
	'use strict';
	angular.module('ng-file-model', []).directive("ngFileModel", [ function() {
		return {
			scope : {
				ngFileData : "=",
				ngFileModel : "=",
			},
			link : function(scope, element, attributes) {
				element.bind("change", function(changeEvent) {
					var reader = new FileReader();
					reader.onload = function(loadEvent) {
						scope.$apply(function() {
							scope.ngFileData = changeEvent.target.files[0];
							// scope.ngFileData = {
							// lastModified:
							// changeEvent.target.files[0].lastModified,
							// lastModifiedDate:
							// changeEvent.target.files[0].lastModifiedDate,
							// name: changeEvent.target.files[0].name,
							// size: changeEvent.target.files[0].size,
							// type: changeEvent.target.files[0].type,
							// data: loadEvent.target.result
							// };
							scope.ngFileModel = loadEvent.target.result;

							console.debug('model : ' + scope.ngFileModel);
							console.debug('data : ' + scope.ngFileData);

						});
					}
					if (changeEvent.target.files[0])
						reader.readAsDataURL(changeEvent.target.files[0]);
				});
			}
		}
	} ]);
})();