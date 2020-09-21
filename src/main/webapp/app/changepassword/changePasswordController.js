app.controller("changePasswordController", function($scope, $location, Labels,
		CrudUtil , OperationUtil) {

	$scope.preload = function() {

		$scope.Labels = Labels;
		$scope.titlePage = Labels.ChangePassword.main;

		$scope.entityName = "Profile";

		$scope.modelData = {};
		$scope.currentPasswordType = "password";
		$scope.newPasswordType = "password";
		$scope.confirmedPasswordType = "password";

		$scope.currentPasswordOperations = [
				OperationUtil.getDefaultIconOperation('fa fa-eye', function() {
					$scope.currentPasswordType = "password";
				}),
				OperationUtil.getDefaultIconOperation('fa fa-eye-slash',
						function() {
							$scope.currentPasswordType = "text";
						}) ];

		$scope.newPasswordOperations = [
				OperationUtil.getDefaultIconOperation('fa fa-eye', function() {
					$scope.newPasswordType = "password";
				}),
				OperationUtil.getDefaultIconOperation('fa fa-eye-slash',
						function() {
							$scope.newPasswordType = "text";
						}) ];

		$scope.confirmedPasswordOperations = [
				OperationUtil.getDefaultIconOperation('fa fa-eye', function() {
					$scope.confirmedPasswordType = "password";
				}),
				OperationUtil.getDefaultIconOperation('fa fa-eye-slash',
						function() {
							$scope.confirmedPasswordType = "text";
						}) ];

	}

	$scope.load = function() {
		$scope.preload();
	}

	$scope.changePassword = function() {
		if (!$scope.modelForm.$valid) {
			alertWarning(Labels.Warning.fillForm);
			return;
		} else if (!$scope.modelForm1.$valid) {
			alertWarning(Labels.Warning.characterNotSuccess);
			return;

		} else {
			CrudUtil.customPostService($scope.entityName, $scope.modelData,
					'changePassword').then(function(data) {
				localStorage.paH = md5($scope.modelData.newPassword + Labels.app);
				alertSuccess(Labels.Success.operationDone);
				$scope.modelData = {};
			});

		}
	}

	$scope.load();

});
