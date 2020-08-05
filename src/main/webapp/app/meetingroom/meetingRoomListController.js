app.controller("meetingRoomListController",
	function ($scope, Labels, UiUtil, CurrentUser, CrudUtil) {

		$scope.preload = function () {

			$scope.Labels = Labels;

			$scope.titlePage = Labels.Generals.list + " "
				+ Labels.MeetingRoom.main;

			$scope.entityName = "MeetingRoom";

			$scope.tableModel = UiUtil.getDefaultTableModel($scope,
				$scope.entityName);

			$scope.tableModel.columns = [
				UiUtil.getDefaultColumn(Labels.MeetingRoom.roomName,
					"roomName"),
				UiUtil.getDefaultColumn(Labels.MeetingRoom.capacity,
					"capacity"),
				UiUtil.getDefaultColumn(Labels.MeetingRoom.address,
					"address")];

			// $scope.serviceModel = UiUtil
			// 	.getDefualtAutoCompleteModel("RoomService",
			// 		"id", "id");
			// $scope.serviceModel.serviceName = "getAll";

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
