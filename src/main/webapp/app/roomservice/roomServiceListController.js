app.controller("roomServiceListController", function ($scope, Labels, UiUtil,
	CurrentUser, CrudUtil) {

	$scope.preload = function () {

		$scope.Labels = Labels;

		$scope.titlePage = Labels.Generals.list + " " + Labels.RoomService.main;

		$scope.entityName = "RoomService";

		$scope.tableModel = UiUtil.getDefaultTableModel($scope,
			$scope.entityName);



		$scope.tableModel.columns = [UiUtil.getDefaultColumn(Labels.RoomService.serviceCount, "serviceCount"), UiUtil.getDefaultColumn(Labels.RoomService.meetingRoom, "meetingRoom.id"), UiUtil.getDefaultColumn(Labels.RoomService.serviceType, "serviceType.id")];
		$scope.meetingRoomModel = UiUtil.getDefualtAutoCompleteModel("MeetingRoom", "id", "id");
		$scope.serviceTypeModel = UiUtil.getDefualtAutoCompleteModel("ServiceType", "id", "id");

	}

	$scope.load = function () {
		$scope.preload();
	}

	$scope.addItem = function () {
		$scope.modelData = {};
	}

	$scope.load();

	$scope.validate = function () {
		return $scope.modelForm.$valid;
	}

	$scope.saveOrUpdate = function () {

		if (!$scope.validate()) {
			alertWarning(Labels.Warning.fillForm);
			return;
		}

		if (!$scope.modelData.id) {
			CrudUtil.save($scope.entityName, $scope.modelData).then(
				function (response) {
					alertSuccess(Labels.Success.save);
					$scope.afterSaveOrUpdateSuccessful(true);
				});
		} else {
			CrudUtil.update($scope.entityName, $scope.modelData).then(
				function (response) {
					alertSuccess(Labels.Success.update);
					$scope.afterSaveOrUpdateSuccessful(false);
				});
		}

	}

	$scope.afterSaveOrUpdateSuccessful = function (isSave) {
		$scope.modelData = {};
		$('#' + $scope.entityName + 'View').modal('hide');
		$scope.tableModel.callService();
	}
});