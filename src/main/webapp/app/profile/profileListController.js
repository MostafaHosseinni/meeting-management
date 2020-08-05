app.controller("profileListController", function($scope, Labels, UiUtil,
		CurrentUser, CrudUtil) {

	$scope.preload = function() {

		$scope.Labels = Labels;

		$scope.titlePage = Labels.Generals.list + " " + Labels.Profile.main;

		$scope.entityName = "Profile";

		$scope.tableModel = UiUtil.getDefaultTableModel($scope,
				$scope.entityName);

		$scope.tableModel.columns = [
				UiUtil.getDefaultColumn(Labels.Profile.firstName, "firstName"),
				UiUtil.getDefaultColumn(Labels.Profile.lastName, "lastName"),
				UiUtil.getDefaultColumn(Labels.Profile.userName, "userName"),
				UiUtil.getDefaultColumn(Labels.Profile.profileType,
						"profileType") ];

		$scope.profileTypeModel = UiUtil.getDefaultLocalComboModel([

				UiUtil.getDefaultComboLocalData(Labels.ProfileType.admin,
						Labels.ProfileType.admin),

				UiUtil.getDefaultComboLocalData(Labels.ProfileType.secratery,
						Labels.ProfileType.secratery),

				UiUtil.getDefaultComboLocalData(Labels.ProfileType.invitees,
						Labels.ProfileType.invitees) ]);

	}

	$scope.load = function() {
		$scope.preload();
	}

	$scope.addItem = function() {
		$scope.modelData = {};
	}

	$scope.load();

	$scope.validate = function() {
		return $scope.modelForm.$valid;
	}

	$scope.saveOrUpdate = function() {

		if (!$scope.validate()) {
			alertWarning(Labels.Warning.fillForm);
			return;
		}

		if ($scope.modelData.password != $scope.modelData.confirmPassword) {
			alertError(Labels.Error.notEqualPassword);
			return;
		}

		if (!$scope.modelData.id) {
			CrudUtil.save($scope.entityName, $scope.modelData).then(
					function(response) {
						alertSuccess(Labels.Success.save);
						$scope.afterSaveOrUpdateSuccessful(true);
					});
		} else {
			CrudUtil.update($scope.entityName, $scope.modelData).then(
					function(response) {
						alertSuccess(Labels.Success.update);
						$scope.afterSaveOrUpdateSuccessful(false);
					});
		}

	}

	$scope.afterSaveOrUpdateSuccessful = function(isSave) {
		$scope.modelData = {};
		$('#' + $scope.entityName + 'View').modal('hide');
		$scope.tableModel.callService();
	}
});
