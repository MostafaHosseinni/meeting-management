app.controller("profile", function($scope, $location, Labels, CrudUtil) {

	$scope.preload = function() {

		$scope.Labels = Labels;
		$scope.titlePage = Labels.Profile.main;

		$scope.entityName = "account";

	}

	$scope.load = function() {
		$scope.preload();
	}

	$scope.save = function() {
		if ($scope.checkCurrentPassword()) {
			CrudUtil.save($scope.entityName, $scope.data).then(function(data) {
				alertSuccess(Labels.Success.update);	
			});
		} else {
			alertError(Labels.Error.currentPassword);
		}
	}

	$scope.load();

});
