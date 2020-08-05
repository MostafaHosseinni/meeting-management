(function() {
	'use strict';
	app.controller('homeController', homeController);

	homeController.$inject = [ '$scope', '$location', 'Labels', 'UiUtil',
			'CrudUtil', 'CurrentUser' ,'ngIntroService'];

	function homeController($scope, $location, Labels, UiUtil, CrudUtil,
			CurrentUser , ngIntroService) {
		$scope.Labels = Labels;
		
	}
})();
