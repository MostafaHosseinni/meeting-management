(function() {
	'use strict';
	app.controller('indexController', indexController);

	indexController.$inject = [ '$scope', '$location', 'Labels', 'UiUtil',
			'CrudUtil', 'CurrentUser' ];

	function indexController($scope, $location, Labels, UiUtil, CrudUtil,
			CurrentUser) {
		$scope.title = 'indexController';

		$scope.preload = function() {
			$scope.Labels = Labels;

			if (CurrentUser.currentUser.id == undefined) {
				CurrentUser
						.getCurrentUser()
						.then(
								function(response) {
									$scope.user = response;

									$scope.fullName = $scope.user.firstName
									+ " "
									+ $scope.user.lastName;
								});

			}

		}
		$scope.load = function() {
			$scope.preload();
		}

		$scope.load();

	}
})();
