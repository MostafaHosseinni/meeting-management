app.controller("meetingRoomListController",
	function ($scope, Labels, UiUtil, CurrentUser, CrudUtil, OperationUtil) {

		$scope.preload = function () {

			$scope.Labels = Labels;

			$scope.modelData = {};
			$scope.modelData.service = [];

			$scope.titlePage = Labels.Generals.list + " " +
				Labels.MeetingRoom.main;

			$scope.entityName = "MeetingRoom";


			$scope.tableModel = UiUtil.getDefaultTableModel($scope,
				$scope.entityName);

			$scope.tableModel.columns = [
				UiUtil.getDefaultColumn(Labels.MeetingRoom.roomName,
					"roomName"),
				UiUtil.getDefaultColumn(Labels.MeetingRoom.capacity,
					"capacity"),
				UiUtil.getDefaultColumn(Labels.MeetingRoom.address,
					"address"),
				UiUtil.getDefaultColumn(Labels.MeetingRoom.expireDate,
					"expireDate")
			];



			$scope.ServiceTypeModel = UiUtil
				.getDefaultServiceComboModel("ServiceType",
					"title");


			$scope.clientTableModel = UiUtil.getDefaultClientTableModel(
				$scope, "RoomService");
			$scope.clientTableModel.columns = [UiUtil.getDefaultColumn(
					Labels.ServiceType.main, "serviceType.title"),
				UiUtil.getDefaultColumn(Labels.ServiceType.serviceNumber, "serviceCount"),


			];

			$scope.clientTableModel.operations[0] = OperationUtil.getDefaultIconOperation('fa fa-trash', function (element, entityName) {
				$scope.modelData.service = $scope.modelData.service
					.filter(function (el) {
						return el != element;
					});
			});
			$scope.clientTableModel.rowTitle = "#";
			$scope.clientTableModel.operationTitle = Labels.Generals.operation;

			$scope.clientTableModel.nextPageLabel = Labels.Buttons.nextPage;
			$scope.clientTableModel.previousPageLabel = Labels.Buttons.backPage;

		}




		$scope.load = function () {
			$scope.preload();
			$scope.temp = {};
		}

		$scope.checkEmpty = function () {
			return $scope.tempForm.$valid;
		}


		$scope.addService = function () {
			if (!$scope.checkEmpty()) {
				alertWarning(Labels.Warning.fillForm);
				return;
			} else {
				for (var i = 0; i < $scope.modelData.service.length; i++) {
					if ($scope.modelData.service[i].serviceType.id == $scope.temp.serviceType.id) {
						alertWarning(Labels.Warning.dataRedantant);
						return;
					}
				}
				$scope.modelData.service.push($scope.temp);
				$scope.temp = {};

			}
		}

		$scope.addItem = function () {
			$scope.modelData = {};
			$scope.modelData.service = [];
			$scope.temp = {};
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