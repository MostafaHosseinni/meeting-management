app.controller("changePasswordController", function ($scope, $location, Labels, CrudUtil) {

	$scope.preload = function () {

		$scope.Labels = Labels;
		$scope.titlePage = Labels.ChangePassword.main;

		$scope.entityName = "Profile";

		$scope.data = {};

	}

	$scope.load = function () {
		$scope.preload();
	}


	$scope.changePassword = function () {
		CrudUtil.customPostService($scope.entityName, $scope.data,
			'changePassword').then(function (data) {
				localStorage.paH = md5($scope.data.newPassword + Labels.app);
				alertSuccess(Labels.Success.operationDone);
				$scope.data = {};
			});

	}

	$scope.load();

});
